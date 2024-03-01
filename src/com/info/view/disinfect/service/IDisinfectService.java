package com.info.view.disinfect.service;

import java.util.List;

import com.info.common.util.PagerList;
import com.info.view.disinfect.model.Disinfect;

public interface IDisinfectService {
	
	public void addDisinfect(Disinfect disinfect);
	
	public void updateDisinfect(Disinfect disinfect);
	
	public void deteleDisinfect(String id);
	
	public List<Disinfect> findAll();
	
	public Disinfect findById(String id);
	
	public Disinfect login(String disinfectNo,String password);

	public PagerList findPagerList(PagerList pagerList, Disinfect disinfect);

	public List<Disinfect> findByCondition(String sql);
	
	public List<Disinfect> executeSQL(String sql);
}
