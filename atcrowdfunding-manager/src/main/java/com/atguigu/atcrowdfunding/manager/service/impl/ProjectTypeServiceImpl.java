package com.atguigu.atcrowdfunding.manager.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.atcrowdfunding.common.Datas;
import com.atguigu.atcrowdfunding.manager.bean.ProjectType;
import com.atguigu.atcrowdfunding.manager.dao.ProjectTypeDao;
import com.atguigu.atcrowdfunding.manager.service.ProjectTypeService;

@Service
public class ProjectTypeServiceImpl implements ProjectTypeService {

	@Autowired
	private ProjectTypeDao projectTypeDao;
	

	public ProjectType queryProjectType(Map<String, Object> paramMap) {
		return projectTypeDao.queryProjectType(paramMap);
	}

	public List<ProjectType> pageQuery(Map<String, Object> paramMap) {
		return projectTypeDao.pageQuery(paramMap);
	}

	public int queryCount(Map<String, Object> paramMap) {
		return projectTypeDao.queryCount(paramMap);
	}

	public void insertProjectType(ProjectType projectType) {
		projectTypeDao.insertProjectType(projectType);
	}

	public ProjectType queryById(Integer id) {
		return projectTypeDao.queryById(id);
	}

	public int updateProjectType(ProjectType projectType) {
		return projectTypeDao.updateProjectType(projectType);
	}

	public int deleteProjectType(Integer id) {
		return projectTypeDao.deleteProjectType(id);
	}

	public int deleteProjectTypes(Datas ds) {
		return projectTypeDao.deleteProjectTypes(ds);
	}

	public List<ProjectType> queryAll() {
		return projectTypeDao.queryAll();
	}

	public List<Map<String, Object>> queryProject4Type() {
		return projectTypeDao.queryProject4Type();
	}
}
