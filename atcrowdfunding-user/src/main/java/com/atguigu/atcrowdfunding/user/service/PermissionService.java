package com.atguigu.atcrowdfunding.user.service;

import java.util.List;

import com.atguigu.atcrowdfunding.user.bean.Permission;

public interface PermissionService {

	List<Permission> queryRootPermissions();

	List<Permission> queryChildrenPermissionsByPid(Integer id);

	List<Permission> queryAll();

	void insertPermission(Permission permission);

	Permission queryById(Integer id);

	int updatePermission(Permission permission);

	int deletePermissionById(Integer id);

}
