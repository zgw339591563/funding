package com.atguigu.atcrowdfunding.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfunding.common.AJAXResult;
import com.atguigu.atcrowdfunding.common.Member;
import com.atguigu.atcrowdfunding.common.User;
import com.atguigu.atcrowdfunding.user.bean.Permission;
import com.atguigu.atcrowdfunding.user.service.MemberService;
import com.atguigu.atcrowdfunding.user.service.UserService;
import com.atguigu.atcrowdfunding.util.Const;
import com.atguigu.atcrowdfunding.util.MD5Util;
import com.atguigu.atcrowdfunding.util.StringUtil;

@Controller
public class DispatcherController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/index")
	public String index( HttpServletRequest req ) throws Exception{
		return "index";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/reg")
	public String reg() {
		return "reg";
	}
	
	@RequestMapping("/error")
	public String error() {
		return "error";
	}
	
	/**
	 * 跳转到后台管理主页面
	 * @return
	 */
	@RequestMapping("/main")
	public String main() {
		return "main";
	}
	
	/**
	 * 跳转到会员中心
	 * @return
	 */
	@RequestMapping("/member")
	public String member() {
		return "member";
	}
	/**
	 * 退出
	 */
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/index.jsp";
	}
	
	/**
	 * 会员登陆
	 * @param session
	 * @param loginacct
	 * @param userpswd
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/doMemberLogin")
	public Object doMemberLogin(HttpSession session, String loginacct, String userpswd) {
		AJAXResult result = new AJAXResult();
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("loginacct", loginacct);
		paramMap.put("userpswd", MD5Util.digest(userpswd));
		// 返回查询结果
		Member member = memberService.queryMember(paramMap);

		if ( member == null ) {
			result.setErrorMsg("会员账号或密码不正确");
			result.setSuccess(false);
		} else {
			// 保存登陆用户信息
			session.setAttribute(Const.LOGIN_MEMBER, member);
			result.setSuccess(true);
		}
		return result;
	}
	
	/**
	 * 执行登陆验证,将操作结果转换为JSON字符串传递给浏览器
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/dologin")
	public Object dologin(HttpSession session, String loginacct, String userpswd, String usertype) {
		
		// JAVA(Object) ==> NET(json string) ==> IE(JSON Object)
		AJAXResult result = new AJAXResult();
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("loginacct", loginacct);
		paramMap.put("userpswd", MD5Util.digest(userpswd));
		// 返回查询结果
		User user = userService.queryUser(paramMap);

		if ( user == null ) {
			result.setErrorMsg("用户账号或密码不正确");
			result.setSuccess(false);
		} else {
			// 保存登陆用户信息
			session.setAttribute(Const.LOGIN_USER, user);
			result.setSuccess(true);
			
			// 读取菜单信息
			Set<String> authUris = new HashSet<String>();
			List<Permission> permissions = userService.queryAuthPermissions(user.getId());
		    Permission rootPermission = null;
		    Map<Integer, Permission> permissionMap = new HashMap<Integer, Permission>();
		    for ( Permission permission : permissions ) {
		    	permissionMap.put(permission.getId(), permission);
		    	if ( StringUtil.isNotEmpty(permission.getUrl()) ) {
		    		authUris.add( "/" + permission.getUrl());
		    	}
		    }
		    
		    for ( Permission permission : permissions ) {
		    	Permission child = permission;
		    	if ( permission.getPid() == 0 ) {
		    		rootPermission = permission;
		    	} else {
		    		Permission parent = permissionMap.get(child.getPid());
		    		parent.getChildren().add(child);
		    	}
		    }
			
			session.setAttribute("rootPermission", rootPermission);
			session.setAttribute(Const.AUTH_URIS, authUris);
		}
		// 在网络中传递的json字符串中的属性一定要使用双引号包含。
		//resp.getWriter().print("{age:20}");
		return result;
	}
	
	/*
	@RequestMapping("/dologin")
	public String dologin( String loginacct, String userpswd, String usertype, Model model ) {
		
		// 获取表单数据
		
		// 查询系统用户（登陆账号，密码）
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("loginacct", loginacct);
		paramMap.put("userpswd", userpswd);
		// 返回查询结果
		User user = userService.queryUser(paramMap);

		// 跳转页面
		if ( user == null ) {
			// 回到登陆页面
			model.addAttribute("errorMsg", "用户账号或密码不正确");
			return "login";
		} else {
			// 跳转页面
			if ( "member".equals(usertype) ) {
				// 会员的场合
				return "redirect:/";
			} else {
				// 管理用户的场合
				return "redirect:/main.htm";
			}
		}
	}
	*/
}
