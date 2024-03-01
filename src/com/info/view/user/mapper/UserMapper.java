package com.info.view.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.info.view.leadership.model.Leadership;
import com.info.view.user.model.User;

public interface UserMapper {
	
	public void addUser(User user);
	
	public void updateUser(User user);
	
	public void deteleUser(String id);
	
	public List<User> findAll();
	
	public User findById(String id);
	
	public List<User> findByCondition(@Param(value = "hql")String hql);
	
	public List<User> executeSQL(@Param(value = "sql")String sql);
	
	int selectCount(@Param(value = "hql")String hql);
	
	
}
