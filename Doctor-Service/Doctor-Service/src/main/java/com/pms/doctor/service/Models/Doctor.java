package com.pms.doctor.service.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="DoctorInfo")
public class Doctor {
	@Id
	private String Id;
	private String name;
	private String contact;
	private String email;
	private String username;
	private String password;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Doctor(String id,String name, String username, String contact, String email, String password) {
		super();
		Id = id;
		this.name = name;
		this.username = username;
		this.contact = contact;
		this.email = email;
		this.password = password;
	}

	public Doctor() {
		super();

	}

	
	

}
