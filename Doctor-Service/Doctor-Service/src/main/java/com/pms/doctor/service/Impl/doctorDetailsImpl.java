package com.pms.doctor.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pms.doctor.service.Exception.EmailIdAlreadyExistsException;
import com.pms.doctor.service.Exception.UserNotFoundByIDException;
import com.pms.doctor.service.Models.Doctor;
import com.pms.doctor.service.Models.DoctorPersonalDetails;
import com.pms.doctor.service.Repository.DoctorPersonalDetailsRepository;
import com.pms.doctor.service.Repository.doctorRepository;
import com.pms.doctor.service.Service.doctorDetailsService;
import com.pms.doctor.service.Service.doctorService;

@Service
public class doctorDetailsImpl implements doctorDetailsService{
	
	@Autowired
	private doctorRepository doctorRepo;
	
	@Autowired
	private DoctorPersonalDetailsRepository doctorPersonalDetailsRepository;
	
	@Autowired 
	private PasswordEncoder passwordEncoder;
    
	DoctorPersonalDetails doctorPersonalDetails=new DoctorPersonalDetails();
	@Override
	public Doctor addDetails(Doctor doctorobj) {
		// TODO Auto-generated method stub
		boolean Emailcheck=false;	
		List<Doctor> doctorList=doctorRepo.findAll();
		for(Doctor e:doctorList)
		{
			if (e.getEmail().toLowerCase().equals(doctorobj.getEmail().toLowerCase()))
			{
				Emailcheck=true;
				break;
			}
		}
		
		if(Emailcheck)
		{
			throw new EmailIdAlreadyExistsException("The given Email Id already Exists!");
		}
		else {
	    
		
		//Hashing the password
		doctorobj.setPassword(passwordEncoder.encode(doctorobj.getPassword()));
		doctorRepo.save(doctorobj);
		DoctorPersonalDetails doctorPersonalDetails=new DoctorPersonalDetails(doctorobj.getDoctorId(),doctorobj.getName(),doctorobj.getContact(),doctorobj.getEmail(),doctorobj.getAddress());
		doctorPersonalDetailsRepository.save(doctorPersonalDetails);
		return doctorobj;
		}
	}
	@Override
	public Doctor updateDetails(Doctor doctorobj) {
		
//		//HAshing updated password
		doctorobj.setPassword(passwordEncoder.encode(doctorobj.getPassword()));	
		
		
		doctorRepo.save(doctorobj);
		
		//Updating data in userPersonalInfo
		DoctorPersonalDetails doctorPersonalDetails=new DoctorPersonalDetails(doctorobj.getDoctorId(),doctorobj.getName(),doctorobj.getContact(),doctorobj.getEmail(),doctorobj.getAddress());
		doctorPersonalDetailsRepository.save(doctorPersonalDetails);
		return doctorobj;
	}
	@Override
	public String getDoctoridBymail(String email) {
		// TODO Auto-generated method stub
		
		Doctor obj= doctorRepo.findByEmail(email);
		return obj.getDoctorId();
	}



	
	

}
