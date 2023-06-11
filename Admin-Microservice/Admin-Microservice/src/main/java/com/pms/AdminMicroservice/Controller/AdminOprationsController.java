package com.pms.AdminMicroservice.Controller;

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

import com.pms.AdminMicroservice.Impl.DrugCatalogueServiceImpl;
import com.pms.AdminMicroservice.Model.Drug;

@RestController
@RequestMapping("/adminOprations")
public class AdminOprationsController {
	
	@Autowired
	private DrugCatalogueServiceImpl catalogueServiceImpl;
	
	@GetMapping("/getalldrugs")
	ResponseEntity<List<Drug>>getalldrugs()
	{
		return ResponseEntity.status(HttpStatus.OK).body(catalogueServiceImpl.getalldrugs());
	}
	
	@GetMapping("/getdrugbyid/{id}")
	ResponseEntity<Drug>getdrugbyid(@PathVariable String id)
	{
		return ResponseEntity.status(HttpStatus.OK).body(catalogueServiceImpl.getdrugbyid(id));
	}
	
	@GetMapping("/getdrugbyname/{name}")
	ResponseEntity<List<Drug>>getdrugbyname(@PathVariable String name)
	{
		return ResponseEntity.status(HttpStatus.OK).body(catalogueServiceImpl.getdrugbyname(name));
	}
	
	@PostMapping("/createdrug")
	ResponseEntity<Drug>createdrug(@RequestBody Drug obj)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(catalogueServiceImpl.createdrug(obj));
	}
	
	@PutMapping("/updatedrug")
	ResponseEntity<Drug>updatedrug(@RequestBody Drug obj)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(catalogueServiceImpl.updatedrug(obj));
	}
	
	@DeleteMapping("/deletedrug/{id}")
	ResponseEntity<String>deletedrug(@PathVariable String id)
	{
		return ResponseEntity.status(HttpStatus.OK).body(catalogueServiceImpl.deletedrug(id));
	}

	
}
