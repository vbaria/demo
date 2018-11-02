package com.demo.companyadmin.model;

import java.io.Serializable;

public class EmployeeRegResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	private long id;
	private int returnCode;
	private String comment;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id =  id;
	}
	public int getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@Override
	public String toString() {
		return "EmployeeRegResponse [id=" + id + ", returnCode=" + returnCode + ", comment=" + comment
				+ "]";
	}
}
