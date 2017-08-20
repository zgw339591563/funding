package com.atguigu.atcrowdfunding.manager.dao;

import java.util.List;
import java.util.Map;

import com.atguigu.atcrowdfunding.common.Datas;
import com.atguigu.atcrowdfunding.manager.bean.Advert;

public interface AdvertDao {

	Advert queryAdvert(Map<String, Object> paramMap);

	List<Advert> pageQuery(Map<String, Object> paramMap);

	int queryCount(Map<String, Object> paramMap);

	void insertAdvert(Advert advert);

	Advert queryById(Integer id);

	int updateAdvert(Advert advert);

	int deleteAdvert(Integer id);

	int deleteAdverts(Datas ds);

	int deployAdvert(Advert advert);

	int submitAdvert(Advert advert);

	List<Advert> pageQuery4Auth(Map<String, Object> paramMap);

	int queryCount4Auth(Map<String, Object> paramMap);

	void passAuthById(Integer id);

	List<Advert> queryAllDeploys();
}
