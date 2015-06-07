package com.ywb.server.beans;

public class Student {
    private Integer id;
    private String name;
    private Integer age;
    private String gender;
    private String contact;
    private String second_contact;
    private String head_photo_url;
    private Integer teacher_id;
    
    
	public Integer getTeacher_id() {
		return teacher_id;
	}

	public void setTeacher_id(Integer teacher_id) {
		this.teacher_id = teacher_id;
	}

	public String getSecondContact() {
		return second_contact;
	}

	public void setSecondContact(String second_contact) {
		this.second_contact = second_contact;
	}

	public String getHeadPhotoURL() {
		return head_photo_url;
	}

	public void setHeadPhotoURL(String headPhotoURL) {
		this.head_photo_url = headPhotoURL;
	}

	public Integer getId() {
		return id;
	}
	
	public Student(){
		
	}
	
	public Student(String name, Integer age, String gender, String contact) {
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.contact = contact;
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
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
}
