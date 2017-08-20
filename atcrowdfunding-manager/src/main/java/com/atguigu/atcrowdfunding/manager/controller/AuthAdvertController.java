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
import com.atguigu.atcrowdfunding.common.Page;
import com.atguigu.atcrowdfunding.manager.bean.Advert;
import com.atguigu.atcrowdfunding.manager.service.AdvertService;
import com.atguigu.atcrowdfunding.util.StringUtil;

@Controller
@RequestMapping("/auth_advert")
public class AuthAdvertController {

	@Autowired
	private AdvertService advertService;
	
	@RequestMapping("/index")
	public String index() {
		return "auth/advert";
	}
	
	@RequestMapping("/show")
	public String show( Integer id, Model model ) {
		
		Advert advert = advertService.queryById(id);
		model.addAttribute("advert", advert);
		
		return "auth/advert-show";
	}
	
	@ResponseBody
	@RequestMapping("/ok")
	public Object ok( Integer id ) {
		AJAXResult result = new AJAXResult();
		try {
			advertService.passAuthById(id);
			result.setSuccess(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}
	
	/**
	 * 分页查询广告数据
	 * @param pageno
	 * @param pagesize
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/pageQuery")
	public Object pageQuery(String pagetext, Integer pageno, Integer pagesize) {
		AJAXResult result = new AJAXResult();
		
		try {
			// 查询广告数据
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("start", (pageno-1)*pagesize);
			paramMap.put("size", pagesize);
			if ( StringUtil.isNotEmpty(pagetext) ) {
				//pagetext = pagetext.replaceAll("%", "\\%");
			}
			paramMap.put("pagetext", pagetext);
			
			// 分页查询数据
			List<Advert> adverts = advertService.pageQuery4Auth(paramMap);
			// 获取数据的总条数
			int count = advertService.queryCount4Auth(paramMap);
			int totalno = 0;
			// 获取总页码
			if ( count % pagesize == 0) {
				totalno = count / pagesize;
			} else {
				totalno = count / pagesize + 1;
			}
			
			Page<Advert> advertPage = new Page<Advert>();
			advertPage.setDatas(adverts);
			advertPage.setTotalno(totalno);
			advertPage.setTotalsize(count);
			advertPage.setPageno(pageno);
			advertPage.setPagesize(pagesize);
			result.setPageObj(advertPage);
			result.setSuccess(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}
}
