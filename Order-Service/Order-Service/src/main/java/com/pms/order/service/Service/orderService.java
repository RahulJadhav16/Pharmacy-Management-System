package com.pms.order.service.Service;

import java.util.List;

import com.pms.order.service.Model.order;

public interface orderService {
	
	//Create order
	order createOrder(order orderObject);
	
	//get the order by orderID
	order getOrderByOrderId(String orderId);
	
	//view all orders by doctorId
	List<order> getOrders(String id);
	
	//Update The order
	order updateOrder(order orderObject);
	
	//Delete the order
	String deleteOrder(String orderId);
	

	

}
