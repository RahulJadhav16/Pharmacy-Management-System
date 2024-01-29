package com.pms.doctor.service.Service;

import com.pms.doctor.service.Models.MailVerification;

public interface doctorEmailVerificationService {
	
	//Get the status;
	MailVerification getDoctorEmailVerification(String id);
	
	
	//Set the verification status 
	MailVerification setDoctorEmailVerification(MailVerification obj);

}
