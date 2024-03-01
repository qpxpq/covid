package com.info.view.message.service;

import java.util.List;

import com.info.common.util.PagerList;
import com.info.view.message.model.Message;

public interface IMessageService {
	
	public void addMessage(Message message);
	
	public void updateMessage(Message message);
	
	public void deteleMessage(String id);
	
	public List<Message> findAll();
	
	public Message findById(String id);
	
	public Message login(String messageNo,String password);

	public PagerList findPagerList(PagerList pagerList, Message message);

	public List<Message> findByCondition(String sql);
	
	public List<Message> executeSQL(String sql);
}
