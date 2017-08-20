package com.atguigu.atcrowdfunding.manager.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.atguigu.atcrowdfunding.common.AJAXResult;
import com.atguigu.atcrowdfunding.common.Datas;
import com.atguigu.atcrowdfunding.common.Page;
import com.atguigu.atcrowdfunding.common.User;
import com.atguigu.atcrowdfunding.manager.bean.Advert;
import com.atguigu.atcrowdfunding.manager.service.AdvertService;
import com.atguigu.atcrowdfunding.util.StringUtil;

@Controller
@RequestMapping("/advert")
public class AdvertController {

	@Autowired
	private AdvertService advertService;
	

	@RequestMapping("/index")
	public String index() {
		return "advert/index";
	}
	
	@RequestMapping("/add")
	public String add() {
		return "advert/add";
	}
	
	@RequestMapping("/edit")
	public String edit( Integer id, Model model ) {
		
		// 根据主键查询广告信息
		Advert advert = advertService.queryById(id);
		model.addAttribute("advert", advert);
		
		return "advert/edit";
	}
	
	@ResponseBody
	@RequestMapping("/deletes")
	public Object deletes( Datas ds ) {
		AJAXResult result = new AJAXResult();
		
		try {
			int count = advertService.deleteAdverts(ds);
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
			int count = advertService.deleteAdvert(id);
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
	public Object update( Advert advert ) {
		AJAXResult result = new AJAXResult();
		
		try {
			int count = advertService.updateAdvert(advert);
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
	@RequestMapping("/deploy")
	public Object deploy( Advert advert ) {
		AJAXResult result = new AJAXResult();
		
		try {
			int count = advertService.deployAdvert(advert);
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
	@RequestMapping("/submit")
	public Object submit( Advert advert ) {
		AJAXResult result = new AJAXResult();
		
		try {
			int count = advertService.submitAdvert(advert);
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
	 * 新增广告数据
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/insert")
	public Object insert( HttpServletRequest req, HttpSession session, Advert advert ) {
		AJAXResult result = new AJAXResult();
		
		try {
			
			MultipartHttpServletRequest request =
				(MultipartHttpServletRequest)req;
			
			MultipartFile file = request.getFile("advPic");
			//System.out.println( "name = " + file.getName() );
			//System.out.println( "filename = " + file.getOriginalFilename() );
			
			// 保存图片
			String picsPath = session.getServletContext().getRealPath("pics");
			String realName = file.getOriginalFilename(); // xxxx.xxx.pic.png
			String fileName = UUID.randomUUID().toString() + realName.substring(realName.lastIndexOf("."));
			File destFile = new File(picsPath + "/adv/" + fileName);
			file.transferTo(destFile);
			
			User loginUser = (User)session.getAttribute("loginUser");
			
			//advert.setStatus("1");
			advert.setIconpath(fileName);
			advert.setUserid(loginUser.getId());
			advertService.insertAdvert(advert);
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
			List<Advert> adverts = advertService.pageQuery(paramMap);
			// 获取数据的总条数
			int count = advertService.queryCount(paramMap);
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
