package com.ywb.server.beans;

import java.util.List;

public class Teacher {
	 private Integer id;
	 private String name;
	 private Integer age;
	 private String gender;
	 private Integer orgid;
	 private String description;
	 private String contact;
	 private List<Integer> listtest;
	 public Teacher() {};
	 public Teacher(String name, Integer age, String gender, String contact) {
		 this.name = name;
		 this.age = age;
		 this.gender = gender;
		 this.setContact(contact);
	 }
	 
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Integer getOrgid() {
		return orgid;
	}
	public void setOrgid(Integer orgid) {
		this.orgid = orgid;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public List<Integer> getListtest() {
		return listtest;
	}
	public void setListtest(List<Integer> listtest) {
		this.listtest = listtest;
	}

}
