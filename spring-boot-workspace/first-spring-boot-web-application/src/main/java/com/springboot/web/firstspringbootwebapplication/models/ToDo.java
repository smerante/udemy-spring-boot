package com.springboot.web.firstspringbootwebapplication.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class ToDo {
	
	@Id
	@GeneratedValue
	private int id;
	private String user;
	
	@Size(min=10,message="Enter atleast 10 characters!")
	private String descr;
	
	public Date targetDate;
	private boolean isDone;
	public ToDo() {
		super();
	}
	public ToDo(int id, String user, String descr, Date targetDate, boolean isDone) {
		super();
		this.id = id;
		this.user = user;
		this.descr = descr;
		this.targetDate = targetDate;
		this.isDone = isDone;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public Date getTargetDate() {
		return targetDate;
	}
	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}
	public boolean isDone() {
		return isDone;
	}
	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}
	@Override
	public boolean equals(Object obj) {
		if(this==obj)
			return true;
		if(obj==null || getClass() != obj.getClass() || ((ToDo)obj).id != this.id)
			return false;
		
		return true;
	}
	@Override
	public String toString() {
		return "ToDo [id=" + id + ", user=" + user + ", descr=" + descr + ", targetDate=" + targetDate + ", isDone="
				+ isDone + "]";
	}
}
