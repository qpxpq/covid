package com.info.view.person.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.info.common.util.PagerList;
import com.info.view.person.mapper.PersonMapper;
import com.info.view.person.model.Person;
import com.info.view.person.service.IPersonService;

@Service("personService")
public class PersonServiceImpl implements IPersonService {
	
	@Autowired
	private PersonMapper personMapper;


	public void addPerson(Person person) {
		personMapper.addPerson(person);
	}

	public void updatePerson(Person person) {
		personMapper.updatePerson(person);
	}

	public void detelePerson(String id) {
		personMapper.detelePerson(id);
	}

	public List<Person> findAll() {
		return personMapper.findAll();
	}

	public Person findById(String id) {
		return personMapper.findById(id);
	}

	public Person login(String personNo, String password) {
		List<Person> list = personMapper.findByCondition(" and personNo='" + personNo + "' and password='" + password +"'");
		if(list != null && list.size() > 0){
			return (Person)list.get(0);
		}
		return null;
	}

	
	public PagerList findPagerList(PagerList pagerList,Person person) {
		String sqlWhere = "";
		if(person.getRole() != null && !"".equals(person.getRole())){
			sqlWhere += " and role like '%"+ person.getRole() +"%'";
		}
		if(person.getPersonName() != null && !"".equals(person.getPersonName())){
			sqlWhere += " and personName like '%"+ person.getPersonName() +"%'";
		}
		if(person.getPersonMobile() != null && !"".equals(person.getPersonMobile())){
			sqlWhere += " and personMobile like '%"+ person.getPersonMobile() +"%'";
		}
		if(person.getSex() != null && !"".equals(person.getSex())){
			sqlWhere += " and sex like '%"+ person.getSex() +"%'";
		}
		if(person.getCardNo() != null && !"".equals(person.getCardNo())){
			sqlWhere += " and cardNo like '%"+ person.getCardNo() +"%'";
		}
		sqlWhere += " order by personId";
		String limitSQL = " limit " + pagerList.getPageStrart() + "," + pagerList.getPageSize();
		List<?> list = personMapper.findByCondition(sqlWhere + limitSQL);
		int count = personMapper.selectCount(sqlWhere);
		//设置分页对象值
		pagerList.setPageList(list);
		pagerList.setTotalRecord(count);
		return pagerList;
	}
}
