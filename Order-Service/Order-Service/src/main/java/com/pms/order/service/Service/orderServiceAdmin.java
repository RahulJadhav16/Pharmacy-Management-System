package com.pms.order.service.Service;

import java.util.List;

import com.pms.order.service.Model.order;

public interface orderServiceAdmin {
	
	//get all orders
	List<order> getAllOrders();
	
	//get order by order id
	order getOrderById(String id);
	
	//get order by doctor id
	List<order> getOrderByDoctorId(String id);
	
	//Update the order status
	order updateOrderStatus(order obj);
	
	//Adding order to pickup
	String addToPickup();
	
	


}
