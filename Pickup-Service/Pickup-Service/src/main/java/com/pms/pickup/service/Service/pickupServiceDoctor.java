package com.pms.pickup.service.Service;

import java.util.List;

import com.pms.pickup.service.Model.PaymentDetails;
import com.pms.pickup.service.Model.Pickup;

public interface pickupServiceDoctor {
	
	//Make payment by Pickup
	Pickup makePayment(Pickup obj);
	
	//Get All Pickup by doctor
	List<Pickup> getAllPickups(String doctorId);
	
	//Add payment details
	PaymentDetails addPaymentDetails(PaymentDetails obj);
	
	//get all payment details 
	List<PaymentDetails> getAllPaymentDetails();
	
	//get paymentdetails by payment id
	PaymentDetails getBypaymentID(String id);

}
