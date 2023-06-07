package com.pms.drug.inventory.Service;
import java.time.LocalDate;
import java.util.List;

import com.pms.drug.inventory.Model.DrugsStock;

public interface DrugsStockService {
	
	//Get all
	List<DrugsStock> getAll();
	
	//get By name
	List<DrugsStock> getByDrugName(String drugName);
	
	//get By Expire date
	List<DrugsStock> getByExpireDate(LocalDate date);
	
	//get drugs by batchNo
	List<DrugsStock> getByBatchNo(String batchNo);
	
	
	//post
	DrugsStock createDrugsStock(DrugsStock drugsStock);
	
	//put
	DrugsStock updateDrugsStock(DrugsStock drugsStock);
	
	//Delete
	String deleteDrugsStocById(String id);
	
	
	
	

}
