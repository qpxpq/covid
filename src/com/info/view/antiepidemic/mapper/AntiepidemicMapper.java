package com.info.view.antiepidemic.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.info.view.antiepidemic.model.Antiepidemic;

public interface AntiepidemicMapper {
	
	public void addAntiepidemic(Antiepidemic antiepidemic);
	
	public void updateAntiepidemic(Antiepidemic antiepidemic);
	
	public void deteleAntiepidemic(String id);
	
	public List<Antiepidemic> findAll();
	
	public Antiepidemic findById(String id);
	
	public List<Antiepidemic> findByCondition(@Param(value = "hql")String hql);
	
	public List<Antiepidemic> executeSQL(@Param(value = "sql")String sql);
	
	int selectCount(@Param(value = "hql")String hql);
}
