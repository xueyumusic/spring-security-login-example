package com.ywb.server.beans;

import java.util.List;

public class CourseCircle {
	private Integer id;
	private Integer course_id;
	private Integer publisher_id;
	private String content;
	private List<String> photo_list;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCourse_id() {
		return course_id;
	}
	public void setCourse_id(Integer course_id) {
		this.course_id = course_id;
	}
	public Integer getPublisher_id() {
		return publisher_id;
	}
	public void setPublisher_id(Integer publisher_id) {
		this.publisher_id = publisher_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<String> getPhoto_list() {
		return photo_list;
	}
	public void setPhoto_list(List<String> photo_list) {
		this.photo_list = photo_list;
	}
	
}
