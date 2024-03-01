package com.info.view.leadership.service;

import java.util.List;

import com.info.common.util.PagerList;
import com.info.view.leadership.model.Leadership;

public interface ILeadershipService {
	
	public void addLeadership(Leadership leadership);
	
	public void updateLeadership(Leadership leadership);
	
	public void deteleLeadership(String id);
	
	public List<Leadership> findAll();
	
	public Leadership findById(String id);
	
	public Leadership login(String leadershipNo,String password);

	public PagerList findPagerList(PagerList pagerList, Leadership leadership);

	public List<Leadership> findByCondition(String sql);
	
	public List<Leadership> executeSQL(String sql);
}
