package com.info.view.bespoke.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.info.view.bespoke.model.Bespoke;

public interface BespokeMapper {
	
	public void addBespoke(Bespoke bespoke);
	
	public void updateBespoke(Bespoke bespoke);
	
	public void deteleBespoke(String id);
	
	public List<Bespoke> findAll();
	
	public Bespoke findById(String id);
	
	public List<Bespoke> findByCondition(@Param(value = "hql")String hql);
	
	public List<Bespoke> executeSQL(@Param(value = "sql")String sql);
	
	int selectCount(@Param(value = "hql")String hql);
}
