package com.pms.doctor.service.Service;

import com.pms.doctor.service.Models.Otp;
import com.pms.doctor.service.Models.OtpVerifyModel;

public interface OptVerify {
	
	//Create 
	public Otp SendOpt(String Email);
	
	//Verify OPt
	public String VerifyOpt(OtpVerifyModel obj);
	
	

}
