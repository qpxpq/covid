package com.info.view.message.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.info.common.util.PagerList;
import com.info.view.message.mapper.MessageMapper;
import com.info.view.message.model.Message;
import com.info.view.message.service.IMessageService;

@Service("messageService")
public class MessageServiceImpl implements IMessageService {
	
	@Autowired
	private MessageMapper messageMapper;


	public void addMessage(Message message) {
		messageMapper.addMessage(message);
	}

	public void updateMessage(Message message) {
		messageMapper.updateMessage(message);
	}

	public void deteleMessage(String id) {
		messageMapper.deteleMessage(id);
	}

	public List<Message> findAll() {
		return messageMapper.findAll();
	}

	public Message findById(String id) {
		return messageMapper.findById(id);
	}

	public Message login(String messageNo, String password) {
		List<Message> list = messageMapper.findByCondition(" and messageNo='" + messageNo + "' and password='" + password +"'");
		if(list != null && list.size() > 0){
			return (Message)list.get(0);
		}
		return null;
	}

	
	public PagerList findPagerList(PagerList pagerList,Message message) {
		String sqlWhere = "";
		//查询申请标题
		if(message.getMessageName() != null && !"".equals(message.getMessageName())){
			sqlWhere += " and messageName like '%"+ message.getMessageName() +"%'";
		}
		//查询申请内容
		if(message.getMessageContext() != null && !"".equals(message.getMessageContext())){
			sqlWhere += " and messageContext like '%"+ message.getMessageContext() +"%'";
		}
		//查询用户Id
		if(message.getUserId() != null && !"".equals(message.getUserId())){
			sqlWhere += " and userId like '%"+ message.getUserId() +"%'";
		}
		//查询申请人
		if(message.getUserName() != null && !"".equals(message.getUserName())){
			sqlWhere += " and userName like '%"+ message.getUserName() +"%'";
		}
		//查询审核人
		if(message.getAuthorId() != null && !"".equals(message.getAuthorId())){
			sqlWhere += " and authorId like '%"+ message.getAuthorId() +"%'";
		}
		//查询审核人
		if(message.getAuthorName() != null && !"".equals(message.getAuthorName())){
			sqlWhere += " and authorName like '%"+ message.getAuthorName() +"%'";
		}
		//查询回复内容
		if(message.getAuthorContext() != null && !"".equals(message.getAuthorContext())){
			sqlWhere += " and authorContext like '%"+ message.getAuthorContext() +"%'";
		}
		//查询状态
		if(message.getMessageType() != null && !"".equals(message.getMessageType())){
			sqlWhere += " and messageType like '%"+ message.getMessageType() +"%'";
		}
		//查询主键
		if(message.getResourceId() != null && !"".equals(message.getResourceId())){
			sqlWhere += " and resourceId like '%"+ message.getResourceId() +"%'";
		}
		//查询创建人ID
		if(message.getCreatorId() != null && !"".equals(message.getCreatorId())){
			sqlWhere += " and creatorId like '%"+ message.getCreatorId() +"%'";
		}
		//查询创建人
		if(message.getCreatorName() != null && !"".equals(message.getCreatorName())){
			sqlWhere += " and creatorName like '%"+ message.getCreatorName() +"%'";
		}
		//查询修改人ID
		if(message.getUpdaterId() != null && !"".equals(message.getUpdaterId())){
			sqlWhere += " and updaterId like '%"+ message.getUpdaterId() +"%'";
		}
		//查询修改人
		if(message.getUpdaterName() != null && !"".equals(message.getUpdaterName())){
			sqlWhere += " and updaterName like '%"+ message.getUpdaterName() +"%'";
		}
		sqlWhere += " order by createTime desc";
		String limitSQL = " limit " + pagerList.getPageStrart() + "," + pagerList.getPageSize();
		List<?> list = messageMapper.findByCondition(sqlWhere + limitSQL);
		int count = messageMapper.selectCount(sqlWhere);
		//设置分页对象值
		pagerList.setPageList(list);
		pagerList.setTotalRecord(count);
		return pagerList;
	}
	
	public List<Message> findByCondition(String sql){
		return messageMapper.findByCondition(sql);
	}
	
	public List<Message> executeSQL(String sql){
		return messageMapper.executeSQL(sql);
	}
}
