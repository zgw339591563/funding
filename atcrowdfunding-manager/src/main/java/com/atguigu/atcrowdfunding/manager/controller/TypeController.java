package com.atguigu.atcrowdfunding.manager.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfunding.common.AJAXResult;
import com.atguigu.atcrowdfunding.manager.bean.Cert;
import com.atguigu.atcrowdfunding.manager.service.CertService;

@Controller
@RequestMapping("/type")
public class TypeController {

	@Autowired
	private CertService certService;
	
	@RequestMapping("/index")
	public String index( Model model ) {
		
		// 读取资质数据集合
		List<Cert> certs = certService.queryAll();
		model.addAttribute("certs", certs);
		
		// 获取已经建立好的关系数据
		List<Map<String, Object>> maps = certService.queryAcctTypeCerts();
		model.addAttribute("maps", maps);
		
		return "type/index";
	}
	
	
	@ResponseBody
	@RequestMapping("/deleteAcctTypeCert")
	public Object deleteAcctTypeCert( Integer certid, String accttype ) {
		AJAXResult result = new AJAXResult();
		
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("certid", certid);
			paramMap.put("accttype", accttype);
			
			certService.deleteAcctTypeCert(paramMap);
			result.setSuccess(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/insertAcctTypeCert")
	public Object insertAcctTypeCert( Integer certid, String accttype ) {
		AJAXResult result = new AJAXResult();
		
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("certid", certid);
			paramMap.put("accttype", accttype);
			
			certService.insertAcctTypeCert(paramMap);
			result.setSuccess(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}
	
	
	
	
	
	
	
	
	
	
}
