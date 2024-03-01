package com.info.view.user.service;

import java.util.List;

import com.info.common.util.PagerList;
import com.info.view.leadership.model.Leadership;
import com.info.view.user.model.User;

public interface IUserService {
	
	public void addUser(User user);
	
	public void updateUser(User user);
	
	public void deteleUser(String id);
	
	public List<User> findAll();
	
	public User findById(String id);
	
	public User login(String userNo,String password);

	public PagerList findPagerList(PagerList pagerList, User user);

	public List<User> findByCondition(String sql);
	
	public List<User> executeSQL(String sql);
	
}
