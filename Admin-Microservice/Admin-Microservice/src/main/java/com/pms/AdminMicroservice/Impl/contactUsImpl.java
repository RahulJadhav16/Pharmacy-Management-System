package com.pms.AdminMicroservice.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.AdminMicroservice.Model.ContactUs;
import com.pms.AdminMicroservice.Repository.ContactUsRepo;
import com.pms.AdminMicroservice.Service.ContactUsService;

@Service
public class contactUsImpl implements ContactUsService{
	
	@Autowired
	private ContactUsRepo repo;
	

	@Override
	public ContactUs createAContactUs(ContactUs obj) {
		// TODO Auto-generated method stub
		return repo.save(obj);
	}

	@Override
	public List<ContactUs> getAllContactUs() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public String deleteContactus(long id) {
		// TODO Auto-generated method stub
		 repo.deleteById(id);
		 return "Deleted";
	}

}
