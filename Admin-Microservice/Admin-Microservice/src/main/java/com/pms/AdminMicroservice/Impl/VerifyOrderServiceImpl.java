package com.pms.AdminMicroservice.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.pms.AdminMicroservice.Exception.OrderNotVerifiedException;
import com.pms.AdminMicroservice.Exception.ordersNotFoundException;
import com.pms.AdminMicroservice.Model.Order;
import com.pms.AdminMicroservice.Service.VerifyOrderService;

@Service
public class VerifyOrderServiceImpl implements VerifyOrderService {
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public List<Order> allOrders() {
		String url="http://ORDER-SERVICE/verifyOrder/allOrders";
		List<Order> response=restTemplate.getForObject(url, ArrayList.class);
		
		return response;
	}

	@Override
	public List<Order> getOrderByDoctorId(String id) {
		// TODO Auto-generated method stub
		String url="http://ORDER-SERVICE/verifyOrder/getOrderByDoctorId/"+id;
		try {
		List<Order> response=restTemplate.getForObject(url, ArrayList.class);
		return response;
		}
		catch(HttpClientErrorException e)
		{
			throw new ordersNotFoundException("Orders Not Found for given Id!");
		}
		
	}

	@Override
	public Order getOrderById(String id) {
		String url="http://ORDER-SERVICE/verifyOrder/getOrderById/"+id;
		try {
			Order response=restTemplate.getForObject(url, Order.class);
			return response;
		}
		catch (HttpClientErrorException e) {
			throw new ordersNotFoundException("Orders Not Found for given Id!");
		}
		
	}

	@Override
	public Order verifyOrder(Order obj) {
		String url="http://ORDER-SERVICE/verifyOrder/verifyOrder";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Order> requestEntity = new HttpEntity<>(obj, headers);
		
		try {
			Order response= restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Order.class).getBody();
			return response;
			
		}
		catch (HttpClientErrorException e) {
			throw new OrderNotVerifiedException("Order not verified either stock is not available or stock is expired!");
		}
		
		
	}

	@Override
	public String deleteOrder(String orderId) {
		// TODO Auto-generated method stub
		String url="http://ORDER-SERVICE/verifyOrder/deleteOrder/"+orderId;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<>(orderId, headers);
		String response= restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, String.class).getBody();
	
		
		return response;
	}

}
