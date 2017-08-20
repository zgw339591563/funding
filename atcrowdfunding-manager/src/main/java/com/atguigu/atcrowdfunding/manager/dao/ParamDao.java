package com.atguigu.atcrowdfunding.manager.dao;

import java.util.List;
import java.util.Map;

import com.atguigu.atcrowdfunding.common.Datas;
import com.atguigu.atcrowdfunding.manager.bean.Param;

public interface ParamDao {

	Param queryParam(Map<String, Object> paramMap);

	List<Param> pageQuery(Map<String, Object> paramMap);

	int queryCount(Map<String, Object> paramMap);

	void insertParam(Param param);

	Param queryById(Integer id);

	int updateParam(Param param);

	int deleteParam(Integer id);

	int deleteParams(Datas ds);
}
