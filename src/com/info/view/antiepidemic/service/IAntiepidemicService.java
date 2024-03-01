package com.info.view.antiepidemic.service;

import java.util.List;

import com.info.common.util.PagerList;
import com.info.view.antiepidemic.model.Antiepidemic;

public interface IAntiepidemicService {
	
	public void addAntiepidemic(Antiepidemic antiepidemic);
	
	public void updateAntiepidemic(Antiepidemic antiepidemic);
	
	public void deteleAntiepidemic(String id);
	
	public List<Antiepidemic> findAll();
	
	public Antiepidemic findById(String id);
	
	public Antiepidemic login(String antiepidemicNo,String password);

	public PagerList findPagerList(PagerList pagerList, Antiepidemic antiepidemic);

	public List<Antiepidemic> findByCondition(String sql);
	
	public List<Antiepidemic> executeSQL(String sql);
}
