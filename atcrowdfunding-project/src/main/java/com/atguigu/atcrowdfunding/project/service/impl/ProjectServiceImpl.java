package com.atguigu.atcrowdfunding.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.atcrowdfunding.project.bean.Project;
import com.atguigu.atcrowdfunding.project.dao.ProjectDao;
import com.atguigu.atcrowdfunding.project.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectDao projectDao;

	public Project queryById(Integer id) {
		return projectDao.queryById(id);
	}

	public List<Project> queryHotProjects() {
		return projectDao.queryHotProjects();
	}

	public List<Project> queryAll() {
		// TODO Auto-generated method stub
		return projectDao.queryAll();
	}
}
