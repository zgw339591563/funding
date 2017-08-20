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
import com.atguigu.atcrowdfunding.common.Datas;
import com.atguigu.atcrowdfunding.common.Page;
import com.atguigu.atcrowdfunding.manager.bean.Param;
import com.atguigu.atcrowdfunding.manager.service.ParamService;
import com.atguigu.atcrowdfunding.util.StringUtil;

@Controller
@RequestMapping("/param")
public class ParamController {

	@Autowired
	private ParamService paramService;
	

	@RequestMapping("/index")
	public String index() {
		return "param/index";
	}
	
	@RequestMapping("/add")
	public String add() {
		return "param/add";
	}
	
	@RequestMapping("/edit")
	public String edit( Integer id, Model model ) {
		
		// 根据主键查询资质信息
		Param param = paramService.queryById(id);
		model.addAttribute("paramObj", param);
		
		return "param/edit";
	}
	
	@ResponseBody
	@RequestMapping("/deletes")
	public Object deletes( Datas ds ) {
		AJAXResult result = new AJAXResult();
		
		try {
			int count = paramService.deleteParams(ds);
			if ( count == ds.getDatas().size() ) {
				result.setSuccess(true);
			} else {
				result.setSuccess(false);
			}
		} catch ( Exception e ) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public Object delete( Integer id ) {
		AJAXResult result = new AJAXResult();
		
		try {
			int count = paramService.deleteParam(id);
			if ( count == 1 ) {
				result.setSuccess(true);
			} else {
				result.setSuccess(false);
			}
		} catch ( Exception e ) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/update")
	public Object update( Param param ) {
		AJAXResult result = new AJAXResult();
		
		try {
			int count = paramService.updateParam(param);
			if ( count == 1 ) {
				result.setSuccess(true);
			} else {
				result.setSuccess(false);
			}
		} catch ( Exception e ) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}
	
	/**
	 * 新增资质数据
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/insert")
	public Object insert( Param param ) {
		AJAXResult result = new AJAXResult();
		
		try {
			paramService.insertParam(param);
			result.setSuccess(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}
	
	/**
	 * 分页查询资质数据
	 * @param pageno
	 * @param pagesize
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/pageQuery")
	public Object pageQuery(String pagetext, Integer pageno, Integer pagesize) {
		AJAXResult result = new AJAXResult();
		
		try {
			// 查询资质数据
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("start", (pageno-1)*pagesize);
			paramMap.put("size", pagesize);
			if ( StringUtil.isNotEmpty(pagetext) ) {
				//pagetext = pagetext.replaceAll("%", "\\%");
			}
			paramMap.put("pagetext", pagetext);
			
			// 分页查询数据
			List<Param> params = paramService.pageQuery(paramMap);
			// 获取数据的总条数
			int count = paramService.queryCount(paramMap);
			int totalno = 0;
			// 获取总页码
			if ( count % pagesize == 0) {
				totalno = count / pagesize;
			} else {
				totalno = count / pagesize + 1;
			}
			
			Page<Param> paramPage = new Page<Param>();
			paramPage.setDatas(params);
			paramPage.setTotalno(totalno);
			paramPage.setTotalsize(count);
			paramPage.setPageno(pageno);
			paramPage.setPagesize(pagesize);
			result.setPageObj(paramPage);
			result.setSuccess(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}
}
