package com.atguigu.atcrowdfunding.user.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.atcrowdfunding.common.CertImg;
import com.atguigu.atcrowdfunding.common.Datas;
import com.atguigu.atcrowdfunding.common.Member;
import com.atguigu.atcrowdfunding.common.Ticket;
import com.atguigu.atcrowdfunding.manager.bean.Cert;
import com.atguigu.atcrowdfunding.user.dao.MemberDao;
import com.atguigu.atcrowdfunding.user.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDao memberDao;

	public Member queryMember(Map<String, Object> paramMap) {
		return memberDao.queryMember(paramMap);
	}

	public void updateAcctType(Member loginMember) {
		memberDao.updateAcctType(loginMember);
	}

	public Ticket quertTicketByMember(Member loginMember) {
		return memberDao.quertTicketByMember(loginMember);
	}

	public void insertTicket(Ticket ticket) {
		memberDao.insertTicket(ticket);
	}

	public void updateTicketProcessStep(Ticket ticket) {
		memberDao.updateTicketProcessStep(ticket);
	}

	public void updateBasicinfo(Member loginMember) {
		memberDao.updateBasicinfo(loginMember);
	}

	public List<Cert> queryCertsByAccttype(String accttype) {
		return memberDao.queryCertsByAccttype(accttype);
	}

	public void insertMemberCerts(Datas ds) {
		for ( CertImg ci : ds.getCertimgs() ) {
			memberDao.insertCertImg(ci);
		}
	}

	public void updateTicket4PI(Ticket ticket) {
		memberDao.updateTicket4PI(ticket);
	}

	public void updateEmail(Member loginMember) {
		memberDao.updateEmail(loginMember);
	}

	public void updateAuthStatus(Member loginMember) {
		memberDao.updateAuthStatus(loginMember);
	}
}
