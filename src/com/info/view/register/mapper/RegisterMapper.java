package com.info.view.register.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.info.view.register.model.Register;

public interface RegisterMapper {
	
	public void addRegister(Register register);
	
	public void updateRegister(Register register);
	
	public void deteleRegister(String id);
	
	public List<Register> findAll();
	
	public Register findById(String id);
	
	public List<Register> findByCondition(@Param(value = "hql")String hql);
	
	public List<Register> executeSQL(@Param(value = "sql")String sql);
	
	int selectCount(@Param(value = "hql")String hql);
}
