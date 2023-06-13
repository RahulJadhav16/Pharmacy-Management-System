package com.pms.doctor.service.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
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
import com.pms.doctor.service.Models.Pickup;

@RestController
@RequestMapping("/doctor")
public class doctorController {
	@Autowired
	private doctorServiceImpl doctorService;
	
	//To view all the drugs
	@GetMapping("/viewAllDrugs")
	public ResponseEntity<List<Drug>>viewAllDrugs()
	{
		List<Drug> allDrugsList=doctorService.viewAllDrugs();
		
		return ResponseEntity.status(HttpStatus.OK).body(allDrugsList);
	}
	
	//To get the drug by  name
	@GetMapping("/drugByName/{name}")
	public ResponseEntity<List<Drug>> drugByName(@PathVariable String name)
	{
		return ResponseEntity.status(HttpStatus.OK).body(doctorService.drugByName(name));
	}
	
	//To get the drug by  Id
	@GetMapping("/drugById/{Id}")
	public ResponseEntity<Drug> drugById(@PathVariable String Id)
	{
		return ResponseEntity.status(HttpStatus.OK).body(doctorService.drugById(Id));
	}
	
	/////////////////// Orders Section /////////////////////////////////
	//To view all Orders
	@GetMapping("/viewAllOrders/{doctorId}")
	public ResponseEntity<List<Order>>viewAllOrders(@PathVariable String doctorId)
	{
		return ResponseEntity.status(HttpStatus.OK).body(doctorService.viewAllOrders(doctorId));
	}
	
	//To place order
	@PostMapping("/addOrder")
	public ResponseEntity<Order> addOrder(@RequestBody Order orderObj)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.addOrder(orderObj));
	}
	
	//Delete order
	public @DeleteMapping("/deleteOrder/{id}")
	ResponseEntity<String> deleteOrder(@PathVariable String id)
	{
		return ResponseEntity.status(HttpStatus.OK).body(doctorService.deleteOrder(id));
	}
	
	///////////////// pickup section ///////////////////////////////////
	
	//viewAllPickups
	public @GetMapping("/viewAllPickups/{id}")
	ResponseEntity<List<Pickup>>viewAllPickups(@PathVariable String id)
	{
		return ResponseEntity.status(HttpStatus.OK).body(doctorService.viewAllPickups(id));
	}
	
	//Make payment 
	@PutMapping("/makePayment")
	public ResponseEntity<Pickup>makePayment(@RequestBody Pickup obj)
	{
		return ResponseEntity.status(HttpStatus.OK).body(doctorService.makePayment(obj));
	}
	


}
