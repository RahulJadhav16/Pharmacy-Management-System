package com.pms.pickup.service.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.DeleteExchange;

import com.pms.pickup.service.Impl.pickupServiceDoctorImpl;
import com.pms.pickup.service.Impl.pickupServiceImpl;
import com.pms.pickup.service.Model.PaymentDetails;
import com.pms.pickup.service.Model.Pickup;

@RestController
@RequestMapping("/pickupAdmin")
public class pickupControllerAdmin {
	@Autowired
	private pickupServiceImpl pickupservice;
	
	@Autowired
	private pickupServiceDoctorImpl doctorimpl;
	
	
	@GetMapping("/getAllPickups")
	public ResponseEntity<List<Pickup>> getAll()
	{
		return ResponseEntity.status(HttpStatus.OK).body(pickupservice.getAll());
	}
	
	
	@GetMapping("/getByPickupId/{id}")
	public ResponseEntity<Pickup> getByPickupId(@PathVariable String id)
	{
		return ResponseEntity.status(HttpStatus.OK).body(pickupservice.getByPickupId(id));
	}
	
	
	@GetMapping("/getByDoctorId/{id}")
	public ResponseEntity<List<Pickup>> getByDoctorId(@PathVariable String id)
	{
		return ResponseEntity.status(HttpStatus.OK).body(pickupservice.getByDoctorId(id));
	}
	
	
	
	@GetMapping("/getPickupPaymentDone")
	public ResponseEntity<List<Pickup>> getPickupPaymentDone()
	{
		return ResponseEntity.status(HttpStatus.OK).body(pickupservice.getPickupPaymentDone());
	}
	
	@GetMapping("/getPickupPaymentNotDone")
	public ResponseEntity<List<Pickup>> getPickupPaymentNotDone()
	{
		return ResponseEntity.status(HttpStatus.OK).body(pickupservice.getPickupPaymentNotDone());
	}
	
    @DeleteMapping("/deletePickup/{id}")
    public ResponseEntity<String> deletePickup(@PathVariable String id)
	{
		return ResponseEntity.status(HttpStatus.OK).body(pickupservice.deletePickup(id));
	}
	
	
	
	//This Method is auto call by Order microservice
		@PostMapping("/addSingleOrder")
		public ResponseEntity<Pickup> createSinglePickup(@RequestBody Pickup pickupobj )
		{
			return ResponseEntity.status(HttpStatus.CREATED).body(pickupservice.createSinglePickup(pickupobj));
		}
	
	/////////////////////////////////////////Payment details ///////////////////////////////
		
		@GetMapping("/getAllPaymentDetails")
		public ResponseEntity<List<PaymentDetails>>getAllPaymentDetails()
		{
			return ResponseEntity.status(HttpStatus.OK).body(doctorimpl.getAllPaymentDetails());
		}
		@GetMapping("/getBypaymentID/{id}")
		public ResponseEntity<PaymentDetails>getBypaymentID(@PathVariable String id)
		{
			return ResponseEntity.status(HttpStatus.OK).body(doctorimpl.getBypaymentID(id));
		}
		
		@GetMapping("/getByOrderID/{id}")
		public ResponseEntity<List<PaymentDetails>>getByOrderID(@PathVariable String id)
		{
			return ResponseEntity.status(HttpStatus.OK).body(doctorimpl.getByOrderID(id));
		}
		
		

		
		

}
