package com.pms.AdminMicroservice.Service;

import java.util.List;

import com.pms.AdminMicroservice.Model.Order;

public interface VerifyOrderService {
	
	//allOrders
	List<Order>allOrders();
	
	//getOrderByDoctorId
	List<Order>getOrderByDoctorId(String id);
	
	//getOrderById
	Order getOrderById(String id);
	
	//verifyOrder
	Order verifyOrder(Order obj);
	
	//Delete the order
	String deleteOrder(String orderId);
	
	

}
