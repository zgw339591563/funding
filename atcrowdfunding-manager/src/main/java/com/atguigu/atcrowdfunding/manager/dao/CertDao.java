package com.atguigu.atcrowdfunding.manager.dao;

import java.util.List;
import java.util.Map;

import com.atguigu.atcrowdfunding.common.Datas;
import com.atguigu.atcrowdfunding.manager.bean.Cert;

public interface CertDao {

	Cert queryCert(Map<String, Object> paramMap);

	List<Cert> pageQuery(Map<String, Object> paramMap);

	int queryCount(Map<String, Object> paramMap);

	void insertCert(Cert cert);

	Cert queryById(Integer id);

	int updateCert(Cert cert);

	int deleteCert(Integer id);

	int deleteCerts(Datas ds);

	List<Cert> queryAll();

	void insertAcctTypeCert(Map<String, Object> paramMap);

	void deleteAcctTypeCert(Map<String, Object> paramMap);

	List<Map<String, Object>> queryAcctTypeCerts();
}
