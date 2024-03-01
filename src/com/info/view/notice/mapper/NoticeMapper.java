package com.info.view.notice.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.info.view.notice.model.Notice;

public interface NoticeMapper {
	
	public void addNotice(Notice notice);
	
	public void updateNotice(Notice notice);
	
	public void deteleNotice(String id);
	
	public List<Notice> findAll();
	
	public Notice findById(String id);
	
	public List<Notice> findByCondition(@Param(value = "hql")String hql);
	
	public List<Notice> executeSQL(@Param(value = "sql")String sql);
	
	int selectCount(@Param(value = "hql")String hql);
}
