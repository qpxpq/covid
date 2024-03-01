package com.info.view.person.service;

import java.util.List;

import com.info.common.util.PagerList;
import com.info.view.person.model.Person;

public interface IPersonService {
	
	public void addPerson(Person person);
	
	public void updatePerson(Person person);
	
	public void detelePerson(String id);
	
	public List<Person> findAll();
	
	public Person findById(String id);
	
	public Person login(String personNo,String password);

	public PagerList findPagerList(PagerList pagerList, Person person);
}
