package com.pms.doctor.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.doctor.service.Exception.UserNotFoundByIDException;
import com.pms.doctor.service.Models.Doctor;
import com.pms.doctor.service.Models.DoctorPersonalDetails;
import com.pms.doctor.service.Repository.DoctorPersonalDetailsRepository;
import com.pms.doctor.service.Service.doctorPersonalDetailsService;

@Service
public class DoctorPersonalDetailsImpl implements doctorPersonalDetailsService{
	
	@Autowired
	private DoctorPersonalDetailsRepository doctorRepo;

	@Override
	public DoctorPersonalDetails getDetails(String id) {
		
		return doctorRepo.findById(id).orElseThrow(()->new UserNotFoundByIDException("User not found"));
		
		
	}

}
