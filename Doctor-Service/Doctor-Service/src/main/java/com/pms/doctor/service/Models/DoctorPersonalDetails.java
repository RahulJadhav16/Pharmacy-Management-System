package com.pms.doctor.service.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("DoctorPersonalInfo")
public class DoctorPersonalDetails {
	@Id
	private String id;
	private String name;
	private String contact;
	private String email;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public DoctorPersonalDetails(String id, String name, String contact, String email) {
		super();
		this.id = id;
		this.name = name;
		this.contact = contact;
		this.email = email;
	}
	public DoctorPersonalDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
