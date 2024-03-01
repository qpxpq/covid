package com.info.view.apply.service;

import java.util.List;

import com.info.common.util.PagerList;
import com.info.view.apply.model.Apply;

public interface IApplyService {
	
	public void addApply(Apply apply);
	
	public void updateApply(Apply apply);
	
	public void deteleApply(String id);
	
	public List<Apply> findAll();
	
	public Apply findById(String id);
	
	public Apply login(String applyNo,String password);

	public PagerList findPagerList(PagerList pagerList, Apply apply);

	public List<Apply> findByCondition(String sql);
	
	public List<Apply> executeSQL(String sql);
}
