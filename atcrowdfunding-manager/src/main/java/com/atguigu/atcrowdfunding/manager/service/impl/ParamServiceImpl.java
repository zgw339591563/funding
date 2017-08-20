package com.atguigu.atcrowdfunding.manager.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.atcrowdfunding.common.Datas;
import com.atguigu.atcrowdfunding.manager.bean.Param;
import com.atguigu.atcrowdfunding.manager.dao.ParamDao;
import com.atguigu.atcrowdfunding.manager.service.ParamService;

@Service
public class ParamServiceImpl implements ParamService {

	@Autowired
	private ParamDao paramDao;

	public Param queryParam(Map<String, Object> paramMap) {
		return paramDao.queryParam(paramMap);
	}

	public List<Param> pageQuery(Map<String, Object> paramMap) {
		return paramDao.pageQuery(paramMap);
	}

	public int queryCount(Map<String, Object> paramMap) {
		return paramDao.queryCount(paramMap);
	}

	public void insertParam(Param param) {
		paramDao.insertParam(param);
	}

	public Param queryById(Integer id) {
		return paramDao.queryById(id);
	}

	public int updateParam(Param param) {
		return paramDao.updateParam(param);
	}

	public int deleteParam(Integer id) {
		return paramDao.deleteParam(id);
	}

	public int deleteParams(Datas ds) {
		return paramDao.deleteParams(ds);
	}

}
