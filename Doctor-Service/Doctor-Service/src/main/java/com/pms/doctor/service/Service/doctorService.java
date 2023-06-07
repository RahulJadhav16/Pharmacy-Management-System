package com.pms.doctor.service.Service;

import java.util.List;

import com.pms.doctor.service.Models.Drug;
import com.pms.doctor.service.Models.Order;

public interface doctorService {
	
	//This will use to view all the drugs
	List<Drug> viewAllDrugs();
	
	//This will use to view single drug
	List<Drug> drugByName(String name);
	
	//This will use to Drug by id
	Drug drugById(String id);
	
	//View all orders
	List<Order>viewAllOrders();
	
	//Add the order 
	Order addOrder(Order orderObj);
	
	
	

}
