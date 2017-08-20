package com.atguigu.act.test;

import com.atguigu.atcrowdfunding.util.ActUtil;

public class TestAct14 {

	public static void main(String[] args) {
		
		ActUtil.deploy("MyProcess8.bpmn");
		
		ActUtil.startProcessInstanceByKey("myProcess");
		
		System.out.println("邮件发送成功");
	}

}
