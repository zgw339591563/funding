package com.atguigu.atcrowdfunding.user.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.atguigu.atcrowdfunding.common.AJAXResult;
import com.atguigu.atcrowdfunding.common.AuthPassListener;
import com.atguigu.atcrowdfunding.common.AuthRefuseListener;
import com.atguigu.atcrowdfunding.common.CertImg;
import com.atguigu.atcrowdfunding.common.Datas;
import com.atguigu.atcrowdfunding.common.Member;
import com.atguigu.atcrowdfunding.common.Ticket;
import com.atguigu.atcrowdfunding.manager.bean.Cert;
import com.atguigu.atcrowdfunding.user.service.MemberService;
import com.atguigu.atcrowdfunding.util.Const;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberService memberService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private TaskService taskService;
	
	@RequestMapping("/mine")
	public String mine() {
		return "member/mine";
	}
	
	@RequestMapping("/accttype")
	public String accttype() {
		return "member/accttype";
	}
	
	@RequestMapping("/basicinfo")
	public String basicinfo() {
		return "member/basicinfo";
	}
	
	@RequestMapping("/checkemail")
	public String checkemail() {
		return "member/checkemail";
	}
	
	@RequestMapping("/message")
	public String message() {
		return "message/index";
	}
	
	@RequestMapping("/apply")
	public String apply( HttpSession session, Model model ) {
		
		// 会员（1） ==》 正在审批的流程单（1）
		// 查询流程审批单
		Member loginMember = (Member)session.getAttribute(Const.LOGIN_MEMBER);
		Ticket ticket = memberService.quertTicketByMember(loginMember);
		if ( ticket == null ) {
			// 创建流程审批单
			ticket = new Ticket();
			ticket.setMemberid(loginMember.getId());
			ticket.setStatus("0");
			memberService.insertTicket(ticket);
			
			return "member/accttype";
		} else {
			// 根据流程审批单的步骤，跳转页面
			if ( "accttype".equals(ticket.getPstep()) ) {
				return "member/basicinfo";
			} else if ( "basicinfo".equals(ticket.getPstep()) ) {
				
				// 查询当前用户账户类型所对应的资质文件集合
				List<Cert> certs = memberService.queryCertsByAccttype(loginMember.getAccttype());
				model.addAttribute("certs", certs);
				return "member/uploadfile";
			} else if ( "uploadfile".equals(ticket.getPstep()) ) {
				return "member/checkemail";
			} else if ( "checkemail".equals(ticket.getPstep()) ) {
				return "member/checkapply";
			}
		}
		
		return "member/accttype";
	}
	
	@ResponseBody
	@RequestMapping("/finishApply")
	public Object finishApply( HttpSession session, String authcode ) {
		AJAXResult result = new AJAXResult();
		
		try {
			Member loginMember = (Member)session.getAttribute(Const.LOGIN_MEMBER);
			Ticket ticket = memberService.quertTicketByMember(loginMember);
			if ( ticket.getAuthcode().equals(authcode) ) {
				
				// 修改会员的申请状态
				loginMember.setAuthstatus("1");
				memberService.updateAuthStatus(loginMember);
				
				// 让申请流程继续执行
				TaskQuery query = 
					taskService.createTaskQuery();
				Task task =
					query.processInstanceId(ticket.getPiid())
				   .taskAssignee(loginMember.getLoginacct())
				   .singleResult();
				
				taskService.complete(task.getId());
				
				result.setSuccess(true);
			} else {
				result.setErrorMsg("验证码输入不正确");
				result.setSuccess(false);
			}
		} catch ( Exception e ) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/startProcess")
	public Object startProcess( HttpSession session, String email ) {
		AJAXResult result = new AJAXResult();
		
		try {
			
			Member loginMember = (Member)session.getAttribute(Const.LOGIN_MEMBER);
			
			if ( !email.equals(loginMember.getEmail()) ) {
				loginMember.setEmail(email);
				memberService.updateEmail(loginMember);
			}
			
			// 启动审批流程
			Map<String, Object> varMap = new HashMap<String, Object>();
			
			// 4 位验证码
			StringBuilder authcode = new StringBuilder();
			for ( int i = 0; i < 4; i++ ) {
				authcode.append(new Random().nextInt(10));
			}
			varMap.put("toEmail", email);
			varMap.put("authcode", authcode.toString());
			varMap.put("loginacct", loginMember.getLoginacct());
			varMap.put("passListener", new AuthPassListener());
			varMap.put("refuseListener", new AuthRefuseListener());
			
			// 查询实名认证的审批流程定义
			ProcessDefinitionQuery query =
				repositoryService.createProcessDefinitionQuery();
			ProcessDefinition pd =
				query.processDefinitionKey("auth").latestVersion().singleResult();
			
			ProcessInstance pi = runtimeService.startProcessInstanceById(pd.getId(), varMap);
			
			// 更新流程审批的步骤
			Ticket ticket = memberService.quertTicketByMember(loginMember);
			ticket.setPstep("checkemail");
			ticket.setPiid(pi.getId());
			ticket.setAuthcode(authcode.toString());
			
			memberService.updateTicket4PI(ticket);
			
			result.setSuccess(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/uploadCertFile")
	public Object uploadCertFile( HttpSession session, Datas ds ) {
		AJAXResult result = new AJAXResult();
		
		try {
			ServletContext application = session.getServletContext();
			Member loginMember = (Member)session.getAttribute(Const.LOGIN_MEMBER);
			String realPath = application.getRealPath("pics");
			// 将上传的文件保存起来，让管理用户进行审核
			for ( CertImg ci : ds.getCertimgs() ) {
				ci.setMemberid(loginMember.getId());
				// 保存文件
				MultipartFile file = ci.getImgfile();
				String oldFileName = file.getOriginalFilename();
				String fileName = UUID.randomUUID().toString() + oldFileName.substring(oldFileName.lastIndexOf("."));
				ci.setIconpath(fileName);
				String filePath = realPath + "/cert/" + fileName;
				file.transferTo(new File(filePath));
			}
			// 增加会员资质
			memberService.insertMemberCerts(ds);
			
			// 更新流程审批的步骤
			Ticket ticket = memberService.quertTicketByMember(loginMember);
			ticket.setPstep("uploadfile");
			
			memberService.updateTicketProcessStep(ticket);
			
			result.setSuccess(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}
	
	/**
	 * 更新基本信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateBasicinfo")
	public Object updateBasicinfo( HttpSession session, Member member ) {
		AJAXResult result = new AJAXResult();
		
		try {
			
			// 获取登录会员信息
			Member loginMember = (Member)session.getAttribute(Const.LOGIN_MEMBER);
			loginMember.setRealname(member.getRealname());
			loginMember.setCardnum(member.getCardnum());;
			loginMember.setTel(member.getTel());
			// 更新会员的基本信息
			memberService.updateBasicinfo(loginMember);
			
			// 更新当前的流程步骤
			Ticket ticket = memberService.quertTicketByMember(loginMember);
			ticket.setPstep("basicinfo");
			
			memberService.updateTicketProcessStep(ticket);
			
			result.setSuccess(true);
		} catch( Exception e ) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}
	
	/**
	 * 更新账户类型
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateAcctType")
	public Object updateAcctType( HttpSession session, String accttype ) {
		AJAXResult result = new AJAXResult();
		
		try {
			
			// 获取登录会员信息
			Member loginMember = (Member)session.getAttribute(Const.LOGIN_MEMBER);
			loginMember.setAccttype(accttype);
			// 更新账户类型
			memberService.updateAcctType(loginMember);
			
			// 更新当前的流程步骤
			Ticket ticket = memberService.quertTicketByMember(loginMember);
			ticket.setPstep("accttype");
			
			memberService.updateTicketProcessStep(ticket);
			
			result.setSuccess(true);
		} catch( Exception e ) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}
}
