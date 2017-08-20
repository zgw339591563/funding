package com.atguigu.act.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import com.atguigu.atcrowdfunding.util.ActUtil;

public class TestAct10 {

	public static void main(String[] args) {
		// 网关： 所谓的网关其实就是逻辑分支判断
		
		// 排他网关：多个逻辑分支同时只能执行一个分支，分支执行完毕后，流程结束
		
		// 决策
		ActUtil.deploy("MyProcess5.bpmn");
		
		Map<String, Object> varMap = new HashMap<String, Object>();
		varMap.put("days", 5);
		
		ProcessInstance pi =
			ActUtil.getProcessEngine()
		    .getRuntimeService()
		    .startProcessInstanceByKey("myProcess", varMap);
		
		// 查询任务
		TaskService taskService = ActUtil.getProcessEngine().getTaskService();
		
		List<Task> tasks = taskService.createTaskQuery().taskAssignee("zhangsan").list();
		List<Task> tasks1 = taskService.createTaskQuery().taskAssignee("lisi").list();
		
		System.out.println( "zhangsan的任务数量 = " + tasks.size() );
		System.out.println( "lisi的任务数量 = " + tasks1.size() );
		
		for ( Task task : tasks ) {
			System.out.println( "zhangsan完成了任务 = " + task.getName() );
			taskService.complete(task.getId());
		}
		
		// 判断流程是否结束
		HistoryService historyService =
			ActUtil.getProcessEngine().getHistoryService();
		
		HistoricProcessInstanceQuery query =
			historyService.createHistoricProcessInstanceQuery();
		
		HistoricProcessInstance hpi =
			query.processInstanceId(pi.getId()).finished().singleResult();
		
		System.out.println( "流程" + pi.getId() + "是否结束 : " + (hpi != null) );
		
		tasks = taskService.createTaskQuery().taskAssignee("zhangsan").list();
		tasks1 = taskService.createTaskQuery().taskAssignee("lisi").list();
		
		System.out.println( "zhangsan的任务数量 = " + tasks.size() );
		System.out.println( "lisi的任务数量 = " + tasks1.size() );
		
		for ( Task task : tasks1 ) {
			System.out.println( "lisi完成了任务 = " + task.getName() );
			taskService.complete(task.getId());
		}
		
		historyService =
			ActUtil.getProcessEngine().getHistoryService();
		
		query =
			historyService.createHistoricProcessInstanceQuery();
		
		hpi =
			query.processInstanceId(pi.getId()).finished().singleResult();
		
		System.out.println( "流程" + pi.getId() + "是否结束 : " + (hpi != null) );
	}

}
