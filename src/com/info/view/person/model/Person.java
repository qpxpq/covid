package com.info.view.person.model;

/**
 * Person entity. @author MyEclipse Persistence Tools
 */

public class Person implements java.io.Serializable {

	// Fields

	private String personId;
	private String personName;
	private String password;
	private String role;
	private String personNo;
	private String cardNo;
	private String personMobile;
	private String sex;

	// Constructors

	/** default constructor */
	public Person() {
	}

	/** minimal constructor */
	public Person(String personId) {
		this.personId = personId;
	}

	/** full constructor */
	public Person(String personId, String personName, String password,
			String role) {
		this.personId = personId;
		this.personName = personName;
		this.password = password;
		this.role = role;
	}

	// Property accessors

	public String getPersonId() {
		return this.personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getPersonName() {
		return this.personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPersonNo() {
		return personNo;
	}

	public void setPersonNo(String personNo) {
		this.personNo = personNo;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getPersonMobile() {
		return personMobile;
	}

	public void setPersonMobile(String personMobile) {
		this.personMobile = personMobile;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

}