package com.pms.drug.inventory.Impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pms.drug.inventory.Model.DrugImges;
import com.pms.drug.inventory.Repository.drugImgesRepository;
import com.pms.drug.inventory.Service.DrugImgesService;

@Service
public class drugImagesImpl implements DrugImgesService{
   
	@Autowired
	private drugImgesRepository repo;
	

	@Override
	public DrugImges getDrugImg(String id) {
		
		return repo.findById(id).orElse(null);
	}

	@Override
	public DrugImges addDrugImg(String id, MultipartFile file) {
		DrugImges obj=new DrugImges();
		obj.setId(id);
		try {
			obj.setImage(file.getBytes());
			repo.save(obj);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public List<DrugImges> getAllDrugImg() {
		
		return repo.findAll();
	}
	

}
