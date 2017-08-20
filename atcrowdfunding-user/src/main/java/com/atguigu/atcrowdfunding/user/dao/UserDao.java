package com.atguigu.atcrowdfunding.user.dao;

import java.util.List;
import java.util.Map;

import com.atguigu.atcrowdfunding.common.Datas;
import com.atguigu.atcrowdfunding.common.User;
import com.atguigu.atcrowdfunding.user.bean.Permission;
import com.atguigu.atcrowdfunding.user.bean.UserRole;

public interface UserDao {

	User queryUser(Map<String, Object> paramMap);

	List<User> pageQuery(Map<String, Object> paramMap);

	int queryCount(Map<String, Object> paramMap);

	void insertUser(User user);

	User queryById(Integer id);

	int updateUser(User user);

	int deleteUser(Integer id);

	int deleteUsers(Datas ds);

	int insertUserRole(UserRole ur);

	int deleteUserRoles(Map<String, Object> paramMap);

	List<Permission> queryAuthPermissions(Integer userid);

}
