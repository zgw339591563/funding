package com.atguigu.atcrowdfunding.user.service;

import java.util.List;
import java.util.Map;

import com.atguigu.atcrowdfunding.common.Datas;
import com.atguigu.atcrowdfunding.common.User;
import com.atguigu.atcrowdfunding.user.bean.Permission;

public interface UserService {
	public User queryUser(Map<String, Object> paramMap);

	public List<User> pageQuery(Map<String, Object> paramMap);

	public int queryCount(Map<String, Object> paramMap);

	public void insertUser(User user);

	public User queryById(Integer id);

	public int updateUser(User user);

	public int deleteUser(Integer id);

	public int deleteUsers(Datas ds);

	public int insertUserRoles(Integer userid, Datas ds);

	public int deleteUserRoles(Integer userid, Datas ds);

	public List<Permission> queryAuthPermissions(Integer userid);

}
