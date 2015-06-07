package com.ywb.server.beans;

public class Orgnization {
	 private Integer id;
	 private String name;
	 private String location;
	 private String description;
	 private Integer orgid;
	 private String headpath;
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
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setOrgid(Integer orgid) {
		this.orgid = orgid;
	}
	public Integer getOrgid() {
		return orgid;
	}
	public void setHeadpath(String headpath) {
		this.headpath = headpath;
	}
	public String getHeadpath() {
		return headpath;
	}
}
