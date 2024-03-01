package com.info.view.bespoke.service;

import java.util.List;

import com.info.common.util.PagerList;
import com.info.view.bespoke.model.Bespoke;

public interface IBespokeService {
	
	public void addBespoke(Bespoke bespoke);
	
	public void updateBespoke(Bespoke bespoke);
	
	public void deteleBespoke(String id);
	
	public List<Bespoke> findAll();
	
	public Bespoke findById(String id);
	
	public Bespoke login(String bespokeNo,String password);

	public PagerList findPagerList(PagerList pagerList, Bespoke bespoke);

	public List<Bespoke> findByCondition(String sql);
	
	public List<Bespoke> executeSQL(String sql);
}
