package com.atguigu.atcrowdfunding.manager.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfunding.common.AJAXResult;
import com.atguigu.atcrowdfunding.common.CertImg;
import com.atguigu.atcrowdfunding.common.Member;
import com.atguigu.atcrowdfunding.common.Page;
import com.atguigu.atcrowdfunding.common.Ticket;
import com.atguigu.atcrowdfunding.manager.service.AuthCertService;

@Controller
@RequestMapping("/auth_cert")
public class AuthCertController {

	@Autowired
	private AuthCertService authCertService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private RepositoryService repositoryService;
	
	@RequestMapping("/index")
	public String index() {
		return "auth/cert";
	}
	
	@RequestMapping("/show")
	public String show( Integer memberid, Model model ) {
		
		Member member = authCertService.queryMemberById(memberid);
		
		// 查询当前会员提交资质文件
		List<CertImg> certImgs = authCertService.queryCertImgsByMemberid(memberid);
		
		model.addAttribute("member", member);
		model.addAttribute("certImgs", certImgs);
		
		return "auth/cert_show";
	}
	
	@ResponseBody
	@RequestMapping("/pageQuery")
	public Object pageQuery(Integer pageno, Integer pagesize) {
		AJAXResult result = new AJAXResult();
		
		try {
			// 查询任务数据
			TaskQuery query = taskService.createTaskQuery();
			List<Task> tasks = 
				query.processDefinitionKey("auth")
				     .taskCandidateGroup("backuser")
				     .listPage((pageno-1)*pagesize, pagesize);
			
			List<Map<String, Object>> taskMapList = new ArrayList<Map<String, Object>>();
			
			for ( Task task : tasks ) {
				Map<String, Object> taskMap = new HashMap<String, Object>();
				taskMap.put("id", task.getId());
				taskMap.put("name", task.getName());
				
				ProcessDefinition pd =
					repositoryService
					    .createProcessDefinitionQuery()
					    .processDefinitionId(task.getProcessDefinitionId())
					    .singleResult();
				
				taskMap.put("procDefName", pd.getName());
				taskMap.put("procDefVersion", pd.getVersion());
				
				// 通过流程查找流程审批单，再查询会员信息
				Ticket ticket = authCertService.queryTicketByPiid(task.getProcessInstanceId());
				Member member = authCertService.queryMemberByTicket(ticket);
				taskMap.put("memberName", member.getUsername());
				taskMap.put("memberid", member.getId());
				
				taskMapList.add(taskMap);
			}
			// 获取数据的总条数
			int count = (int)query.count();
			int totalno = 0;
			// 获取总页码
			if ( count % pagesize == 0) {
				totalno = count / pagesize;
			} else {
				totalno = count / pagesize + 1;
			}
			
			Page<Map<String, Object>> certPage = new Page<Map<String, Object>>();
			certPage.setDatas(taskMapList);
			certPage.setTotalno(totalno);
			certPage.setTotalsize(count);
			certPage.setPageno(pageno);
			certPage.setPagesize(pagesize);
			result.setPageObj(certPage);
			result.setSuccess(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/pass")
	public Object pass( String taskId, Integer memberid ) {
		AJAXResult result = new AJAXResult();
		
		try {
			taskService.setVariable(taskId, "flg", true);
			taskService.setVariable(taskId, "memberid", memberid);
			// 传递参数，让流程继续执行
			taskService.complete(taskId);
			result.setSuccess(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/refuse")
	public Object refuse(String taskId, Integer memberid) {
		AJAXResult result = new AJAXResult();
		
		try {
			taskService.setVariable(taskId, "flg", false);
			taskService.setVariable(taskId, "memberid", memberid);
			// 传递参数，让流程继续执行
			taskService.complete(taskId);
			result.setSuccess(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			result.setSuccess(false);
		}		
		
		return result;
	}
}
