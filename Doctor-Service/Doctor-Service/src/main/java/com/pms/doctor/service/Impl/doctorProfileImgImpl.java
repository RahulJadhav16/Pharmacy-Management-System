package com.pms.doctor.service.Impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pms.doctor.service.Models.DoctorProfileImg;
import com.pms.doctor.service.Repository.ImageRepository;
import com.pms.doctor.service.Service.doctorProfileImg;

@Service
public class doctorProfileImgImpl implements doctorProfileImg{
	
	@Autowired
	private ImageRepository repo;

	@Override
	public DoctorProfileImg uploadImg(String id, MultipartFile file) {
		// TODO Auto-generated method stub
		DoctorProfileImg  doctor=new DoctorProfileImg();
		doctor.setId(id);
		try {
			doctor.setImage(file.getBytes());
			repo.save(doctor);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return doctor;
	}

	

	//Get profile img
	@Override
	public DoctorProfileImg getProfileImg(String id) {
		
		return repo.findById(id).orElse(null);
	}


}
