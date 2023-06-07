package com.pms.drug.inventory.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.drug.inventory.Exception.DrugAlreadyAddedException;
import com.pms.drug.inventory.Exception.DrugNotFoundById;
import com.pms.drug.inventory.Exception.DrugNotFoundByname;
import com.pms.drug.inventory.Model.Drug;
import com.pms.drug.inventory.Repository.drugRepository;
import com.pms.drug.inventory.Service.DrugService;

@Service
public class drugInventoryImpl implements DrugService{
	
	@Autowired
	private drugRepository repo;

	@Override
	public List<Drug> getAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Drug getDrugById(String id) {
		// TODO Auto-generated method stub
		return repo.findById(id).orElseThrow(()->new DrugNotFoundById("Drug Not Found By Given Id !"));
	}

	@Override
	public List<Drug> getDrugsByName(String name) {
		 List<Drug> drugs = repo.findByName(name);
		    
		    if (drugs.isEmpty()) {
		        throw new DrugNotFoundByname("Drug Not Found By Given Name !");
		    } else {
		        return drugs;
		    }
	}

	@Override
	public Drug addDrug(Drug drug) {
		String drugName = drug.getName();
	    List<Drug> drugs = repo.findAll();
	    boolean flag = false;
	    
	    for (Drug e : drugs) {
	        if (drugName.equals(e.getName())) {
	            flag = true;
	            break;  // Optional: exit the loop since a match is found
	        }
	    }
	    
	    if (flag) {
	        throw new DrugAlreadyAddedException("Drug Is Already added with the same name!");
	    } else {
	        return repo.save(drug);
	    }
		
		
	}

	@Override
	public Drug updateDrug(Drug drug) {
		// TODO Auto-generated method stub
		String id=drug.getId();
		repo.findById(id).orElseThrow(()->new DrugNotFoundById("Drug Not Found By Given Id !"));
		
		return repo.save(drug);
	}

	@Override
	public String deleteDrug(String id) {
		 Optional<Drug> optionalDrug = repo.findById(id);
		    
		    if (optionalDrug.isPresent()) {
		        repo.deleteById(id);
		        return "Record Deleted!";
		    } else {
		        throw new DrugNotFoundById("Drug Not Found By Given Id !");
		    }
		

		
	}

}
