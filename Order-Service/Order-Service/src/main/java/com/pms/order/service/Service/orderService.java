package com.pms.order.service.Service;

import java.util.List;

import com.pms.order.service.Model.order;

public interface orderService {
	
	//Create order
	order createOrder(order orderObject);
	
	//view all orders by doctorId
	List<order> getOrders(String id);

}
