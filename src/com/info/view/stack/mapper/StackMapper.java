package com.info.view.stack.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.info.view.stack.model.Stack;

public interface StackMapper {
	
	public void addStack(Stack stack);
	
	public void updateStack(Stack stack);
	
	public void deteleStack(String id);
	
	public List<Stack> findAll();
	
	public Stack findById(String id);
	
	public List<Stack> findByCondition(@Param(value = "hql")String hql);
	
	public List<Stack> executeSQL(@Param(value = "sql")String sql);
	
	int selectCount(@Param(value = "hql")String hql);
}
