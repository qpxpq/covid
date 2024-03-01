package com.info.view.divergence.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.info.view.divergence.model.Divergence;

public interface DivergenceMapper {
	
	public void addDivergence(Divergence divergence);
	
	public void updateDivergence(Divergence divergence);
	
	public void deteleDivergence(String id);
	
	public List<Divergence> findAll();
	
	public Divergence findById(String id);
	
	public List<Divergence> findByCondition(@Param(value = "hql")String hql);
	
	public List<Divergence> executeSQL(@Param(value = "sql")String sql);
	
	int selectCount(@Param(value = "hql")String hql);
}
