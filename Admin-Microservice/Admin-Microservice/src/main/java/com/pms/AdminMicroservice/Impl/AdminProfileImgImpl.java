package com.pms.AdminMicroservice.Impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pms.AdminMicroservice.Model.AdminProfileImg;
import com.pms.AdminMicroservice.Repository.AdminProfileImgRepository;
import com.pms.AdminMicroservice.Service.AdminProfileImgService;


@Service
public class AdminProfileImgImpl implements AdminProfileImgService{
	
	
	@Autowired
	private AdminProfileImgRepository repo;

	@Override
	public AdminProfileImg addAdminProfileImg(String id, MultipartFile file) {
		AdminProfileImg  admin=new AdminProfileImg();
		admin.setId(id);
		try {
			admin.setImage(file.getBytes());
			repo.save(admin);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return admin;
		
	}

	@Override
	public AdminProfileImg getAdminProfileImg(String id) {
		
		return repo.findById(id).orElse(null);
	}

}
