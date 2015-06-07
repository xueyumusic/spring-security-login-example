package com.ywb.server.beans;

import java.sql.Timestamp;

public class LessonComment {
	private Integer id;
	private Integer class_id;
	private Integer parent_id;
	private boolean anonymity;
	private String review;
	private Integer rate;
	private Timestamp timestamp;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getClass_id() {
		return class_id;
	}
	public void setClass_id(Integer class_id) {
		this.class_id = class_id;
	}
	public Integer getPublisher() {
		return parent_id;
	}
	public void setPublisher(Integer publisher) {
		this.parent_id = publisher;
	}
	public boolean isAnonymity() {
		return anonymity;
	}
	public void setAnonymity(boolean anonymity) {
		this.anonymity = anonymity;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public Integer getRate() {
		return rate;
	}
	public void setRate(Integer rate) {
		this.rate = rate;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
}
