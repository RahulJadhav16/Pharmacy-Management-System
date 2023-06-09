package com.pms.order.service.Impl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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

import com.pms.order.service.Email.sendEmail;
import com.pms.order.service.Exception.OrderNotVerifiedException;
import com.pms.order.service.Exception.ordersNotFoundException;
import com.pms.order.service.Model.DoctorPersonalDetails;
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
	
	@Autowired
	private sendEmail email;
	
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
	            LocalDate stockExpiredate=stock.getExpireDate();
	            
	             // Calculate the difference in days between the provided date and today
	            long daysDifference = ChronoUnit.DAYS.between(todaysDate, stockExpiredate);
	            
	            if ((stock.getQuantity() >= obj.getQuantity()) && (daysDifference>7)) {
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
	                
	                //Here order is verified now i fetch the email address and send email
	                //I am fetching the email from doctor microservice 
	                String urlForEmail="http://DOCTOR-SERVICE/registerDoctor/getDetails/"+obj.getDoctorId();
	                DoctorPersonalDetails details=restTemplate.getForObject(urlForEmail, DoctorPersonalDetails.class);
	                
	                //Adding mail title and and sending mail
	                double calculateAmount=stock.getPrice()*obj.getQuantity();
	                String totalAmount=Double.toString(calculateAmount)	;
	                
	                String emailBody = "Dear Dr." + details.getName() + ",\r\n"
	                	    + "We hope this email finds you well. We are reaching out to inform you that your recent order has been successfully verified and added to our pickup section.\r\n"
	                	    + "\r\n"
	                	    + "Please find below the details of your order for your reference:\r\n"
	                	    + "Order Number: " + obj.getOrderId() + "\r\n"
	                	    + "Item Ordered: " + stock.getDrugName() +" With quantity "+obj.getQuantity()+ "\r\n"
	                	    + "Total Amount: ₹" + totalAmount + "\r\n"
	                	    + "\r\n"
	                	    + "To proceed with the completion of your order, we kindly request that you make the payment within the next four days from today's date. Failure to do so may result in the automatic cancellation of your order.\r\n"
	                	    + "\r\n"
	                	    + "If you encounter any difficulties or require assistance regarding the payment process, please do not hesitate to contact our customer support team.\r\n"
	                	    + "\r\n"
	                	    + "Thank you for choosing our services!";


	                String emailTitle="Order Verification and Payment Reminder for order ID: "+obj.getOrderId();
	                
	                email.someMethod(details.getEmail(),emailTitle,emailBody);
	                
	            } else {
	                obj.setStatus(false);
	                orderObj = orderRepo.save(obj);
	                
	                //I am fetching the email from doctor microservice 
	                String urlForEmail="http://DOCTOR-SERVICE/registerDoctor/getDetails/"+obj.getDoctorId();
	                DoctorPersonalDetails details=restTemplate.getForObject(urlForEmail, DoctorPersonalDetails.class);
	                
	                //Adding mail title and and sending mail
	                double calculateAmount=stock.getPrice()*obj.getQuantity();
	                String totalAmount=Double.toString(calculateAmount)	;
	                //Here  I am sending the mail that order not verified
	                String emailBody = "Dear Dr. " + details.getName() + ",\r\n"
	                	    + "We hope this email finds you well. We are reaching out to inform you that we regretfully cannot verify your recent order at the moment due to insufficient stock.\r\n"
	                	    + "\r\n"
	                	    + "Please find below the details of your order for your reference:\r\n"
	                	    + "Order Number: " + obj.getOrderId() + "\r\n"
	                	    + "Item Ordered: " + stock.getDrugName() + " with quantity " + obj.getQuantity() + "\r\n"
	                	    + "Total Amount: ₹" + totalAmount + "\r\n"
	                	    + "\r\n"
	                	    + "We apologize for any inconvenience caused. Our team is actively working to restock the item, and we will prioritize your order as soon as it becomes available. Please stay connected with us for further updates.\r\n"
	                	    + "\r\n"
	                	    + "If you have any questions or require assistance, please do not hesitate to contact our customer support team.\r\n"
	                	    + "\r\n"
	                	    + "Thank you for your understanding and patience!\r\n";
	                
	                String emailTitle = "Order Update: Insufficient Stock for Order ID " + obj.getOrderId();
	                email.someMethod(details.getEmail(),emailTitle,emailBody);

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
