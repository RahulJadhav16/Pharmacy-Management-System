package com.pms.doctor.service.Impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.pms.doctor.service.Exception.DrugNotFoundById;
import com.pms.doctor.service.Exception.DrugNotFoundByname;
import com.pms.doctor.service.Exception.UserNotFoundByIDException;
import com.pms.doctor.service.Exception.VerifyedOrderNotChangeException;
import com.pms.doctor.service.Models.DoctorPersonalDetails;
import com.pms.doctor.service.Models.Drug;
import com.pms.doctor.service.Models.Order;
import com.pms.doctor.service.Models.Pickup;
import com.pms.doctor.service.Repository.doctorRepository;
import com.pms.doctor.service.Service.doctorService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.HttpEntity;


@Service
public class doctorServiceImpl implements doctorService {
	
	@Autowired
	private doctorRepository repo;
	
	@Autowired
	private DoctorPersonalDetailsImpl checkID;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	private Logger logger= LoggerFactory.getLogger(doctorServiceImpl.class);

	@Override
	public List<Drug> viewAllDrugs() {
		// TODO Auto-generated method stub
		ArrayList<Drug>viewAllDrugs=restTemplate.getForObject("http://DRUG-INVENTORY/drugs/getalldrugs", ArrayList.class);
		logger.info("{}",viewAllDrugs);
		return viewAllDrugs;
	}

	@Override
	public List<Drug> drugByName(String name) {
		
		try {
            ArrayList<Drug> viewAllDrugsByName = restTemplate.getForObject("http://DRUG-INVENTORY/drugs/getdrugbyname/" + name, ArrayList.class);
            logger.info("{}", viewAllDrugsByName);
            return viewAllDrugsByName;
        } catch (HttpClientErrorException.NotFound exception) {
           
            logger.error("Drug not found: {}", exception.getMessage());
            throw new DrugNotFoundByname("Drug Not Found By Given Name !");
        }
	}

	@Override
	public Drug drugById(String id) {
		
	try {
		Drug getDrug=restTemplate.getForObject("http://DRUG-INVENTORY/drugs/getdrugbyid/"+id, Drug.class);
		logger.info("{}",getDrug);
		return getDrug;
	}
	catch (HttpClientErrorException.NotFound exception) {
        
        logger.error("Drug not found: {}", exception.getMessage());
        throw new DrugNotFoundById("Drug Not Found By Given Id !");
    }
	
		
		
	}

	//From here order service starting 
	@Override
	public List<Order> viewAllOrders(String doctorId) {
		
		// Here i am getting  the user orders info
	    try {
		String url = "http://ORDER-SERVICE/orderService/showOrder/"+doctorId;
		ArrayList<Order> orderList=restTemplate.getForObject(url, ArrayList.class);
		return orderList;
	    }
	    
	    catch (HttpClientErrorException.NotFound exception) {
	        
	        logger.error("User not found: {}", exception.getMessage());
	        throw new UserNotFoundByIDException("User not found By Given Id !");
	    }
	    
	}

	@Override
	public Order addOrder(Order orderObj) {
		LocalDate todaysDate=LocalDate.now();
		DoctorPersonalDetails doctordetails=new DoctorPersonalDetails();
		String doctorid=orderObj.getDoctorId();
		doctordetails=checkID.getDetails(doctorid);
		orderObj.setDoctorName(doctordetails.getName());
		orderObj.setEmail(doctordetails.getEmail());
		orderObj.setAddress(doctordetails.getAddress());
		logger.info("{}",orderObj);
		orderObj.setOrderDate(todaysDate);
		//Here i am making post request to ORDER-SERVICE 
		String url = "http://ORDER-SERVICE/orderService/addOrder"; 
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<Order> requestEntity = new HttpEntity<>(orderObj, headers);
		Order response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Order.class).getBody();
		return response;
	}



	@Override
	public String deleteOrder(String orderId) {
		String url = "http://ORDER-SERVICE/orderService/getOrderByOrderId/"+orderId;
	    Order orderList=restTemplate.getForObject(url, Order.class);
	    if(orderList.isStatus())
	    { 
	    	throw new VerifyedOrderNotChangeException("Order is verified it canot be changed now!");
	    }
	    else
	    {
	    	
	    	//Here i am making Put request to ORDER-SERVICE 
			String urlUpdate = "http://ORDER-SERVICE/orderService/deleteOrder/"+orderId; 
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			
			HttpEntity<String> requestEntity = new HttpEntity<>(orderId, headers);
			restTemplate.exchange(urlUpdate, HttpMethod.DELETE, requestEntity, String.class).getBody();
			return "Order has been deleted!";
	    	
	    	
	    }	
	}

	@Override
	public List<Pickup> viewAllPickups(String id) {
		String url="http://PICKUP-SERVICE/pickupDoctor/getAllPickups/"+id;
		
		return restTemplate.getForObject(url, ArrayList.class);
	}

	@Override
	public Pickup makePayment(Pickup pickup) {
		
		String url="http://PICKUP-SERVICE/pickupDoctor/makePayment";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Pickup> requestEntity = new HttpEntity<>(pickup, headers);
		
		return restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Pickup.class).getBody();
	}
	
	
	

}
