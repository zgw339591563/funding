package com.atguigu.atcrowdfunding.controller;

import java.util.Iterator;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfunding.common.Datas;
import com.atguigu.atcrowdfunding.common.User;
import com.atguigu.atcrowdfunding.util.DesUtil;
import com.atguigu.atcrowdfunding.util.StringUtil;

@Controller
@RequestMapping("/test")
public class TestController {

	@ResponseBody
	@RequestMapping("/act")
	public Object act( String p ) throws Exception {
		
		String val = DesUtil.decrypt(p, "abcdefghijklmnopquvwxyz");
		
		return val;
	}
	
	/**
	 * 将表单中多条相同数据封装成后台的对象集合
	 * 
	 * 1. 增加包装类, 用于包装对象集合, 增加集合属性及set,get方法
	 * 2. 前台数据的传递方式要发生变化
	 *    loginacct=1&loginacct=2&loginacct=3
	 *    ==>
	 *    datas[0].loginacct=1&datas[1].loginacct=2&datas[2].loginacct=3
	 * @param user
	 * @return
	 */
	@RequestMapping("/insertUsers")
	public String insertUsers(Datas ds) {
		
		// 10
//		for ( int i = 0; i < ds.getDatas().size(); i++ ) {
//			User user = ds.getDatas().get(i);
//			if ( StringUtil.isEmpty(user.getLoginacct()) ) {
//				ds.getDatas().remove(i);
//			}
//		}
		
		// 集合的删除功能一般采用迭代器实现
		Iterator<User> iter = ds.getDatas().iterator();
		while ( iter.hasNext() ) {
			User user = iter.next();
			if ( StringUtil.isEmpty(user.getLoginacct()) ) {
				iter.remove();
			}
		}
		
		return "test";
	}
}
