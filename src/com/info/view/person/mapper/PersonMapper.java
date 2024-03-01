package com.info.view.person.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.info.view.person.model.Person;

public interface PersonMapper {
	
	public void addPerson(Person person);
	
	public void updatePerson(Person person);
	
	public void detelePerson(String id);
	
	public List<Person> findAll();
	
	public Person findById(String id);
	
	public List<Person> findByCondition(@Param(value = "hql")String hql);
	
	public List<Person> executeSQL(@Param(value = "sql")String sql);
	
	int selectCount(@Param(value = "hql")String hql);
}
