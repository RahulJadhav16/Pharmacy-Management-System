package com.pms.pickup.service.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pms.pickup.service.Model.PaymentDetails;
import com.pms.pickup.service.Model.Pickup;
import com.pms.pickup.service.Service.pickupServiceDoctor;

@RestController
@RequestMapping("/pickupDoctor")
public class pickupControllerDoctor {
	
	
	@Autowired
	private pickupServiceDoctor service;
	
	
	@GetMapping("/getAllPickups/{id}")
	public ResponseEntity< List<Pickup>> getAllPickups(@PathVariable String id)
	{
		return ResponseEntity.status(HttpStatus.OK).body(service.getAllPickups(id));
	}
	
	
	@PutMapping("/makePayment")
	public ResponseEntity<Pickup> makePayment(@RequestBody Pickup obj)
	{
		return ResponseEntity.status(HttpStatus.OK).body(service.makePayment(obj));
	}
	
	@PostMapping("/addPaymentDetails")
	public ResponseEntity<PaymentDetails>addPaymentDetails(@RequestBody PaymentDetails obj)
	{
		return ResponseEntity.status(HttpStatus.OK).body(service.addPaymentDetails(obj));
	}
	
	

}
