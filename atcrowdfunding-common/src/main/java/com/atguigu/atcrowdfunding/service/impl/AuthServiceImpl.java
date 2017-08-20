package com.atguigu.atcrowdfunding.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.atcrowdfunding.common.Ticket;
import com.atguigu.atcrowdfunding.dao.AuthDao;
import com.atguigu.atcrowdfunding.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private AuthDao authDao;

	public void passAuth(Integer memberid) {
		authDao.passAuth(memberid);
	}

	public Ticket queryTicketByMemberid(Integer memberid) {
		return authDao.queryTicketByMemberid(memberid);
	}

	public void updateTicketStatus(Ticket ticket) {
		authDao.updateTicketStatus(ticket);
	}

	public void refuseAuth(Integer memberid) {
		authDao.refuseAuth(memberid);
	}
}
