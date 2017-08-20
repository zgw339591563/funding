package com.atguigu.atcrowdfunding.manager.service;

import java.util.List;
import java.util.Map;

import com.atguigu.atcrowdfunding.common.Datas;
import com.atguigu.atcrowdfunding.manager.bean.Param;

public interface ParamService {
	public Param queryParam(Map<String, Object> paramMap);

	public List<Param> pageQuery(Map<String, Object> paramMap);

	public int queryCount(Map<String, Object> paramMap);

	public void insertParam(Param param);

	public Param queryById(Integer id);

	public int updateParam(Param param);

	public int deleteParam(Integer id);

	public int deleteParams(Datas ds);
}
