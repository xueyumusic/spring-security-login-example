package com.ywb.server.beans;

import java.sql.Date;
import java.util.List;

public class Course {
    private Integer id;
    private String course_name;
    private Integer principal_id;
    private List<Integer> teacher_id;
    private double price;
    private Date   begin_time;
    private Date    end_time;
    private String head_photo;
    private Integer class_hours;
    private String class_description;
    private List<String> photos;
    
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCoursename() {
		return course_name;
	}
	public void setCoursename(String coursename) {
		this.course_name = coursename;
	}
	public List<Integer> getTeacher_id() {
		return teacher_id;
	}
	public void setTeacher_id(List<Integer> teacher_id) {
		this.teacher_id = teacher_id;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Date getBegin_time() {
		return begin_time;
	}
	public void setBegin_time(Date begin_time) {
		this.begin_time = begin_time;
	}
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	public Integer getPrincipal_id() {
		return principal_id;
	}
	public void setPrincipal_id(Integer principal_id) {
		this.principal_id = principal_id;
	}
	public String getHead_photo() {
		return head_photo;
	}
	public void setHead_photo(String head_photo) {
		this.head_photo = head_photo;
	}
	public Integer getClass_hours() {
		return class_hours;
	}
	public void setClass_hours(Integer class_hours) {
		this.class_hours = class_hours;
	}
	public String getClass_description() {
		return class_description;
	}
	public void setClass_description(String class_description) {
		this.class_description = class_description;
	}
	public List<String> getPhotos() {
		return photos;
	}
	public void setPhotos(List<String> photos) {
		this.photos = photos;
	}
	
}
