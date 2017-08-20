package com.atguigu.atcrowdfunding.util;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ActUtil {
	
	public static ProcessEngine getProcessEngine() {
		
		ApplicationContext application =
				new ClassPathXmlApplicationContext("spring/spring-*.xml");

		ProcessEngine processEngine =
				(ProcessEngine)application.getBean("processEngine");
		
		return processEngine;
	}
	
	public static void deploy( String procDefFileName ) {
		DeploymentBuilder builer =
				getProcessEngine().getRepositoryService().createDeployment();
			
		Deployment deployment =
			builer.addClasspathResource(procDefFileName).deploy();
	}
	
	public static ProcessInstance startProcessInstanceById( String procDefId ) {
		ProcessInstance pi =
				getProcessEngine().getRuntimeService().startProcessInstanceById(procDefId);
		return pi;
	}
	
	public static ProcessInstance startProcessInstanceByKey( String procDefKey ) {
		
		ProcessDefinitionQuery query =
				getProcessEngine().getRepositoryService().createProcessDefinitionQuery();
		ProcessDefinition processDefinition =
				query.processDefinitionKey(procDefKey).latestVersion().singleResult();
		
		return startProcessInstanceById(processDefinition.getId());
	}
}
