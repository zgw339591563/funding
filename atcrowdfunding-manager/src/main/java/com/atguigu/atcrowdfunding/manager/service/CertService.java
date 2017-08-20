package com.atguigu.atcrowdfunding.manager.service;

import java.util.List;
import java.util.Map;

import com.atguigu.atcrowdfunding.common.Datas;
import com.atguigu.atcrowdfunding.manager.bean.Cert;

public interface CertService {
	public Cert queryCert(Map<String, Object> paramMap);

	public List<Cert> pageQuery(Map<String, Object> paramMap);

	public int queryCount(Map<String, Object> paramMap);

	public void insertCert(Cert cert);

	public Cert queryById(Integer id);

	public int updateCert(Cert cert);

	public int deleteCert(Integer id);

	public int deleteCerts(Datas ds);

	public List<Cert> queryAll();

	public void insertAcctTypeCert(Map<String, Object> paramMap);

	public void deleteAcctTypeCert(Map<String, Object> paramMap);

	public List<Map<String, Object>> queryAcctTypeCerts();
}
