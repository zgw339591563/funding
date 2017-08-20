package com.atguigu.atcrowdfunding.user.service;

import java.util.List;
import java.util.Map;

import com.atguigu.atcrowdfunding.common.Datas;
import com.atguigu.atcrowdfunding.common.Member;
import com.atguigu.atcrowdfunding.common.Ticket;
import com.atguigu.atcrowdfunding.manager.bean.Cert;

public interface MemberService {

	Member queryMember(Map<String, Object> paramMap);

	void updateAcctType(Member loginMember);

	Ticket quertTicketByMember(Member loginMember);

	void insertTicket(Ticket ticket);

	void updateTicketProcessStep(Ticket ticket);

	void updateBasicinfo(Member loginMember);

	List<Cert> queryCertsByAccttype(String accttype);

	void insertMemberCerts(Datas ds);

	void updateTicket4PI(Ticket ticket);

	void updateEmail(Member loginMember);

	void updateAuthStatus(Member loginMember);

}
