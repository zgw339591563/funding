package com.atguigu.atcrowdfunding.user.dao;

import java.util.List;
import java.util.Map;

import com.atguigu.atcrowdfunding.common.Datas;
import com.atguigu.atcrowdfunding.user.bean.Role;
import com.atguigu.atcrowdfunding.user.bean.RolePermission;

public interface RoleDao {

	Role queryRole(Map<String, Object> paramMap);

	List<Role> pageQuery(Map<String, Object> paramMap);

	int queryCount(Map<String, Object> paramMap);

	void insertRole(Role role);

	Role queryById(Integer id);

	int updateRole(Role role);

	int deleteRole(Integer id);

	int deleteRoles(Datas ds);

	List<Role> queryAll();

	List<Integer> queryRoleIdsByUserid(Integer id);

	int insertRolePermission(RolePermission rp);

	void deleteRolePermissionsByRoleid(Integer roleid);

	List<Integer> queryPermissionIdsByRoleid(Integer roleid);

}
