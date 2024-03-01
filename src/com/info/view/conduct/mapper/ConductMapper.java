package com.info.view.conduct.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.info.view.conduct.model.Conduct;

public interface ConductMapper {
	
	public void addConduct(Conduct conduct);
	
	public void updateConduct(Conduct conduct);
	
	public void deteleConduct(String id);
	
	public List<Conduct> findAll();
	
	public Conduct findById(String id);
	
	public List<Conduct> findByCondition(@Param(value = "hql")String hql);
	
	public List<Conduct> executeSQL(@Param(value = "sql")String sql);
	
	int selectCount(@Param(value = "hql")String hql);
}
