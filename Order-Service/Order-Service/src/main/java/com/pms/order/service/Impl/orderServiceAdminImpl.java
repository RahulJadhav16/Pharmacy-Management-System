package com.pms.order.service.Impl;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pms.order.service.Exception.OrderNotVerifiedException;
import com.pms.order.service.Exception.ordersNotFoundException;
import com.pms.order.service.Model.DrugsStock;
import com.pms.order.service.Model.order;

import com.pms.order.service.Repository.OrderRepository;
import com.pms.order.service.Service.orderServiceAdmin;

@Service
public class orderServiceAdminImpl implements orderServiceAdmin{
	
	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	private Logger logger= LoggerFactory.getLogger(orderServiceAdminImpl.class);

	@Override
	public List<order> getAllOrders() {
		// TODO Auto-generated method stub
		return orderRepo.findAll();
	}

	@Override
	public order getOrderById(String id) {
		// TODO Auto-generated method stub
		return orderRepo.findById(id).orElseThrow(()->new ordersNotFoundException("Order not found by given order id! "));
	}

	@Override
	public List<order> getOrderByDoctorId(String id) {

		List<order> orders = orderRepo.findByDoctorId(id);
	    if (orders.isEmpty()) {
	        throw new ordersNotFoundException("No orders found with this Doctor ID!");
	    }
	    return orders;
		
	}

	@Override
	public order updateOrderStatus(order obj) {
	    String orderId = obj.getOrderId();
	    boolean checkId = orderRepo.existsById(orderId);
	    order orderObj;
	    
	    if (checkId) {
	        obj.setStatus(false);
	        orderObj = orderRepo.save(obj);
	        
	        // Updating the stock info because the order is verified
	        // Getting info of Quantity
	        String url = "http://DRUG-INVENTORY/stock/getByDrugName/" + obj.getDrugName();
	        
	        ResponseEntity<DrugsStock[]> response = restTemplate.getForEntity(url, DrugsStock[].class);
	        DrugsStock[] stocks = response.getBody();
	        
	        if (stocks != null && stocks.length > 0) {
	            DrugsStock stock = stocks[0]; // Assuming the response contains the first matching stock
	            
	            logger.info("{}", stock);
	            int leftoverQuantity = 0;
	            LocalDate todaysDate = LocalDate.now();
	            
	            if ((stock.getQuantity() >= obj.getQuantity()) && (stock.getExpireDate().isAfter(todaysDate))) {
	                leftoverQuantity = stock.getQuantity() - obj.getQuantity();
	                stock.setQuantity(leftoverQuantity);
	                
	                // Updating the stock inside the inventory
	                String updateUrl = "http://DRUG-INVENTORY/stock/update";
	                HttpHeaders headers = new HttpHeaders();
	                headers.setContentType(MediaType.APPLICATION_JSON);
	                HttpEntity<DrugsStock> requestEntity = new HttpEntity<>(stock, headers);
	                
	                DrugsStock updatedStock = restTemplate.exchange(updateUrl, HttpMethod.PUT, requestEntity, DrugsStock.class).getBody();
	                logger.info("{}", updatedStock);
	                
	                obj.setStatus(true);
	                orderObj = orderRepo.save(obj);
	            } else {
	                obj.setStatus(false);
	                orderObj = orderRepo.save(obj);
	                throw new OrderNotVerifiedException("Order not verified either stock is not available or stock is expired!");
	            }
	        } else {
	            throw new OrderNotVerifiedException("No stock found for the given drug name!");
	        }
	    } else {
	        throw new ordersNotFoundException("No orders found with the given ID!");
	    }
	    
	    return orderObj;
	}


}
