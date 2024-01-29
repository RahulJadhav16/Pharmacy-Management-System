package com.pms.doctor.service.Models;

import java.util.Date;

public class OtpVerifyModel {
	
	private String mailId;
	private String otp;
	private Date dateOfVerification;
	
	
	public String getMailId() {
		return mailId;
	}
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public Date getDateOfVerification() {
		return dateOfVerification;
	}
	public void setDateOfVerification(Date dateOfVerification) {
		this.dateOfVerification = dateOfVerification;
	}
	public OtpVerifyModel(String mailId, String otp, Date dateOfVerification) {
		super();
		this.mailId = mailId;
		this.otp = otp;
		this.dateOfVerification = dateOfVerification;
	}
	public OtpVerifyModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

	
	
	

}
