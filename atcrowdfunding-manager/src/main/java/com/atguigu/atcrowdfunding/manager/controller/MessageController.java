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
import com.atguigu.atcrowdfunding.manager.bean.Message;
import com.atguigu.atcrowdfunding.manager.service.MessageService;
import com.atguigu.atcrowdfunding.util.StringUtil;

@Controller
@RequestMapping("/message")
public class MessageController {

	@Autowired
	private MessageService messageService;
	

	@RequestMapping("/index")
	public String index() {
		return "message/index";
	}
	
	@RequestMapping("/add")
	public String add() {
		return "message/add";
	}
	
	@RequestMapping("/edit")
	public String edit( Integer id, Model model ) {
		
		// 根据主键查询消息信息
		Message message = messageService.queryById(id);
		model.addAttribute("message", message);
		
		return "message/edit";
	}
	
	@ResponseBody
	@RequestMapping("/deletes")
	public Object deletes( Datas ds ) {
		AJAXResult result = new AJAXResult();
		
		try {
			int count = messageService.deleteMessages(ds);
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
			int count = messageService.deleteMessage(id);
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
	public Object update( Message message ) {
		AJAXResult result = new AJAXResult();
		
		try {
			int count = messageService.updateMessage(message);
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
	 * 新增消息数据
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/insert")
	public Object insert( Message message ) {
		AJAXResult result = new AJAXResult();
		
		try {
			messageService.insertMessage(message);
			result.setSuccess(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}
	
	/**
	 * 分页查询消息数据
	 * @param pageno
	 * @param pagesize
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/pageQuery")
	public Object pageQuery(String pagetext, Integer pageno, Integer pagesize) {
		AJAXResult result = new AJAXResult();
		
		try {
			// 查询消息数据
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("start", (pageno-1)*pagesize);
			paramMap.put("size", pagesize);
			if ( StringUtil.isNotEmpty(pagetext) ) {
				//pagetext = pagetext.replaceAll("%", "\\%");
			}
			paramMap.put("pagetext", pagetext);
			
			// 分页查询数据
			List<Message> messages = messageService.pageQuery(paramMap);
			// 获取数据的总条数
			int count = messageService.queryCount(paramMap);
			int totalno = 0;
			// 获取总页码
			if ( count % pagesize == 0) {
				totalno = count / pagesize;
			} else {
				totalno = count / pagesize + 1;
			}
			
			Page<Message> messagePage = new Page<Message>();
			messagePage.setDatas(messages);
			messagePage.setTotalno(totalno);
			messagePage.setTotalsize(count);
			messagePage.setPageno(pageno);
			messagePage.setPagesize(pagesize);
			result.setPageObj(messagePage);
			result.setSuccess(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}
}
