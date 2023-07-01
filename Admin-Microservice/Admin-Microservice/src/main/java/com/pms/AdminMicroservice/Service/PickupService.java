package com.pms.AdminMicroservice.Service;

import java.util.List;

import com.pms.AdminMicroservice.Model.PaymentDetails;
import com.pms.AdminMicroservice.Model.Pickup;



public interface PickupService {
	
	//getAll
	List<Pickup> getAllPickups();
	
	//getByDoctorid
	List<Pickup> getByDoctorId(String doctorId);
	
	//get by pickupid
	Pickup getByPickupId(String pickupId);
	
	//get by payment done
	List<Pickup> getPickupPaymentDone();
	
	//get by payment done
	List<Pickup> getPickupPaymentNotDone();
	
	//get all payment details 
	List<PaymentDetails> getAllPaymentDetails();
		
	//get paymentdetails by payment id
	PaymentDetails getBypaymentID(String id);
	
	//get by order id
	List<PaymentDetails>getByOrderID(String id);
	
	//Delete pickup
	String deletePickup(String id);
	

}
