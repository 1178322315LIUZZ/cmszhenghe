package com.liuzhenzhen.cms.entity;

public enum ContentType {
	HTML(0,"HTML"),VOTE(1,"VOTE");
	private Integer code;
	private String name;
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private ContentType(Integer code, String name) {
		this.code = code;
		this.name = name;
	}
	private ContentType() {
	}
	
}
