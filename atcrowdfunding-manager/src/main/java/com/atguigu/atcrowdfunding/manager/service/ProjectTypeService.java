package com.atguigu.atcrowdfunding.manager.service;

import java.util.List;
import java.util.Map;

import com.atguigu.atcrowdfunding.common.Datas;
import com.atguigu.atcrowdfunding.manager.bean.ProjectType;

public interface ProjectTypeService {
	public ProjectType queryProjectType(Map<String, Object> paramMap);

	public List<ProjectType> pageQuery(Map<String, Object> paramMap);

	public int queryCount(Map<String, Object> paramMap);

	public void insertProjectType(ProjectType projectType);

	public ProjectType queryById(Integer id);

	public int updateProjectType(ProjectType projectType);

	public int deleteProjectType(Integer id);

	public int deleteProjectTypes(Datas ds);

	public List<ProjectType> queryAll();

	public List<Map<String, Object>> queryProject4Type();
}
