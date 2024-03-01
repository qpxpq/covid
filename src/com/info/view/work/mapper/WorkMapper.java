package com.info.view.work.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.info.view.work.model.Work;

public interface WorkMapper {
	
	public void addWork(Work work);
	
	public void updateWork(Work work);
	
	public void deteleWork(String id);
	
	public List<Work> findAll();
	
	public Work findById(String id);
	
	public List<Work> findByCondition(@Param(value = "hql")String hql);
	
	public List<Work> executeSQL(@Param(value = "sql")String sql);
	
	int selectCount(@Param(value = "hql")String hql);
}
