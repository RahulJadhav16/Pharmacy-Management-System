package com.pms.doctor.service.Models;

import java.util.Date;





public class Chat {
	
	
	
	
	private String id;
	private Date date;
	private String time;
	private String personID;
	private String role;
	private String name;
	private String message;
	
	
	
	
	public Chat(String id, Date date, String time, String personID, String role, String name, String message) {
		super();
		this.id = id;
		this.date = date;
		this.time = time;
		this.personID = personID;
		this.role = role;
		this.name = name;
		this.message = message;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Chat(String id, Date date, String time, String personID, String name, String message) {
		super();
		this.id = id;
		this.date = date;
		this.time = time;
		this.personID = personID;
		this.name = name;
		this.message = message;
	}
	public String getPersonID() {
		return personID;
	}
	public void setPersonID(String personID) {
		this.personID = personID;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	
	
	public Chat(String id, Date date, String time, String name, String message) {
		super();
		this.id = id;
		this.date = date;
		this.time = time;
		this.name = name;
		this.message = message;
	}
	public Chat() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	

}
