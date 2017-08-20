package com.atguigu.act.test;

import org.activiti.engine.HistoryService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;

import com.atguigu.atcrowdfunding.util.ActUtil;

public class TestAct07 {

	public static void main(String[] args) {
		
		// 查询历史纪录
		HistoryService historyService = 
			ActUtil.getProcessEngine().getHistoryService();
		
		HistoricProcessInstanceQuery query = 
			historyService.createHistoricProcessInstanceQuery();
		
		HistoricProcessInstance hpi = 
			query.processInstanceId("501").finished().singleResult();
		
		System.out.println( "流程实例 =" + hpi );
	}

}
