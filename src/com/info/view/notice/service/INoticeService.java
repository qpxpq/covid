package com.info.view.notice.service;

import java.util.List;

import com.info.common.util.PagerList;
import com.info.view.notice.model.Notice;

public interface INoticeService {
	
	public void addNotice(Notice notice);
	
	public void updateNotice(Notice notice);
	
	public void deteleNotice(String id);
	
	public List<Notice> findAll();
	
	public Notice findById(String id);
	
	public Notice login(String noticeNo,String password);

	public PagerList findPagerList(PagerList pagerList, Notice notice);

	public List<Notice> findByCondition(String sql);
	
	public List<Notice> executeSQL(String sql);
}
