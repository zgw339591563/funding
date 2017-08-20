package com.atguigu.act.test;

import java.util.List;

import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import com.atguigu.atcrowdfunding.util.ActUtil;

public class TestAct09 {

	public static void main(String[] args) {
		// 将任务分配给小组，由小组内自己分配给个人
		
		ActUtil.deploy("MyProcess4.bpmn");
		
		ProcessInstance pi = ActUtil.startProcessInstanceByKey("myProcess");
		
		// 查询任务
		TaskService taskService = 
			ActUtil.getProcessEngine().getTaskService();
		
		List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("manager").list();
		
		// 分配任务（领取）
		for ( Task task : tasks ) {
			taskService.claim(task.getId(), "tianqi");
		}
		
		// 查询任务
		long count = taskService.createTaskQuery().taskAssignee("tianqi").count();
		
		System.out.println( "tianqi的任务数量 = " + count );
	}

}
