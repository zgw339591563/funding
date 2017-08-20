package com.atguigu.atcrowdfunding.manager.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.atcrowdfunding.common.Datas;
import com.atguigu.atcrowdfunding.manager.bean.Message;
import com.atguigu.atcrowdfunding.manager.dao.MessageDao;
import com.atguigu.atcrowdfunding.manager.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageDao messageDao;

	public Message queryMessage(Map<String, Object> paramMap) {
		return messageDao.queryMessage(paramMap);
	}

	public List<Message> pageQuery(Map<String, Object> paramMap) {
		return messageDao.pageQuery(paramMap);
	}

	public int queryCount(Map<String, Object> paramMap) {
		return messageDao.queryCount(paramMap);
	}

	public void insertMessage(Message message) {
		messageDao.insertMessage(message);
	}

	public Message queryById(Integer id) {
		return messageDao.queryById(id);
	}

	public int updateMessage(Message message) {
		return messageDao.updateMessage(message);
	}

	public int deleteMessage(Integer id) {
		return messageDao.deleteMessage(id);
	}

	public int deleteMessages(Datas ds) {
		return messageDao.deleteMessages(ds);
	}

}
