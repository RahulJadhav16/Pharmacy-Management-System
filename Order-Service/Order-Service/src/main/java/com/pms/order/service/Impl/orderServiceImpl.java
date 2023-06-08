package com.pms.order.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.order.service.Model.order;
import com.pms.order.service.Repository.OrderRepository;
import com.pms.order.service.Service.orderService;
import com.pms.order.service.Exception.ordersNotFoundException;
@Service
public class orderServiceImpl implements orderService{
	@Autowired
	private OrderRepository orderRepo;

	@Override
	public order createOrder(order orderObject) {
		
		return orderRepo.save(orderObject);
	}

	@Override
	public List<order> getOrders(String Doctorid) {
	    List<order> orders = orderRepo.findByDoctorId(Doctorid);
	    if (orders.isEmpty()) {
	        throw new ordersNotFoundException("No orders found!");
	    }
	    return orders;		
	}

	

}
