package com.info.view.disinfect.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.info.view.disinfect.model.Disinfect;

public interface DisinfectMapper {
	
	public void addDisinfect(Disinfect disinfect);
	
	public void updateDisinfect(Disinfect disinfect);
	
	public void deteleDisinfect(String id);
	
	public List<Disinfect> findAll();
	
	public Disinfect findById(String id);
	
	public List<Disinfect> findByCondition(@Param(value = "hql")String hql);
	
	public List<Disinfect> executeSQL(@Param(value = "sql")String sql);
	
	int selectCount(@Param(value = "hql")String hql);
}
