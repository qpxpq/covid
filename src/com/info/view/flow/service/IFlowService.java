package com.info.view.flow.service;

import java.util.List;

import com.info.common.util.PagerList;
import com.info.view.flow.model.Flow;

public interface IFlowService {
	
	public void addFlow(Flow flow);
	
	public void updateFlow(Flow flow);
	
	public void deteleFlow(String id);
	
	public List<Flow> findAll();
	
	public Flow findById(String id);
	
	public Flow login(String flowNo,String password);

	public PagerList findPagerList(PagerList pagerList, Flow flow);

	public List<Flow> findByCondition(String sql);
	
	public List<Flow> executeSQL(String sql);
}
