package com.pms.order.service.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


public class DoctorPersonalDetails {
	
	private String id;
	private String name;
	private String contact;
	private String email;
	private String address;
	private boolean doctorVerification=false;
	
	
	public DoctorPersonalDetails(String id, String name, String contact, String email, String address,
			boolean doctorVerification) {
		super();
		this.id = id;
		this.name = name;
		this.contact = contact;
		this.email = email;
		this.address = address;
		this.doctorVerification = doctorVerification;
	}
	public boolean isDoctorVerification() {
		return doctorVerification;
	}
	public void setDoctorVerification(boolean doctorVerification) {
		this.doctorVerification = doctorVerification;
	}
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public DoctorPersonalDetails(String id, String name, String contact, String email, String address) {
		super();
		this.id = id;
		this.name = name;
		this.contact = contact;
		this.email = email;
		this.address = address;
	}
	public DoctorPersonalDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	

}
