package com.atguigu.atcrowdfunding.manager.service;

import java.util.List;

import com.atguigu.atcrowdfunding.common.CertImg;
import com.atguigu.atcrowdfunding.common.Member;
import com.atguigu.atcrowdfunding.common.Ticket;

public interface AuthCertService {

	Ticket queryTicketByPiid(String processInstanceId);

	Member queryMemberByTicket(Ticket ticket);

	Member queryMemberById(Integer memberid);

	List<CertImg> queryCertImgsByMemberid(Integer memberid);

}
