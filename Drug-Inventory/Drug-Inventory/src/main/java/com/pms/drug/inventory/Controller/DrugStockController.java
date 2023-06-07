package com.pms.drug.inventory.Controller;

import java.time.LocalDate;
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

import com.pms.drug.inventory.Impl.DrugsStockServiceImpl;
import com.pms.drug.inventory.Model.Drug;
import com.pms.drug.inventory.Model.DrugsStock;

@RestController
@RequestMapping("/stock")
public class DrugStockController {
	
	@Autowired
	private DrugsStockServiceImpl drugsService;
	
	
	@GetMapping("/getAllStock")
	public ResponseEntity<List<DrugsStock>>getAll()
	{
		return ResponseEntity.status(HttpStatus.OK).body(drugsService.getAll());
	}
	
	@GetMapping("/getByDrugName/{name}")
	public ResponseEntity<List<DrugsStock>>getDrugByNme(@PathVariable String name)
	{
		return ResponseEntity.status(HttpStatus.OK).body(drugsService.getByDrugName(name));
	}
	
	@GetMapping("/getByExpireDate/{date}")
	public ResponseEntity<List<DrugsStock>>getByExpireDate(@PathVariable LocalDate date)
	{
		return ResponseEntity.status(HttpStatus.OK).body(drugsService.getByExpireDate(date));
	}
	
	@GetMapping("/getByBatchNo/{batchNo}")
	public ResponseEntity<List<DrugsStock>>getByBatchNo(@PathVariable String batchNo)
	{
		return ResponseEntity.status(HttpStatus.OK).body(drugsService.getByBatchNo(batchNo));
	}
	
	@PostMapping("/create")
	public ResponseEntity<DrugsStock>createDrugsStock(@RequestBody DrugsStock drugsStock)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(drugsService.createDrugsStock(drugsStock));
	}
	
	@PutMapping("/update")
	public ResponseEntity<DrugsStock>updateDrugsStock(@RequestBody DrugsStock drugsStock)
	{
		return ResponseEntity.status(HttpStatus.OK).body(drugsService.updateDrugsStock(drugsStock));
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String>deleteDrugsStocById(@PathVariable String id)
	{
		return ResponseEntity.status(HttpStatus.OK).body(drugsService.deleteDrugsStocById(id));
	}
	

}
