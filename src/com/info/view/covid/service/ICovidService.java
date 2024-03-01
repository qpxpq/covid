package com.info.view.covid.service;

import java.util.List;

import com.info.common.util.PagerList;
import com.info.view.covid.model.Covid;

public interface ICovidService {
	
	public void addCovid(Covid covid);
	
	public void updateCovid(Covid covid);
	
	public void deteleCovid(String id);
	
	public List<Covid> findAll();
	
	public Covid findById(String id);
	
	public Covid login(String covidNo,String password);

	public PagerList findPagerList(PagerList pagerList, Covid covid);

	public List<Covid> findByCondition(String sql);
	
	public List<Covid> executeSQL(String sql);
}
