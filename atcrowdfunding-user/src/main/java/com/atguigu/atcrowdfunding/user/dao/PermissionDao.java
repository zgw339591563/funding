package com.atguigu.atcrowdfunding.user.dao;

import java.util.List;

import com.atguigu.atcrowdfunding.user.bean.Permission;

public interface PermissionDao {

	List<Permission> queryRootPermissions();

	List<Permission> queryChildrenPermissionsByPid(Integer id);

	List<Permission> queryAll();

	void insertPermission(Permission permission);

	Permission queryById(Integer id);

	int updatePermission(Permission permission);

	int deletePermissionById(Integer id);

}
