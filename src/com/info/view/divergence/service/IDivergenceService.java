package com.info.view.divergence.service;

import java.util.List;

import com.info.common.util.PagerList;
import com.info.view.divergence.model.Divergence;

public interface IDivergenceService {
	
	public void addDivergence(Divergence divergence);
	
	public void updateDivergence(Divergence divergence);
	
	public void deteleDivergence(String id);
	
	public List<Divergence> findAll();
	
	public Divergence findById(String id);
	
	public Divergence login(String divergenceNo,String password);

	public PagerList findPagerList(PagerList pagerList, Divergence divergence);

	public List<Divergence> findByCondition(String sql);
	
	public List<Divergence> executeSQL(String sql);
}
