package com.atguigu.atcrowdfunding.project.dao;

import java.util.List;

import com.atguigu.atcrowdfunding.project.bean.Project;

public interface ProjectDao {

	Project queryById(Integer id);

	List<Project> queryHotProjects();

	List<Project> queryAll();

}
