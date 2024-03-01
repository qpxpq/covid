package com.info.view.log.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.info.common.util.PagerList;
import com.info.view.log.model.Log;

public interface ILogService {
	
	public void addLogInfo(HttpServletRequest request);
	
	public void addLog(Log log);
	
	public void updateLog(Log log);
	
	public void deteleLog(String id);
	
	public List<Log> findAll();
	
	public Log findById(String id);
	
	public Log login(String logNo,String password);

	public PagerList findPagerList(PagerList pagerList, Log log);

	public List<Log> findByCondition(String sql);
	
	public List<Log> executeSQL(String sql);
}
