package com.pms.doctor.service.Service;





import com.pms.doctor.service.Models.Doctor;

public interface doctorDetailsService {
	
	//add details
	Doctor addDetails(Doctor doctorobj);
	
	//Update details
	Doctor updateDetails(Doctor doctorobj);
	
	//get doctor id by mail
	String getDoctoridBymail(String email);
	
	

}
