package com.atguigu.atcrowdfunding.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfunding.common.AJAXResult;
import com.atguigu.atcrowdfunding.project.bean.Project;
import com.atguigu.atcrowdfunding.project.service.ProjectService;

@Controller
@RequestMapping(value={"/project"})
public class ProjectController {

	@Autowired
	private ProjectService projectService;
	
	@RequestMapping(value={"/list"})
	public String list() {
		return "project/list";
	}
	
	@RequestMapping("/detail")
	public String detail( Integer pid, Model model ) {
		
		Project project = projectService.queryById(pid);
		
		if ( project == null ) {
			return "redirect:/project/list.htm";
		} else {
			model.addAttribute("project", project);
			return "project/detail";
		}
	}
	
	@RequestMapping("/start")
	public String start() {
		return "project/start";
	}
	
	@RequestMapping("/create")
	public String create() {
		return "project/create";
	}
	
	@RequestMapping("/basicinfo")
	public String basicinfo() {
		return "project/basicinfo";
	}
	
	@ResponseBody
	@RequestMapping("/saveBasicinfo")
	public Object saveBasicinfo() {
		AJAXResult result = new AJAXResult();
		
		try {
			
			
			result.setSuccess(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}

	public ProjectService getProjectService() {
		return projectService;
	}

	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}
}
