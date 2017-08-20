package com.atguigu.act.test;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.runtime.ProcessInstance;

import com.atguigu.atcrowdfunding.util.ActUtil;

public class TestAct08 {

	public static void main(String[] args) {
		
		// 流程中使用流程变量，让流程更具备扩展性
		ActUtil.deploy("MyProcess3.bpmn");
		
		// 启动流程
		//ProcessInstance pi = ActUtil.startProcessInstanceByKey("myProcess");
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("TL", "wangwu");
		paramMap.put("PM", "zhaoliu");
		
		ProcessInstance pi =
			ActUtil.getProcessEngine()
			       .getRuntimeService()
			       .startProcessInstanceByKey("myProcess", paramMap);
	}

}
