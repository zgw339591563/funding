package com.atguigu.atcrowdfunding.manager.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.atcrowdfunding.common.Datas;
import com.atguigu.atcrowdfunding.manager.bean.Advert;
import com.atguigu.atcrowdfunding.manager.dao.AdvertDao;
import com.atguigu.atcrowdfunding.manager.service.AdvertService;

@Service
public class AdvertServiceImpl implements AdvertService {

	@Autowired
	private AdvertDao advertDao;

	public Advert queryAdvert(Map<String, Object> paramMap) {
		return advertDao.queryAdvert(paramMap);
	}

	public List<Advert> pageQuery(Map<String, Object> paramMap) {
		return advertDao.pageQuery(paramMap);
	}

	public int queryCount(Map<String, Object> paramMap) {
		return advertDao.queryCount(paramMap);
	}

	public void insertAdvert(Advert advert) {
		advertDao.insertAdvert(advert);
	}

	public Advert queryById(Integer id) {
		return advertDao.queryById(id);
	}

	public int updateAdvert(Advert advert) {
		return advertDao.updateAdvert(advert);
	}

	public int deleteAdvert(Integer id) {
		return advertDao.deleteAdvert(id);
	}

	public int deleteAdverts(Datas ds) {
		return advertDao.deleteAdverts(ds);
	}

	public int deployAdvert(Advert advert) {
		return advertDao.deployAdvert(advert);
	}

	public int submitAdvert(Advert advert) {
		return advertDao.submitAdvert(advert);
	}

	public List<Advert> pageQuery4Auth(Map<String, Object> paramMap) {
		return advertDao.pageQuery4Auth(paramMap);
	}

	public int queryCount4Auth(Map<String, Object> paramMap) {
		return advertDao.queryCount4Auth(paramMap);
	}

	public void passAuthById(Integer id) {
		advertDao.passAuthById(id);
	}

	public List<Advert> queryAllDeploys() {
		return advertDao.queryAllDeploys();
	}

}
