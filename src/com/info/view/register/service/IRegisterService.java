package com.info.view.register.service;

import java.util.List;

import com.info.common.util.PagerList;
import com.info.view.register.model.Register;

public interface IRegisterService {
	
	public void addRegister(Register register);
	
	public void updateRegister(Register register);
	
	public void deteleRegister(String id);
	
	public List<Register> findAll();
	
	public Register findById(String id);
	
	public Register login(String registerNo,String password);

	public PagerList findPagerList(PagerList pagerList, Register register);

	public List<Register> findByCondition(String sql);
	
	public List<Register> executeSQL(String sql);
}
