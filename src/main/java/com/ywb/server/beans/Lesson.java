package com.ywb.server.beans;

import java.sql.Timestamp;
import java.util.List;

public class Lesson {
	private Integer id;
	private Integer course_id;
	private String summary_text;
	private List<String> summary_photo;
	private String summary_video;
	private String homework_text;
	private String homework_video;
	private List<String> homework_photo;
	private Timestamp timestamp;
	
	
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
	
	public String getSummary_text() {
		return summary_text;
	}
	public void setSummary_text(String summary_text) {
		this.summary_text = summary_text;
	}
	public List<String> getSummary_photo() {
		return summary_photo;
	}
	public void setSummary_photo(List<String> summary_photo) {
		this.summary_photo = summary_photo;
	}
	public String getSummary_video() {
		return summary_video;
	}
	public void setSummary_video(String summary_video) {
		this.summary_video = summary_video;
	}
	public String getHomework_text() {
		return homework_text;
	}
	public void setHomework_text(String homework_text) {
		this.homework_text = homework_text;
	}
	public String getHomework_video() {
		return homework_video;
	}
	public void setHomework_video(String homework_video) {
		this.homework_video = homework_video;
	}
	
	public List<String> getHomework_photo() {
		return homework_photo;
	}
	public void setHomework_photo(List<String> homework_photo) {
		this.homework_photo = homework_photo;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
}
