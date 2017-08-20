package com.atguigu.atcrowdfunding.manager.bean;

import java.util.ArrayList;
import java.util.List;

import com.atguigu.atcrowdfunding.project.bean.Project;

public class ProjectType {

	private Integer id;
	private String name;
	private String remark;
	
	private List<Project> projects = new ArrayList<Project>();
	
	public List<Project> getProjects() {
		return projects;
	}
	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
