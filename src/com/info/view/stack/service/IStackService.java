package com.info.view.stack.service;

import java.util.List;

import com.info.common.util.PagerList;
import com.info.view.stack.model.Stack;

public interface IStackService {
	
	public void addStack(Stack stack);
	
	public void updateStack(Stack stack);
	
	public void deteleStack(String id);
	
	public List<Stack> findAll();
	
	public Stack findById(String id);
	
	public Stack login(String stackNo,String password);

	public PagerList findPagerList(PagerList pagerList, Stack stack);

	public List<Stack> findByCondition(String sql);
	
	public List<Stack> executeSQL(String sql);
}
