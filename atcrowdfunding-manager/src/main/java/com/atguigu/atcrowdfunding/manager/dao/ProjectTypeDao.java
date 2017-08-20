package com.atguigu.atcrowdfunding.manager.dao;

import java.util.List;
import java.util.Map;

import com.atguigu.atcrowdfunding.common.Datas;
import com.atguigu.atcrowdfunding.manager.bean.ProjectType;

public interface ProjectTypeDao {
	ProjectType queryProjectType(Map<String, Object> paramMap);

	List<ProjectType> pageQuery(Map<String, Object> paramMap);

	int queryCount(Map<String, Object> paramMap);

	void insertProjectType(ProjectType projectType);

	ProjectType queryById(Integer id);

	int updateProjectType(ProjectType projectType);

	int deleteProjectType(Integer id);

	int deleteProjectTypes(Datas ds);

	List<ProjectType> queryAll();

	List<Map<String, Object>> queryProject4Type();
}
