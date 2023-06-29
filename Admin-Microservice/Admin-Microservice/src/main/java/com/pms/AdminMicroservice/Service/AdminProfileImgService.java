package com.pms.AdminMicroservice.Service;

import org.springframework.web.multipart.MultipartFile;

import com.pms.AdminMicroservice.Model.AdminProfileImg;

public interface AdminProfileImgService {
	
	//Add profile img for admin
	AdminProfileImg addAdminProfileImg(String id, MultipartFile file);
	
	//get prifile img for admin
	AdminProfileImg getAdminProfileImg(String id);

}
