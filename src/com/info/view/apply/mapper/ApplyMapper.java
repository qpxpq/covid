package com.info.view.apply.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.info.view.apply.model.Apply;

public interface ApplyMapper {
	
	public void addApply(Apply apply);
	
	public void updateApply(Apply apply);
	
	public void deteleApply(String id);
	
	public List<Apply> findAll();
	
	public Apply findById(String id);
	
	public List<Apply> findByCondition(@Param(value = "hql")String hql);
	
	public List<Apply> executeSQL(@Param(value = "sql")String sql);
	
	int selectCount(@Param(value = "hql")String hql);
}
