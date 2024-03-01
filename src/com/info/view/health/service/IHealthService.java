package com.info.view.health.service;

import java.util.List;

import com.info.common.util.PagerList;
import com.info.view.health.model.Health;

public interface IHealthService {
	
	public void addHealth(Health health);
	
	public void updateHealth(Health health);
	
	public void deteleHealth(String id);
	
	public List<Health> findAll();
	
	public Health findById(String id);
	
	public Health login(String healthNo,String password);

	public PagerList findPagerList(PagerList pagerList, Health health);

	public List<Health> findByCondition(String sql);
	
	public List<Health> executeSQL(String sql);
}
