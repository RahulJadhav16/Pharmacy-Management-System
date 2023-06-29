package com.pms.doctor.service.Service;

import org.springframework.web.multipart.MultipartFile;

import com.pms.doctor.service.Models.DoctorProfileImg;

public interface doctorProfileImg {
	
	//Get the profile img 
	DoctorProfileImg getProfileImg(String id);
	
	//Add profile img and update
	DoctorProfileImg uploadImg(String id, MultipartFile file);
	
	

}
