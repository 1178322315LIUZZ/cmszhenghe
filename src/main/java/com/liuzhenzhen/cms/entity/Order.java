package com.liuzhenzhen.cms.entity;

import java.io.Serializable;

/**
 * 
 * @ClassName: Order 
 * @Description: 节目单实体类
 * @author: Lzz18
 * @date: 2020年4月14日 上午8:50:53
 */
public class Order implements Serializable{
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String state;
	private String start;
	private String end;
	private String addr;
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", name=" + name + ", state=" + state + ", start=" + start + ", end=" + end
				+ ", addr=" + addr + "]";
	}
	
}
