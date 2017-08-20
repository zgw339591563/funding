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

import com.atguigu.atcrowdfunding.act.listener.NoListener;
import com.atguigu.atcrowdfunding.act.listener.YesListener;
import com.atguigu.atcrowdfunding.util.ActUtil;

public class TestAct15 {

	public static void main(String[] args) {
		
	    // 包含网关：多个逻辑分支根据条件的不同而执行不同的操作
		//        如果只有一个分支条件成立，那么等同于排他网关
		//        如果同时又多个分支条件成立，那么等同于并行网关
		
		ActUtil.deploy("MyProcess9.bpmn");
		
		Map<String, Object> varMap = new HashMap<String, Object>();
		varMap.put("yesListener", new YesListener());
		varMap.put("noListener", new NoListener());
		
		ProcessInstance pi =
			ActUtil.getProcessEngine()
		    .getRuntimeService()
		    .startProcessInstanceByKey("myProcess", varMap);
		
		TaskService taskService = ActUtil.getProcessEngine().getTaskService();
		
		List<Task> tasks = taskService.createTaskQuery().taskAssignee("zhangsan").list();
		
		System.out.println( "zhangsan的任务数量 = " + tasks.size() );
		
		for ( Task task : tasks ) {
			taskService.setVariable(task.getId(), "flg", false);
			taskService.complete(task.getId());
		}
		
		System.out.println( "流程结束" );
	}

}
