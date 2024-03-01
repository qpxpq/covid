package com.info.view.message.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.info.view.message.model.Message;

public interface MessageMapper {
	
	public void addMessage(Message message);
	
	public void updateMessage(Message message);
	
	public void deteleMessage(String id);
	
	public List<Message> findAll();
	
	public Message findById(String id);
	
	public List<Message> findByCondition(@Param(value = "hql")String hql);
	
	public List<Message> executeSQL(@Param(value = "sql")String sql);
	
	int selectCount(@Param(value = "hql")String hql);
}
