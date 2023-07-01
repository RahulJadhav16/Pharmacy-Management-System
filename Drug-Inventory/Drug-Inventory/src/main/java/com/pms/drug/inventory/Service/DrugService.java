package com.pms.drug.inventory.Service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.pms.drug.inventory.Model.Drug;

public interface DrugService {
	
	//get all
	List<Drug> getAll();
	
	//get by id
	Drug getDrugById(String id);
	
	
	//get by name
	List<Drug> getDrugsByName(String name);

	//add drug
	Drug addDrug(Drug drug);
	
	
	//update drug
	Drug updateDrug(Drug drug);
	
	
	
	//delete drug
	String deleteDrug(String drug);
	
	
	
}
