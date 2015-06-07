package com.ywb.server.beans;

public class StudentWork {
	private Integer id;
	private Integer student_id;
	private Integer teacher_id;
	private String work_url;
	private String type;
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
	public Integer getTeacher_id() {
		return teacher_id;
	}
	public void setTeacher_id(Integer teacher_id) {
		this.teacher_id = teacher_id;
	}
	public String getWork_url() {
		return work_url;
	}
	public void setWork_url(String work_url) {
		this.work_url = work_url;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
