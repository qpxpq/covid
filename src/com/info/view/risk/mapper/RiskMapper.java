package com.info.view.risk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.info.view.risk.model.Risk;

public interface RiskMapper {
	
	public void addRisk(Risk risk);
	
	public void updateRisk(Risk risk);
	
	public void deteleRisk(String id);
	
	public List<Risk> findAll();
	
	public Risk findById(String id);
	
	public List<Risk> findByCondition(@Param(value = "hql")String hql);
	
	public List<Risk> executeSQL(@Param(value = "sql")String sql);
	
	int selectCount(@Param(value = "hql")String hql);
}
