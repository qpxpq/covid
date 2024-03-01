package com.info.view.vaccinum.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.info.view.vaccinum.model.Vaccinum;

public interface VaccinumMapper {
	
	public void addVaccinum(Vaccinum vaccinum);
	
	public void updateVaccinum(Vaccinum vaccinum);
	
	public void deteleVaccinum(String id);
	
	public List<Vaccinum> findAll();
	
	public Vaccinum findById(String id);
	
	public List<Vaccinum> findByCondition(@Param(value = "hql")String hql);
	
	public List<Vaccinum> executeSQL(@Param(value = "sql")String sql);
	
	int selectCount(@Param(value = "hql")String hql);
}
