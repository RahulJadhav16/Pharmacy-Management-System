package com.chat.system.chatservice.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Chat")
public class Chat {
	@Id
	private String id;
	private Date date;
	private String time;
	private String role;
	private String name;
	private String personID;
	private String message;
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
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPersonID() {
		return personID;
	}
	public void setPersonID(String personID) {
		this.personID = personID;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Chat() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Chat(String id, Date date, String time, String role, String name, String personID, String message) {
		super();
		this.id = id;
		this.date = date;
		this.time = time;
		this.role = role;
		this.name = name;
		this.personID = personID;
		this.message = message;
	}
	
	
	
	
	

}
