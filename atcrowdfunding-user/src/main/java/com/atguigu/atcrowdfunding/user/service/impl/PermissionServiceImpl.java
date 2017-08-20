package com.atguigu.atcrowdfunding.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.atcrowdfunding.user.bean.Permission;
import com.atguigu.atcrowdfunding.user.dao.PermissionDao;
import com.atguigu.atcrowdfunding.user.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionDao permissionDao;

	public List<Permission> queryRootPermissions() {
		return permissionDao.queryRootPermissions();
	}

	public List<Permission> queryChildrenPermissionsByPid(Integer id) {
		return permissionDao.queryChildrenPermissionsByPid(id);
	}

	public List<Permission> queryAll() {
		return permissionDao.queryAll();
	}

	public void insertPermission(Permission permission) {
		permissionDao.insertPermission(permission);
	}

	public Permission queryById(Integer id) {
		return permissionDao.queryById(id);
	}

	public int updatePermission(Permission permission) {
		return permissionDao.updatePermission(permission);
	}

	public int deletePermissionById(Integer id) {
		return permissionDao.deletePermissionById(id);
	}
}
