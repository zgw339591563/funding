package com.atguigu.atcrowdfunding.common;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.springframework.context.ApplicationContext;

import com.atguigu.atcrowdfunding.service.AuthService;
import com.atguigu.atcrowdfunding.util.ApplicationContextUtils;

public class AuthPassListener implements ExecutionListener {

	public void notify(DelegateExecution execution) throws Exception {
		// 审批通过
		Integer memberid = (Integer)execution.getVariable("memberid");
		
		// 获取Spring容器
		ApplicationContext context = ApplicationContextUtils.applicationContext;
		
		// 获取service
		AuthService authService = context.getBean(AuthService.class);
		// 改变会员的状态
		authService.passAuth(memberid);
		// TicketID
		Ticket ticket = authService.queryTicketByMemberid(memberid);
		// 改变流程审批单的状态
		ticket.setStatus("1");
		authService.updateTicketStatus(ticket);
	}

}
