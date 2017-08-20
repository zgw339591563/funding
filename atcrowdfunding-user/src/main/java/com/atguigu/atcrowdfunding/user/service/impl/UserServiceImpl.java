package com.atguigu.atcrowdfunding.user.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.atcrowdfunding.common.Datas;
import com.atguigu.atcrowdfunding.common.User;
import com.atguigu.atcrowdfunding.user.bean.Permission;
import com.atguigu.atcrowdfunding.user.bean.UserRole;
import com.atguigu.atcrowdfunding.user.dao.UserDao;
import com.atguigu.atcrowdfunding.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	public User queryUser(Map<String, Object> paramMap) {
		return userDao.queryUser(paramMap);
	}

	public List<User> pageQuery(Map<String, Object> paramMap) {
		return userDao.pageQuery(paramMap);
	}

	public int queryCount(Map<String, Object> paramMap) {
		return userDao.queryCount(paramMap);
	}

	public void insertUser(User user) {
		userDao.insertUser(user);
	}

	public User queryById(Integer id) {
		return userDao.queryById(id);
	}

	public int updateUser(User user) {
		return userDao.updateUser(user);
	}

	public int deleteUser(Integer id) {
		return userDao.deleteUser(id);
	}

	public int deleteUsers(Datas ds) {
//		int i = 0;
//		for ( User user : ds.getDatas() ) {
//			i = i + deleteUser(user.getId());
//		}
//		return i;
		return userDao.deleteUsers(ds);
	}

	public int insertUserRoles(Integer userid, Datas ds) {
		int count = 0;
		for ( Integer roleid : ds.getIds() ) {
			UserRole ur = new UserRole();
			ur.setRoleid(roleid);
			ur.setUserid(userid);
			count = count + userDao.insertUserRole(ur);
		}
		return count;
	}

	public int deleteUserRoles(Integer userid, Datas ds) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("userid", userid);
		paramMap.put("roleids", ds.getIds());
		
		return userDao.deleteUserRoles(paramMap);
	}

	public List<Permission> queryAuthPermissions( Integer userid ) {
		return userDao.queryAuthPermissions(userid);
	}

}
