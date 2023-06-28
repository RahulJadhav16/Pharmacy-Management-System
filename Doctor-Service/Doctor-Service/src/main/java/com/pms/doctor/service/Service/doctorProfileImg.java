package com.pms.doctor.service.Service;

import org.springframework.web.multipart.MultipartFile;

import com.pms.doctor.service.Models.DoctorProfileImg;

public interface doctorProfileImg {
	
	//Get the profile img 
	DoctorProfileImg getProfileImg(String id);
	
	//Add profile img
	DoctorProfileImg uploadImg(String id, MultipartFile file);
	
	//update the img 
	DoctorProfileImg updateImg(String id, MultipartFile file);
	
	//delete the img 
	DoctorProfileImg deleteImg(String id);

}
