package com.pms.doctor.service.Service;

import java.util.List;

import com.pms.doctor.service.Models.Drug;
import com.pms.doctor.service.Models.Order;
import com.pms.doctor.service.Models.Pickup;

public interface doctorService {
	
	//This will use to view all the drugs
	List<Drug> viewAllDrugs();
	
	//This will use to view single drug
	List<Drug> drugByName(String name);
	
	//This will use to Drug by id
	Drug drugById(String id);
	
	//View all orders by doctor id
	List<Order>viewAllOrders(String doctorId);
	
	//Add the order 
	Order addOrder(Order orderObj);
	
	
	//Delete Order
	String deleteOrder(String orderId);
	
	
	//See all pickups 
	List<Pickup> viewAllPickups(String id);
	
	//Make payment from pickup
	Pickup makePayment(Pickup pickup);
	
	

}
