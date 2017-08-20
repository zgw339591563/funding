package com.atguigu.atcrowdfunding.manager.dao;

import java.util.List;
import java.util.Map;

import com.atguigu.atcrowdfunding.common.Datas;
import com.atguigu.atcrowdfunding.manager.bean.Message;

public interface MessageDao {

	Message queryMessage(Map<String, Object> paramMap);

	List<Message> pageQuery(Map<String, Object> paramMap);

	int queryCount(Map<String, Object> paramMap);

	void insertMessage(Message message);

	Message queryById(Integer id);

	int updateMessage(Message message);

	int deleteMessage(Integer id);

	int deleteMessages(Datas ds);
}
