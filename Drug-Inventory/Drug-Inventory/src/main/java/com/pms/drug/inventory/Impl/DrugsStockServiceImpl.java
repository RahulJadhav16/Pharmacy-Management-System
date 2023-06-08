package com.pms.drug.inventory.Impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.pms.drug.inventory.Exception.DrugNotFoundByExpiryDate;
import com.pms.drug.inventory.Exception.InvalidexpireDate;
import com.pms.drug.inventory.Exception.DrugNotFoundById;
import com.pms.drug.inventory.Exception.DrugAlreadyAddedException;
import com.pms.drug.inventory.Exception.DrugNotFoundByBatchId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.drug.inventory.Exception.DrugNotFoundByname;
import com.pms.drug.inventory.Model.Drug;
import com.pms.drug.inventory.Model.DrugsStock;
import com.pms.drug.inventory.Repository.DrugsStockRepository;
import com.pms.drug.inventory.Repository.drugRepository;
import com.pms.drug.inventory.Service.DrugsStockService;

@Service
public class DrugsStockServiceImpl implements DrugsStockService{
	
	@Autowired
	private DrugsStockRepository drugsRepo;

	@Override
	public List<DrugsStock> getAll() {
		// TODO Auto-generated method stub
		LocalDate today = LocalDate.now();
		LocalDate expireDate = null;
		List<DrugsStock> allDrugsInfo=drugsRepo.findAll();
		
		for(DrugsStock e:allDrugsInfo)
		{
			expireDate=e.getExpireDate();
			if (expireDate.isBefore(today)) {
	            e.setStatus("Expired");
	        } else if (expireDate.isEqual(today)) {
	            System.out.println("Expiring today");
	        } else {
	        	e.setStatus("Not expired");
	        }
		}
		
		return allDrugsInfo;
	}

	@Override
	public List<DrugsStock> getByDrugName(String drugName) {
		// TODO Auto-generated method stub
		 List<DrugsStock> allDrugsInfo = drugsRepo.findByDrugNameIgnoreCase(drugName);
		    
		    if (allDrugsInfo.isEmpty()) {
		        throw new DrugNotFoundByname("Drug Not Found By Given Name !");
		    } else {
		    	LocalDate today = LocalDate.now();
				LocalDate expireDate = null;
		    	for(DrugsStock e:allDrugsInfo)
				{
					expireDate=e.getExpireDate();
					if (expireDate.isBefore(today)) {
			            e.setStatus("Expired");
			        } else if (expireDate.isEqual(today)) {
			            System.out.println("Expiring today");
			        } else {
			        	e.setStatus("Not expired");
			        }
				}
				
				return allDrugsInfo;
		    }
	}

	@Override
	public List<DrugsStock> getByExpireDate(LocalDate date) {
		// TODO Auto-generated method stub
		List<DrugsStock> allDrugsInfo = drugsRepo.findByExpireDate(date);
	    
	    if (allDrugsInfo.isEmpty()) {
	        throw new DrugNotFoundByExpiryDate("Drug Not Found By Given Date !");
	    } else {
	    	LocalDate today = LocalDate.now();
			LocalDate expireDate = null;
	    	for(DrugsStock e:allDrugsInfo)
			{
				expireDate=e.getExpireDate();
				if (expireDate.isBefore(today)) {
		            e.setStatus("Expired");
		        } else if (expireDate.isEqual(today)) {
		            System.out.println("Expiring today");
		        } else {
		        	e.setStatus("Not expired");
		        }
			}
			
			return allDrugsInfo;
	    }
	}

	@Override
	public List<DrugsStock> getByBatchNo(String batchNo) {
		// TODO Auto-generated method stub
        List<DrugsStock> allDrugsInfo = drugsRepo.findByBatchId(batchNo);
	    
	    if (allDrugsInfo.isEmpty()) {
	        throw new DrugNotFoundByBatchId("Drugs are  not found by given Batch id !");
	    } else {
	    	LocalDate today = LocalDate.now();
			LocalDate expireDate = null;
	    	for(DrugsStock e:allDrugsInfo)
			{
				expireDate=e.getExpireDate();
				if (expireDate.isBefore(today)) {
		            e.setStatus("Expired");
		        } else if (expireDate.isEqual(today)) {
		        	 e.setStatus("Expiring today");
		        } else {
		        	e.setStatus("Not expired");
		        }
			}
			
			return allDrugsInfo;
	    }
	}	

	@Override
	public DrugsStock createDrugsStock(DrugsStock drugsStock) {
		
		//Here i am making sure that duplicate drugs not be added
		String drugName = drugsStock.getDrugName();
	    List<DrugsStock> drugs = drugsRepo.findAll();
	    boolean flag = false;
	    
	    for (DrugsStock e : drugs) {
	        if (drugName.equals(e.getDrugName())) {
	            flag = true;
	            break;  // Optional: exit the loop since a match is found
	        }
	    }
	    
	    if (flag) {
	        throw new DrugAlreadyAddedException("Drug Is Already added with the same name!");
	    } else {
	    	
	    	LocalDate today = LocalDate.now();
			LocalDate expireDate=drugsStock.getExpireDate();
			if (expireDate.isBefore(today)) {
				throw new InvalidexpireDate("Expire date should be greater than todays date !");
				
	        } else if (expireDate.isEqual(today)) {
	        	drugsStock.setStatus("Expiring today");
	        	drugsRepo.save(drugsStock);
	        	return drugsStock;
	        } else {
	        	drugsStock.setStatus("Not expired");
	        	drugsRepo.save(drugsStock);
	        	return drugsStock;
	        }
	        
	    }
			
	}

	@Override
	public DrugsStock updateDrugsStock(DrugsStock drugsStock) {
		// TODO Auto-generated method stub
		String id=drugsStock.getId();
		drugsRepo.findById(id).orElseThrow(()->new DrugNotFoundById("Drug Not Found By Given Id !"));
		LocalDate today = LocalDate.now();
		LocalDate expireDate=drugsStock.getExpireDate();
		if (expireDate.isAfter(today)) {
			drugsStock.setStatus("not expired");
			return drugsRepo.save(drugsStock);
        } else {
            throw new InvalidexpireDate("Expire date should be greater than todays date !");
        }
		
	}

	@Override
	public String deleteDrugsStocById(String id) {
		Optional<DrugsStock> optionalDrug = drugsRepo.findById(id);
	    
	    if (optionalDrug.isPresent()) {
	    	drugsRepo.deleteById(id);
	        return "Record Deleted!";
	    } else {
	        throw new DrugNotFoundById("Drug Not Found By Given Id !");
	    }
	
	}
	

}
