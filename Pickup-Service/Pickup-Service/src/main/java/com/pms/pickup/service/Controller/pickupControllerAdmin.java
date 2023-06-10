package com.pms.pickup.service.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pms.pickup.service.Impl.pickupServiceImpl;
import com.pms.pickup.service.Model.Pickup;

@RestController
@RequestMapping("/pickupAdmin")
public class pickupControllerAdmin {
	@Autowired
	private pickupServiceImpl pickupservice;
	
	
	@GetMapping("/getAllPickups")
	public ResponseEntity<List<Pickup>> getAll()
	{
		return ResponseEntity.status(HttpStatus.OK).body(pickupservice.getAll());
	}
	
	@PostMapping("/addSingleOrder")
	public ResponseEntity<Pickup> createSinglePickup(@RequestBody Pickup pickupobj )
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(pickupservice.createSinglePickup(pickupobj));
	}

}
