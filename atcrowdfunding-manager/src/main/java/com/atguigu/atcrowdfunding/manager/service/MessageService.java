package com.atguigu.atcrowdfunding.manager.service;

import java.util.List;
import java.util.Map;

import com.atguigu.atcrowdfunding.common.Datas;
import com.atguigu.atcrowdfunding.manager.bean.Message;

public interface MessageService {
	public Message queryMessage(Map<String, Object> paramMap);

	public List<Message> pageQuery(Map<String, Object> paramMap);

	public int queryCount(Map<String, Object> paramMap);

	public void insertMessage(Message message);

	public Message queryById(Integer id);

	public int updateMessage(Message message);

	public int deleteMessage(Integer id);

	public int deleteMessages(Datas ds);
}
