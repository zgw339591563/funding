package com.atguigu.atcrowdfunding.manager.service;

import java.util.List;
import java.util.Map;

import com.atguigu.atcrowdfunding.common.Datas;
import com.atguigu.atcrowdfunding.manager.bean.Advert;

public interface AdvertService {
	public Advert queryAdvert(Map<String, Object> paramMap);

	public List<Advert> pageQuery(Map<String, Object> paramMap);

	public int queryCount(Map<String, Object> paramMap);

	public void insertAdvert(Advert advert);

	public Advert queryById(Integer id);

	public int updateAdvert(Advert advert);

	public int deleteAdvert(Integer id);

	public int deleteAdverts(Datas ds);

	public int deployAdvert(Advert advert);

	public int submitAdvert(Advert advert);

	public List<Advert> pageQuery4Auth(Map<String, Object> paramMap);

	public int queryCount4Auth(Map<String, Object> paramMap);

	public void passAuthById(Integer id);

	public List<Advert> queryAllDeploys();
}
