package com.pms.doctor.service.Controller;

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

import com.pms.doctor.service.Impl.doctorServiceImpl;
import com.pms.doctor.service.Models.Drug;
import com.pms.doctor.service.Models.Order;

@RestController
@RequestMapping("/doctor")
public class doctorController {
	@Autowired
	private doctorServiceImpl doctorService;
	
	//To view all the drugs
	@GetMapping("/viewAllDrugs")
	ResponseEntity<List<Drug>>viewAllDrugs()
	{
		List<Drug> allDrugsList=doctorService.viewAllDrugs();
		
		return ResponseEntity.status(HttpStatus.OK).body(allDrugsList);
	}
	
	//To get the drug by  name
	@GetMapping("/drugByName/{name}")
	ResponseEntity<List<Drug>> drugByName(@PathVariable String name)
	{
		return ResponseEntity.status(HttpStatus.OK).body(doctorService.drugByName(name));
	}
	
	//To get the drug by  Id
	@GetMapping("/drugById/{Id}")
	ResponseEntity<Drug> drugById(@PathVariable String Id)
	{
		return ResponseEntity.status(HttpStatus.OK).body(doctorService.drugById(Id));
	}
	
	//To view all Orders
	@GetMapping("/viewAllOrders/{doctorId}")
	ResponseEntity<List<Order>>viewAllOrders(@PathVariable String doctorId)
	{
		return ResponseEntity.status(HttpStatus.OK).body(doctorService.viewAllOrders(doctorId));
	}
	
	//To place order
	@PostMapping("/addOrder")
	ResponseEntity<Order> addOrder(@RequestBody Order orderObj)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.addOrder(orderObj));
	}
	
	//Delete order
	@DeleteMapping("/deleteOrder/{id}")
	ResponseEntity<String> deleteOrder(@PathVariable String id)
	{
		return ResponseEntity.status(HttpStatus.OK).body(doctorService.deleteOrder(id));
	}
	
	


}
