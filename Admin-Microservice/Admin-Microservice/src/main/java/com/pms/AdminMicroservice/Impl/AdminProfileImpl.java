package com.pms.AdminMicroservice.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pms.AdminMicroservice.Exception.UserEmailIdAlreadyPresentException;
import com.pms.AdminMicroservice.Model.AdminDetails;
import com.pms.AdminMicroservice.Repository.AdminDetailsRepository;
import com.pms.AdminMicroservice.Service.AdminProfile;

@Service
public class AdminProfileImpl implements AdminProfile{
	
	
	@Autowired
	private AdminDetailsRepository repo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
    
	
	//This method will create the admin user
	@Override
	public AdminDetails createAdmin(AdminDetails obj) {
		
		List<AdminDetails> allDetails=repo.findAll();
		
		boolean checkEmailID=false;
		
		for(AdminDetails e:allDetails)
		{
			if(e.getEmail().equals(obj.getEmail()))
			{
				checkEmailID=true;
				break;
			}
		}
		if(checkEmailID)
		{
			throw new UserEmailIdAlreadyPresentException("User email already present");
			
		}
		else {
			
			obj.setPassword(passwordEncoder.encode(obj.getPassword()));
			
			return repo.save(obj);
			
		}

		
		
	}


	@Override
	public AdminDetails getAdminDetails(String email) {
		// TODO Auto-generated method stub
		AdminDetails obj=repo.findByEmail(email);
	    //for security setting password null
		obj.setPassword(null);
		
		return obj;
	}


	@Override
	public AdminDetails updateAdmin(AdminDetails obj) {
		
		obj.setPassword(passwordEncoder.encode(obj.getPassword()));
		
		return repo.save(obj);
	}


	
}
