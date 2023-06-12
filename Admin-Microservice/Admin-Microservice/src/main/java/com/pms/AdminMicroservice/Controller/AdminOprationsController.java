package com.pms.AdminMicroservice.Controller;

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

import com.pms.AdminMicroservice.Impl.DrugCatalogueServiceImpl;
import com.pms.AdminMicroservice.Impl.DrugStockServiceImpl;
import com.pms.AdminMicroservice.Impl.PickupServiceImpl;
import com.pms.AdminMicroservice.Impl.VerifyOrderServiceImpl;
import com.pms.AdminMicroservice.Model.Drug;
import com.pms.AdminMicroservice.Model.DrugsStock;
import com.pms.AdminMicroservice.Model.Order;
import com.pms.AdminMicroservice.Model.Pickup;

@RestController
@RequestMapping("/adminOprations")
public class AdminOprationsController {
	
	@Autowired
	private DrugCatalogueServiceImpl catalogueServiceImpl;
	
	@Autowired
	private DrugStockServiceImpl drugStockServiceImpl;
	
	@Autowired
	private VerifyOrderServiceImpl verifyOrderServiceImpl;
	
	
	@Autowired
	private PickupServiceImpl pickupServiceImpl;
	
	/////////////////// These End points are for DrugsCatelog //////////////////////////////
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
	
	//////////////////////////////  These endpoints are for Drug Stock ////////////////////////
	
	@GetMapping("/getAllStock")
	ResponseEntity<List<DrugsStock>>getAllStock()
	{
		return ResponseEntity.status(HttpStatus.OK).body(drugStockServiceImpl.getAllStock());
	}
	
	@GetMapping("/getByDrugName/{name}")
	ResponseEntity<List<DrugsStock>>getByDrugName(@PathVariable String name)
	{
		return ResponseEntity.status(HttpStatus.OK).body(drugStockServiceImpl.getByDrugName(name));
	}
	
	@GetMapping("/getByExpireDate/{date}")
	ResponseEntity<List<DrugsStock>>getByExpireDate(@PathVariable LocalDate date)
	{
		return ResponseEntity.status(HttpStatus.OK).body(drugStockServiceImpl.getByExpireDate(date));
	}
	
	
	@GetMapping("/getByBatchNo/{name}")
	ResponseEntity<List<DrugsStock>>getByBatchNo(@PathVariable String name)
	{
		return ResponseEntity.status(HttpStatus.OK).body(drugStockServiceImpl.getByBatchNo(name));
	}
	
	@PostMapping("/createStock")
	ResponseEntity<DrugsStock>createStock(@RequestBody DrugsStock obj)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(drugStockServiceImpl.createStock(obj));
	}
	
	@PutMapping("/updateStock")
	ResponseEntity<DrugsStock>updateStock(@RequestBody DrugsStock obj)
	{
		return ResponseEntity.status(HttpStatus.OK).body(drugStockServiceImpl.updateStock(obj));
	}
	
	@DeleteMapping("/deleteStock/{id}")
	ResponseEntity<String>deleteStock(@PathVariable String id)
	{
		return ResponseEntity.status(HttpStatus.OK).body(drugStockServiceImpl.deleteStock(id));
	}
	
	
	/////////////////////////These end points are for verify the order /////////////////////
	
	@GetMapping("/allOrders")
	ResponseEntity<List<Order>>allOrders()
	{
		return ResponseEntity.status(HttpStatus.OK).body(verifyOrderServiceImpl.allOrders());
	}
	
	
	
	@GetMapping("/getOrderByDoctorId/{id}")
	ResponseEntity<List<Order>>getOrderByDoctorId(@PathVariable String id)
	{
		return ResponseEntity.status(HttpStatus.OK).body(verifyOrderServiceImpl.getOrderByDoctorId(id));
	}
	
	
	
	@GetMapping("/getOrderById/{id}")
	ResponseEntity<Order>getOrderById(@PathVariable String id)
	{
		return ResponseEntity.status(HttpStatus.OK).body(verifyOrderServiceImpl.getOrderById(id));
	}
	
	@PutMapping("/verifyOrder")
	ResponseEntity<Order>verifyOrder(@RequestBody Order obj)
	{
		return ResponseEntity.status(HttpStatus.OK).body(verifyOrderServiceImpl.verifyOrder(obj));
	}
	
	
	
	//////////////////////////////These endpoints are for pickupService /////////////////////
	
	
	@GetMapping("/getAll")
	ResponseEntity<List<Pickup>>getAll()
	{
		return ResponseEntity.status(HttpStatus.OK).body(pickupServiceImpl.getAll());
	}
	
	@GetMapping("/getByDoctorId/{id}")
	ResponseEntity<List<Pickup>>getByDoctorId(@PathVariable String id)
	{
		return ResponseEntity.status(HttpStatus.OK).body(pickupServiceImpl.getByDoctorId(id));
	}
	
	
	@GetMapping("/getByPickupId/{id}")
	ResponseEntity<Pickup>getByPickupId(@PathVariable String id)
	{
		return ResponseEntity.status(HttpStatus.OK).body(pickupServiceImpl.getByPickupId(id));
	}
	
	
	@GetMapping("/getPickupPaymentDone")
	ResponseEntity<List<Pickup>>getPickupPaymentDone()
	{
		return ResponseEntity.status(HttpStatus.OK).body(pickupServiceImpl.getPickupPaymentDone());
	}
	
	
	@GetMapping("/getPickupPaymentNotDone")
	ResponseEntity<List<Pickup>>getPickupPaymentNotDone()
	{
		return ResponseEntity.status(HttpStatus.OK).body(pickupServiceImpl.getPickupPaymentNotDone());
	}

	
}
