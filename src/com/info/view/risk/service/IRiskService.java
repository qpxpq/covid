package com.info.view.risk.service;

import java.util.List;

import com.info.common.util.PagerList;
import com.info.view.risk.model.Risk;

public interface IRiskService {
	
	public void addRisk(Risk risk);
	
	public void updateRisk(Risk risk);
	
	public void deteleRisk(String id);
	
	public List<Risk> findAll();
	
	public Risk findById(String id);
	
	public Risk login(String riskNo,String password);

	public PagerList findPagerList(PagerList pagerList, Risk risk);

	public List<Risk> findByCondition(String sql);
	
	public List<Risk> executeSQL(String sql);
}
