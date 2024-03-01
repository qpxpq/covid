package com.info.view.leadership.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.info.view.leadership.model.Leadership;

public interface LeadershipMapper {
	
	public void addLeadership(Leadership leadership);
	
	public void updateLeadership(Leadership leadership);
	
	public void deteleLeadership(String id);
	
	public List<Leadership> findAll();
	
	public Leadership findById(String id);
	
	public List<Leadership> findByCondition(@Param(value = "hql")String hql);
	
	public List<Leadership> executeSQL(@Param(value = "sql")String sql);
	
	int selectCount(@Param(value = "hql")String hql);
}
