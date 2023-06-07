package com.pms.drug.inventory.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pms.drug.inventory.Impl.drugInventoryImpl;
import com.pms.drug.inventory.Model.Drug;

@RestController
@RequestMapping("/drugs")
public class DrugController {
	
	@Autowired
	private drugInventoryImpl drugImpl;
	
	@GetMapping("/getalldrugs")
	public ResponseEntity<List<Drug>>getAll()
	{
		return ResponseEntity.status(HttpStatus.OK).body(drugImpl.getAll());
	}
	
	@GetMapping("/getdrugbyname/{name}")
	public ResponseEntity<List<Drug>> getDrugsByName(@PathVariable String name)
	{
		return ResponseEntity.status(HttpStatus.OK).body(drugImpl.getDrugsByName(name));
	}
	
	@GetMapping("/getdrugbyid/{id}")
	public ResponseEntity<Drug> getDrugsById(@PathVariable String id)
	{
		return ResponseEntity.status(HttpStatus.OK).body(drugImpl.getDrugById(id));
	}
	
	@PostMapping("/createdrug")
	public ResponseEntity<Drug> addDrug(@RequestBody Drug drug)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(drugImpl.addDrug(drug));
	}
	
	@PutMapping("/updatedrug")
	public ResponseEntity<Drug> updateDrug(@RequestBody Drug drug)
	{
		return ResponseEntity.status(HttpStatus.OK).body(drugImpl.updateDrug(drug));
	}
	
	@DeleteMapping("/deletedrug/{id}")
	public ResponseEntity<String> deleteDrug(@PathVariable String id)
	{
		return ResponseEntity.status(HttpStatus.OK).body(drugImpl.deleteDrug(id));
	}
	
	
	

}
