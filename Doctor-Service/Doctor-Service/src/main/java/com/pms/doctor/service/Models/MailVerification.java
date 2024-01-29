package com.pms.doctor.service.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("EmailVerification")
public class MailVerification {
	
	@Id
	private String id;
	private boolean isEmailVerified;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isEmailVerified() {
		return isEmailVerified;
	}
	public void setEmailVerified(boolean isEmailVerified) {
		this.isEmailVerified = isEmailVerified;
	}
	public MailVerification(String id, boolean isEmailVerified) {
		super();
		this.id = id;
		this.isEmailVerified = isEmailVerified;
	}
	public MailVerification() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	

}
