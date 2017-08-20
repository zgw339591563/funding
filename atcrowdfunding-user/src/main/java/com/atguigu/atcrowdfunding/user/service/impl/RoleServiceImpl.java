package com.atguigu.atcrowdfunding.user.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.atcrowdfunding.common.Datas;
import com.atguigu.atcrowdfunding.user.bean.Role;
import com.atguigu.atcrowdfunding.user.bean.RolePermission;
import com.atguigu.atcrowdfunding.user.dao.RoleDao;
import com.atguigu.atcrowdfunding.user.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;
	
	public Role queryRole(Map<String, Object> paramMap) {
		return roleDao.queryRole(paramMap);
	}

	public List<Role> pageQuery(Map<String, Object> paramMap) {
		return roleDao.pageQuery(paramMap);
	}

	public int queryCount(Map<String, Object> paramMap) {
		return roleDao.queryCount(paramMap);
	}

	public void insertRole(Role role) {
		roleDao.insertRole(role);
	}

	public Role queryById(Integer id) {
		return roleDao.queryById(id);
	}

	public int updateRole(Role role) {
		return roleDao.updateRole(role);
	}

	public int deleteRole(Integer id) {
		return roleDao.deleteRole(id);
	}

	public int deleteRoles(Datas ds) {
//		int i = 0;
//		for ( Role role : ds.getDatas() ) {
//			i = i + deleteRole(role.getId());
//		}
//		return i;
		return roleDao.deleteRoles(ds);
	}

	public List<Role> queryAll() {
		return roleDao.queryAll();
	}

	public List<Integer> queryRoleIdsByUserid(Integer id) {
		return roleDao.queryRoleIdsByUserid(id);
	}

	public int insertRolePermissions(Datas ds, Integer roleid) {
		
		// 删除当前角色所有旧的权限信息
		roleDao.deleteRolePermissionsByRoleid(roleid);
		
		int i = 0;
		for ( Integer permissionid : ds.getIds() ) {
			RolePermission rp = new RolePermission();
			rp.setPermissionid(permissionid);
			rp.setRoleid(roleid);
			i = i + roleDao.insertRolePermission(rp);
		}
		return i;
	}

	public List<Integer> queryPermissionIdsByRoleid(Integer roleid) {
		return roleDao.queryPermissionIdsByRoleid(roleid);
	}

}
