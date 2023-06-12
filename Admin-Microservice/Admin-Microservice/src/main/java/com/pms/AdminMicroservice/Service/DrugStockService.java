package com.pms.AdminMicroservice.Service;

import java.time.LocalDate;
import java.util.List;

import com.pms.AdminMicroservice.Model.DrugsStock;

public interface DrugStockService {
	
	//getAllStock
	List<DrugsStock>getAllStock();
	
	//getByDrugName
	List<DrugsStock> getByDrugName(String name);
	
	//getByExpireDate
	List<DrugsStock>getByExpireDate(LocalDate date);
	
	//getByBatchNo
	List<DrugsStock>getByBatchNo(String batchNo);
	
	//createStock
	DrugsStock createStock(DrugsStock obj);
	
	//updateStock
	DrugsStock updateStock(DrugsStock obj);
	
	//deleteStock
	String deleteStock(String id);

}
