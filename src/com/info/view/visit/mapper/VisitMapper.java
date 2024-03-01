package com.info.view.visit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.info.view.visit.model.Visit;

public interface VisitMapper {
	
	public void addVisit(Visit visit);
	
	public void updateVisit(Visit visit);
	
	public void deteleVisit(String id);
	
	public List<Visit> findAll();
	
	public Visit findById(String id);
	
	public List<Visit> findByCondition(@Param(value = "hql")String hql);
	
	public List<Visit> executeSQL(@Param(value = "sql")String sql);
	
	int selectCount(@Param(value = "hql")String hql);
}
