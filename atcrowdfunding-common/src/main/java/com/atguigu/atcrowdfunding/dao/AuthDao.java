package com.atguigu.atcrowdfunding.dao;

import com.atguigu.atcrowdfunding.common.Ticket;

public interface AuthDao {

	void passAuth(Integer memberid);

	Ticket queryTicketByMemberid(Integer memberid);

	void updateTicketStatus(Ticket ticket);

	void refuseAuth(Integer memberid);

}
