package com.pms.AdminMicroservice.Service;

import java.util.List;

import com.pms.AdminMicroservice.Model.Drug;

public interface DrugCatalogueService {
	
	/////////////////////  This is for drug catalog  /////////////////////////////////
	//getalldrugs
	List<Drug>getalldrugs();
	
	//getdrugbyid
	Drug getdrugbyid(String id);
	
	//getdrugbyname
	List<Drug>getdrugbyname(String name);
	
	//createdrug
	Drug createdrug(Drug obj);
	
	//updatedrug
	Drug updatedrug(Drug obj);
	
	//deletedrug
	String deletedrug(String id);

}
