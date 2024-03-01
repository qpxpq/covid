package com.info.view.accusation.service;

import java.util.List;

import com.info.common.util.PagerList;
import com.info.view.accusation.model.Accusation;

public interface IAccusationService {
	
	public void addAccusation(Accusation accusation);
	
	public void updateAccusation(Accusation accusation);
	
	public void deteleAccusation(String id);
	
	public List<Accusation> findAll();
	
	public Accusation findById(String id);
	
	public Accusation login(String accusationNo,String password);

	public PagerList findPagerList(PagerList pagerList, Accusation accusation);

	public List<Accusation> findByCondition(String sql);
	
	public List<Accusation> executeSQL(String sql);
}
