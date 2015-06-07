package com.ywb.server.beans;

import java.sql.Timestamp;

public class Favorite {
	private Integer id;
	private Integer subject_id;
	private Integer course_circle_id;
	private Timestamp time;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSubject_id() {
		return subject_id;
	}
	public void setSubject_id(Integer subject_id) {
		this.subject_id = subject_id;
	}
	public Integer getCourse_circle_id() {
		return course_circle_id;
	}
	public void setCourse_circle_id(Integer course_circle_id) {
		this.course_circle_id = course_circle_id;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	
	
}
