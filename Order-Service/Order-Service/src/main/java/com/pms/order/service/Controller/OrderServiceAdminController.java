package com.pms.order.service.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.pms.order.service.Impl.orderServiceAdminImpl;
import com.pms.order.service.Model.order;


@RestController
@RequestMapping("/verifyOrder")
public class OrderServiceAdminController {
	
	@Autowired
	private orderServiceAdminImpl orderService;
	
	
	
	@GetMapping("/allOrders")
	ResponseEntity<List<order>> getAllOrders()
	{
		
		
		return ResponseEntity.status(HttpStatus.OK).body(orderService.getAllOrders());
	}
	
	@GetMapping("/getOrderById/{id}")
	ResponseEntity<order> getOrderById(@PathVariable String id)
	{
		return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrderById(id));
	}
	@GetMapping("/getOrderByDoctorId/{id}")
	ResponseEntity<List<order>> getOrderByDoctorId(@PathVariable String id)
	{
		return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrderByDoctorId(id));
	}
	
	@PutMapping("/verifyOrder")
	ResponseEntity<order>updateOrderStatus(@RequestBody order obj)
	{
		return ResponseEntity.status(HttpStatus.OK).body(orderService.updateOrderStatus(obj));
	}
	
	
	
	
	@GetMapping("/addToPickup")
	ResponseEntity<String> addToPickup()
	{
		return ResponseEntity.status(HttpStatus.OK).body(orderService.addToPickup());
	}

}
