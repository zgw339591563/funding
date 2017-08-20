package com.atguigu.act.test;

import java.util.List;

import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;

import com.atguigu.atcrowdfunding.util.ActUtil;

public class TestAct06 {

	public static void main(String[] args) {
		
		// 给任务添加委托人
		ActUtil.deploy("MyProcess2.bpmn");
		
		ProcessInstance pi = ActUtil.startProcessInstanceByKey("myProcess");
		
		// 查询任务
		TaskService taskService = ActUtil.getProcessEngine().getTaskService();
		
		TaskQuery taskQuery = taskService.createTaskQuery();
		
		List<Task> tasks = taskQuery.taskAssignee("zhangsan").list();
		List<Task> tasks1 = taskQuery.taskAssignee("lisi").list();
		
		System.out.println( "zhangsan的任务数量 = " + tasks.size() );
		System.out.println( "lisi的任务数量 = " + tasks1.size() );
		
		// 完成任务
		for ( Task task : tasks ) {
			System.out.println( "zhangsan完成了任务：" + task.getName() );
			taskService.complete(task.getId());
		}
		
		tasks = taskQuery.taskAssignee("zhangsan").list();
		tasks1 = taskQuery.taskAssignee("lisi").list();
		
		System.out.println( "zhangsan的任务数量 = " + tasks.size() );
		System.out.println( "lisi的任务数量 = " + tasks1.size() );
		
//		for ( Task task : tasks1 ) {
//			System.out.println( "lisi完成了任务：" + task.getName() );
//			taskService.complete(task.getId());
//		}
//		
//		tasks = taskQuery.taskAssignee("zhangsan").list();
//		tasks1 = taskQuery.taskAssignee("lisi").list();
//		
//		System.out.println( "zhangsan的任务数量 = " + tasks.size() );
//		System.out.println( "lisi的任务数量 = " + tasks1.size() );
	}
}
