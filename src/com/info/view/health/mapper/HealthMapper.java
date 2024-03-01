package com.info.view.health.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.info.view.health.model.Health;

public interface HealthMapper {
	
	public void addHealth(Health health);
	
	public void updateHealth(Health health);
	
	public void deteleHealth(String id);
	
	public List<Health> findAll();
	
	public Health findById(String id);
	
	public List<Health> findByCondition(@Param(value = "hql")String hql);
	
	public List<Health> executeSQL(@Param(value = "sql")String sql);
	
	int selectCount(@Param(value = "hql")String hql);
}
