package com.atguigu.atcrowdfunding.project.service;

import java.util.List;

import com.atguigu.atcrowdfunding.project.bean.Project;

public interface ProjectService {

	Project queryById(Integer pid);

	List<Project> queryHotProjects();

	List<Project> queryAll();

}
