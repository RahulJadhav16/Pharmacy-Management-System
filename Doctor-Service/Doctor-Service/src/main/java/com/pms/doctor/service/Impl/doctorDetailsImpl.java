package com.pms.doctor.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    
	DoctorPersonalDetails doctorPersonalDetails=new DoctorPersonalDetails();
	@Override
	public Doctor addDetails(Doctor doctorobj) {
		// TODO Auto-generated method stub
	
		Doctor obj= doctorRepo.save(doctorobj);
		DoctorPersonalDetails doctorPersonalDetails=new DoctorPersonalDetails(obj.getDoctorid(),obj.getName(),obj.getContact(),obj.getEmail());
		doctorPersonalDetailsRepository.save(doctorPersonalDetails);
		return obj;
	}

	@Override
	public Doctor updateDetails(Doctor doctorobj) {
		Doctor obj= doctorRepo.save(doctorobj);
		DoctorPersonalDetails doctorPersonalDetails=new DoctorPersonalDetails(obj.getDoctorid(),obj.getName(),obj.getContact(),obj.getEmail());
		doctorPersonalDetailsRepository.save(doctorPersonalDetails);
		return obj;
	}

	
	

}
