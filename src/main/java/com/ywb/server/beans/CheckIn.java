package com.ywb.server.beans;

import java.sql.Timestamp;

public class CheckIn {
    private Integer id;
    private Integer student_id;
    private Timestamp time;
    private Integer course_id;
    private String type;
    
    public CheckIn() {};
    public CheckIn(Integer course_id, Integer student_id) {
    	this.setCourse_id(course_id);
    	this.student_id = student_id;
    }
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStudent_id() {
		return student_id;
	}
	public void setStudent_id(Integer student_id) {
		this.student_id = student_id;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getCourse_id() {
		return course_id;
	}
	public void setCourse_id(Integer course_id) {
		this.course_id = course_id;
	}
}
