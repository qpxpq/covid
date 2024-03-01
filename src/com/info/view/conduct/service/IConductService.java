package com.info.view.conduct.service;

import java.util.List;

import com.info.common.util.PagerList;
import com.info.view.conduct.model.Conduct;

public interface IConductService {
	
	public void addConduct(Conduct conduct);
	
	public void updateConduct(Conduct conduct);
	
	public void deteleConduct(String id);
	
	public List<Conduct> findAll();
	
	public Conduct findById(String id);
	
	public Conduct login(String conductNo,String password);

	public PagerList findPagerList(PagerList pagerList, Conduct conduct);

	public List<Conduct> findByCondition(String sql);
	
	public List<Conduct> executeSQL(String sql);
}
