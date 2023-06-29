package com.pms.drug.inventory.Service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.pms.drug.inventory.Model.DrugImges;

public interface DrugImgesService {
	
	//Add image 
	DrugImges addDrugImg(String id, MultipartFile file);
	
	//get image
	DrugImges getDrugImg(String id);
	
	//get all images
	List<DrugImges>getAllDrugImg();

}
