package com.pms.AdminMicroservice.Service;

import java.util.List;

import com.pms.AdminMicroservice.Model.ContactUs;

public interface ContactUsService {
	
	//create
	ContactUs createAContactUs(ContactUs obj);
	
	
	//get 
	List<ContactUs> getAllContactUs();
	
	//delete
	String deleteContactus(long id);
	
	
	
	

}
