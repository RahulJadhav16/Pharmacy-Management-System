package com.pms.pickup.service.Impl;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pms.pickup.service.Model.DrugsStock;
import com.pms.pickup.service.Model.Order;
import com.pms.pickup.service.Model.Pickup;
import com.pms.pickup.service.Repository.pickupRepository;
import com.pms.pickup.service.Service.pickupService;

@Service
public class pickupServiceImpl implements pickupService{
    
	@Autowired
	private pickupRepository pickupRepo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public List<Pickup> getAll() {
		// TODO Auto-generated method stub
		return pickupRepo.findAll();
	}

	@Override
	public List<Pickup> getByDoctorId(String doctorId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pickup getByPickupId(String pickupId) {
		// TODO Auto-generated method stub
		return pickupRepo.findById(pickupId).orElseThrow();
	}


	@Override
	public Pickup createSinglePickup(Pickup pickupObj) {
		// Here in this method i am adding the order in to pickup section and if
		// multiple orders are their i am just adding it into same pickup section
		
		LocalDate todaysDate=LocalDate.now();
		pickupObj.setPickupdate(todaysDate);
		
		List<Pickup> getAll=pickupRepo.findAll();
		
		if(pickupObj.getOrders().isEmpty())
		{
			//Terminating the code if order list isEmpty	
		}
		else
		{
		LocalDate orderDate=pickupObj.getOrders().get(0).getOrderDate();
		
		for(Pickup e:getAll)
		{
			if(e.getOrders().get(0).getOrderDate().isEqual(orderDate))
			{
				ArrayList<Order> oldList=e.getOrders();
				Order newOrder=pickupObj.getOrders().get(0);
				oldList.add(newOrder);
				
				
				pickupObj.setOrders(oldList);
				
				String pickupId=e.getPickupId();
				pickupObj.setPickupId(pickupId);
				break;
			}
		}
		
		
		//Here i am setting the Last date for payment which 4 days because expire is on 8th day
		pickupObj.setPickupdate(orderDate.plusDays(4));
		
		//here i am making total bill
		double totalBill = 0.0;
		for (Order order : pickupObj.getOrders()) {
		    String drugName = order.getDrugName();
		    int quantity = order.getQuantity();
		    String url = "http://DRUG-INVENTORY/stock/getByDrugName/" + drugName;
		    ResponseEntity<DrugsStock[]> response = restTemplate.getForEntity(url, DrugsStock[].class);
		    DrugsStock[] stocks = response.getBody();
		    if (stocks != null && stocks.length > 0) {
		        DrugsStock stock = stocks[0];
		        totalBill += quantity * stock.getPrice();
		    }
		    
		    
		}
		
		pickupObj.setTotalBill(totalBill);
		}
		
		//MAking payment status false now because after doctor payment it will change 
		pickupObj.setPaymentStatus(false);
		return pickupRepo.save(pickupObj);
	}

	
	

	


}
