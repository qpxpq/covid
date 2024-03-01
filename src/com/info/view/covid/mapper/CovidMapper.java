package com.info.view.covid.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.info.view.covid.model.Covid;

public interface CovidMapper {
	
	public void addCovid(Covid covid);
	
	public void updateCovid(Covid covid);
	
	public void deteleCovid(String id);
	
	public List<Covid> findAll();
	
	public Covid findById(String id);
	
	public List<Covid> findByCondition(@Param(value = "hql")String hql);
	
	public List<Covid> executeSQL(@Param(value = "sql")String sql);
	
	int selectCount(@Param(value = "hql")String hql);
}
