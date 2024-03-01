package com.info.view.flow.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.info.view.flow.model.Flow;

public interface FlowMapper {
	
	public void addFlow(Flow flow);
	
	public void updateFlow(Flow flow);
	
	public void deteleFlow(String id);
	
	public List<Flow> findAll();
	
	public Flow findById(String id);
	
	public List<Flow> findByCondition(@Param(value = "hql")String hql);
	
	public List<Flow> executeSQL(@Param(value = "sql")String sql);
	
	int selectCount(@Param(value = "hql")String hql);
}
