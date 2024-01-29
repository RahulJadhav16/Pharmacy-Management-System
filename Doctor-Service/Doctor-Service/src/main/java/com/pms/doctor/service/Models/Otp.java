package com.pms.doctor.service.Models;



import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.internal.connection.Time;

@Document("Otp")
public class Otp {
	
	@Id
	private String id;
	private String OTP;
	private Date otpSentDate;
	
	public Otp(String id, String oTP, Date otpSentDate) {
		super();
		this.id = id;
		OTP = oTP;
		this.otpSentDate = otpSentDate;
	}
	
	
	public Otp() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOTP() {
		return OTP;
	}
	public void setOTP(String oTP) {
		OTP = oTP;
	}
	public Date getOtpSentDate() {
		return otpSentDate;
	}
	public void setOtpSentDate(Date otpSentDate) {
		this.otpSentDate = otpSentDate;
	}
	
	
	
	
	
	

}
