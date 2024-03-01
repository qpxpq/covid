package com.info.view.work.service;

import java.util.List;

import com.info.common.util.PagerList;
import com.info.view.work.model.Work;

public interface IWorkService {
	
	public void addWork(Work work);
	
	public void updateWork(Work work);
	
	public void deteleWork(String id);
	
	public List<Work> findAll();
	
	public Work findById(String id);
	
	public Work login(String workNo,String password);

	public PagerList findPagerList(PagerList pagerList, Work work);

	public List<Work> findByCondition(String sql);
	
	public List<Work> executeSQL(String sql);
}
