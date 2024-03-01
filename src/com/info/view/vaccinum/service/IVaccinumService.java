package com.info.view.vaccinum.service;

import java.util.List;

import com.info.common.util.PagerList;
import com.info.view.vaccinum.model.Vaccinum;

public interface IVaccinumService {
	
	public void addVaccinum(Vaccinum vaccinum);
	
	public void updateVaccinum(Vaccinum vaccinum);
	
	public void deteleVaccinum(String id);
	
	public List<Vaccinum> findAll();
	
	public Vaccinum findById(String id);
	
	public Vaccinum login(String vaccinumNo,String password);

	public PagerList findPagerList(PagerList pagerList, Vaccinum vaccinum);

	public List<Vaccinum> findByCondition(String sql);
	
	public List<Vaccinum> executeSQL(String sql);
}
