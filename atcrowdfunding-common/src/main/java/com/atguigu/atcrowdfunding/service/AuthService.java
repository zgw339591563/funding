package com.atguigu.atcrowdfunding.service;

import com.atguigu.atcrowdfunding.common.Ticket;

public interface AuthService {

	void passAuth(Integer memberid);

	Ticket queryTicketByMemberid(Integer memberid);

	void updateTicketStatus(Ticket ticket);

	void refuseAuth(Integer memberid);

}
