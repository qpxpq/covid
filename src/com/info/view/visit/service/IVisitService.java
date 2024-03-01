package com.info.view.visit.service;

import java.util.List;

import com.info.common.util.PagerList;
import com.info.view.visit.model.Visit;

public interface IVisitService {
	
	public void addVisit(Visit visit);
	
	public void updateVisit(Visit visit);
	
	public void deteleVisit(String id);
	
	public List<Visit> findAll();
	
	public Visit findById(String id);
	
	public Visit login(String visitNo,String password);

	public PagerList findPagerList(PagerList pagerList, Visit visit);

	public List<Visit> findByCondition(String sql);
	
	public List<Visit> executeSQL(String sql);
}
