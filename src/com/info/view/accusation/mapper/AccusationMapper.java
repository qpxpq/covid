package com.info.view.accusation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.info.view.accusation.model.Accusation;

public interface AccusationMapper {
	
	public void addAccusation(Accusation accusation);
	
	public void updateAccusation(Accusation accusation);
	
	public void deteleAccusation(String id);
	
	public List<Accusation> findAll();
	
	public Accusation findById(String id);
	
	public List<Accusation> findByCondition(@Param(value = "hql")String hql);
	
	public List<Accusation> executeSQL(@Param(value = "sql")String sql);
	
	int selectCount(@Param(value = "hql")String hql);
}
