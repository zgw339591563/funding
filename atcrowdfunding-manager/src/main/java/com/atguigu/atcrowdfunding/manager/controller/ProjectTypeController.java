package com.atguigu.atcrowdfunding.manager.controller;

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
import com.atguigu.atcrowdfunding.manager.bean.ProjectType;
import com.atguigu.atcrowdfunding.manager.service.ProjectTypeService;
import com.atguigu.atcrowdfunding.util.StringUtil;

@Controller
@RequestMapping("/projectType")
public class ProjectTypeController {

	@Autowired
	private ProjectTypeService projectTypeService;
	

	@RequestMapping("/index")
	public String index() {
		return "prjtype/index";
	}
	
	@RequestMapping("/add")
	public String add() {
		return "prjtype/add";
	}
	
	@RequestMapping("/edit")
	public String edit( Integer id, Model model ) {
		
		// 根据主键查询资质信息
		ProjectType projectType = projectTypeService.queryById(id);
		model.addAttribute("projectType", projectType);
		
		return "prjtype/edit";
	}
	
	@ResponseBody
	@RequestMapping("/deletes")
	public Object deletes( Datas ds ) {
		AJAXResult result = new AJAXResult();
		
		try {
			int count = projectTypeService.deleteProjectTypes(ds);
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
			int count = projectTypeService.deleteProjectType(id);
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
	public Object update( ProjectType projectType ) {
		AJAXResult result = new AJAXResult();
		
		try {
			int count = projectTypeService.updateProjectType(projectType);
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
	 * 新增资质数据
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/insert")
	public Object insert( ProjectType projectType ) {
		AJAXResult result = new AJAXResult();
		
		try {
			projectTypeService.insertProjectType(projectType);
			result.setSuccess(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}
	
	/**
	 * 分页查询资质数据
	 * @param pageno
	 * @param pagesize
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/pageQuery")
	public Object pageQuery(String pagetext, Integer pageno, Integer pagesize) {
		AJAXResult result = new AJAXResult();
		
		try {
			// 查询资质数据
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("start", (pageno-1)*pagesize);
			paramMap.put("size", pagesize);
			if ( StringUtil.isNotEmpty(pagetext) ) {
				//pagetext = pagetext.replaceAll("%", "\\%");
			}
			paramMap.put("pagetext", pagetext);
			
			// 分页查询数据
			List<ProjectType> projectTypes = projectTypeService.pageQuery(paramMap);
			// 获取数据的总条数
			int count = projectTypeService.queryCount(paramMap);
			int totalno = 0;
			// 获取总页码
			if ( count % pagesize == 0) {
				totalno = count / pagesize;
			} else {
				totalno = count / pagesize + 1;
			}
			
			Page<ProjectType> projectTypePage = new Page<ProjectType>();
			projectTypePage.setDatas(projectTypes);
			projectTypePage.setTotalno(totalno);
			projectTypePage.setTotalsize(count);
			projectTypePage.setPageno(pageno);
			projectTypePage.setPagesize(pagesize);
			result.setPageObj(projectTypePage);
			result.setSuccess(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}
}
