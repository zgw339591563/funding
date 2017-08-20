package com.atguigu.atcrowdfunding.user.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfunding.common.AJAXResult;
import com.atguigu.atcrowdfunding.common.Datas;
import com.atguigu.atcrowdfunding.common.Page;
import com.atguigu.atcrowdfunding.common.User;
import com.atguigu.atcrowdfunding.user.bean.Role;
import com.atguigu.atcrowdfunding.user.service.RoleService;
import com.atguigu.atcrowdfunding.user.service.UserService;
import com.atguigu.atcrowdfunding.util.MD5Util;
import com.atguigu.atcrowdfunding.util.StringUtil;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	
	@RequestMapping("/index")
	public String index() {
		return "user/index";
	}
	
	@RequestMapping("/add")
	public String add() {
		return "user/add";
	}
	
	@RequestMapping("/assignRole")
	public String assignRole( Integer id, Model model ) {
		
		User user = userService.queryById(id);
		model.addAttribute("user", user);
		
		// 获取所有的角色数据
		List<Role> roles = roleService.queryAll();
		
		// 未分配的角色数据集合
		List<Role> unassignList = new ArrayList<Role>();
		// 已分配的角色数据集合
		List<Role> assignList = new ArrayList<Role>();
		
		// 查询当前用户已经分配的角色ID列表
		// 1, 4
		List<Integer> roleIds = roleService.queryRoleIdsByUserid(id);
		
		for ( Role role : roles ) {
			if ( roleIds.contains(role.getId()) ) {
				assignList.add(role);
			} else {
				unassignList.add(role);
			}
		}
		
		model.addAttribute("unassignList", unassignList);
		model.addAttribute("assignList", assignList);
		
		return "user/assignRole";
	}
	
	@RequestMapping("/edit")
	public String edit( Integer id, Model model ) {
		
		// 根据主键查询用户信息
		User user = userService.queryById(id);
		model.addAttribute("user", user);
		
		return "user/edit";
	}
	
	@ResponseBody
	@RequestMapping("/assign")
	public Object assign( Integer userid, Datas ds ) {
		AJAXResult result = new AJAXResult();
		
		try {
			int count = userService.insertUserRoles(userid, ds);
			if ( count == ds.getIds().size() ) {
				result.setSuccess(true);
			} else {
				result.setSuccess(false);
			}
		} catch ( Exception e ) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/unassign")
	public Object unassign( Integer userid, Datas ds ) {
		AJAXResult result = new AJAXResult();
		
		try {
			int count = userService.deleteUserRoles(userid, ds);
			if ( count == ds.getIds().size() ) {
				result.setSuccess(true);
			} else {
				result.setSuccess(false);
			}
		} catch ( Exception e ) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/deletes")
	public Object deletes( Datas ds ) {
		AJAXResult result = new AJAXResult();
		
		try {
			int count = userService.deleteUsers(ds);
			if ( count == ds.getDatas().size() ) {
				result.setSuccess(true);
			} else {
				result.setSuccess(false);
			}
		} catch ( Exception e ) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public Object delete( Integer id ) {
		AJAXResult result = new AJAXResult();
		
		try {
			int count = userService.deleteUser(id);
			if ( count == 1 ) {
				result.setSuccess(true);
			} else {
				result.setSuccess(false);
			}
		} catch ( Exception e ) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/update")
	public Object update( User user ) {
		AJAXResult result = new AJAXResult();
		
		try {
			int count = userService.updateUser(user);
			if ( count == 1 ) {
				result.setSuccess(true);
			} else {
				result.setSuccess(false);
			}
		} catch ( Exception e ) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}
	
	/**
	 * 新增用户数据
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/insert")
	public Object insert( User user ) {
		AJAXResult result = new AJAXResult();
		
		try {
			user.setUserpswd(MD5Util.digest("123456"));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			user.setCreatetime(sdf.format(new Date()));
			userService.insertUser(user);
			result.setSuccess(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}
	
	/**
	 * 分页查询用户数据
	 * @param pageno
	 * @param pagesize
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/pageQuery")
	public Object pageQuery(String pagetext, Integer pageno, Integer pagesize) {
		AJAXResult result = new AJAXResult();
		
		try {
			// 查询用户数据
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("start", (pageno-1)*pagesize);
			paramMap.put("size", pagesize);
			if ( StringUtil.isNotEmpty(pagetext) ) {
				//pagetext = pagetext.replaceAll("%", "\\%");
			}
			paramMap.put("pagetext", pagetext);
			
			// 分页查询数据
			List<User> users = userService.pageQuery(paramMap);
			// 获取数据的总条数
			int count = userService.queryCount(paramMap);
			int totalno = 0;
			// 获取总页码
			if ( count % pagesize == 0) {
				totalno = count / pagesize;
			} else {
				totalno = count / pagesize + 1;
			}
			
			Page<User> userPage = new Page<User>();
			userPage.setDatas(users);
			userPage.setTotalno(totalno);
			userPage.setTotalsize(count);
			userPage.setPageno(pageno);
			userPage.setPagesize(pagesize);
			result.setPageObj(userPage);
			result.setSuccess(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}
	
	
/*	@RequestMapping("/index")
	public String index( Model model, @RequestParam(required=false, defaultValue="1")Integer pageno, @RequestParam(required=false, defaultValue="2")Integer pagesize ) {
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("start", (pageno-1)*pagesize);
		paramMap.put("size", pagesize);
		// 分页查询数据
		List<User> users = userService.pageQuery(paramMap);
		// 获取数据的总条数
		int count = userService.queryCount();
		int totalno = 0;
		// 获取总页码
		if ( count % pagesize == 0) {
			totalno = count / pagesize;
		} else {
			totalno = count / pagesize + 1;
		}
		
		Page<User> userPage = new Page<User>();
		userPage.setDatas(users);
		userPage.setTotalno(totalno);
		userPage.setTotalsize(count);
		userPage.setPageno(pageno);
		userPage.setPagesize(pagesize);
		
		model.addAttribute("userPage", userPage);
		
		return "user/index";
	}*/
}
