package com.atguigu.atcrowdfunding.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.atcrowdfunding.common.CertImg;
import com.atguigu.atcrowdfunding.common.Member;
import com.atguigu.atcrowdfunding.common.Ticket;
import com.atguigu.atcrowdfunding.manager.dao.AuthCertDao;
import com.atguigu.atcrowdfunding.manager.service.AuthCertService;

@Service
public class AuthCertServiceImpl implements AuthCertService {

	@Autowired
	private AuthCertDao authCertDao;
	
	public Ticket queryTicketByPiid(String processInstanceId) {
		return authCertDao.queryTicketByPiid(processInstanceId);
	}

	public Member queryMemberByTicket(Ticket ticket) {
		return authCertDao.queryMemberByTicket(ticket);
	}

	public Member queryMemberById(Integer memberid) {
		return authCertDao.queryMemberById(memberid);
	}

	public List<CertImg> queryCertImgsByMemberid(Integer memberid) {
		return authCertDao.queryCertImgsByMemberid(memberid);
	}
}
