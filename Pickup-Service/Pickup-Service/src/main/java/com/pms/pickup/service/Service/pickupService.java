package com.pms.pickup.service.Service;

import java.util.List;

import com.pms.pickup.service.Model.Order;
import com.pms.pickup.service.Model.Pickup;

public interface pickupService {
	
	//getAll
	List<Pickup> getAll();
	
	//getByDoctorid
	List<Pickup> getByDoctorId(String doctorId);
	
	//get by pickupid
	Pickup getByPickupId(String pickupId);
	
	
	
	//post for pickup creation
	Pickup createSinglePickup(Pickup pickupObj);
	
	
	
	

}
