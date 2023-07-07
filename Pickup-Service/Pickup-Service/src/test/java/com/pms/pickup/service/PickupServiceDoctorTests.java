package com.pms.pickup.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.pms.pickup.service.Controller.pickupControllerDoctor;
import com.pms.pickup.service.Impl.pickupServiceDoctorImpl;
import com.pms.pickup.service.Model.Order;
import com.pms.pickup.service.Model.PaymentDetails;
import com.pms.pickup.service.Model.Pickup;
import com.pms.pickup.service.Service.pickupServiceDoctor;

@SpringBootTest
public class PickupServiceDoctorTests {
	
	 @Mock
	 private pickupServiceDoctor pickupService;
	  
	  @Mock
	  private pickupServiceDoctorImpl doctorimpl;

	    @InjectMocks
	    private pickupControllerDoctor pickupController;
	    
	    
	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }
	    
	    @Test
	    void testGetAllPickups() {
	        // Prepare test data
	        String id = "12345";
	        
	        List<Pickup> expectedPickups = new ArrayList<>();
	        ArrayList<Order>orderList=new ArrayList<>();
//	        Order order1 = new Order("1", "12345", "Dr. John Doe","abc@exmple.com","mg.road", "Drug1", 5, true, LocalDate.now());
//	        orderList.add(order1);
//	        
//	        Pickup pickup1 = new Pickup("1", 100.0, LocalDate.now(), true, 50.0,orderList );
//	        expectedPickups.add(pickup1);
	    
	        

	       
	        when(pickupService.getAllPickups(id)).thenReturn(expectedPickups);

	        // Call the API endpoint
	        ResponseEntity<List<Pickup>> response = pickupController.getAllPickups(id);

	        // Verify the response
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(expectedPickups, response.getBody());
	        verify(doctorimpl).getAllPickups(id);

	        
	    }
	    
	    
	    @Test
	    void testMakePayment() {
	        // Prepare test data
	    	 ArrayList<Order>orderList=new ArrayList<>();
		     Order order1 = new Order("1", "12345", "Dr. John Doe","abc@exmple.com","mg.road", "Drug1", 5, true, LocalDate.now());
		     orderList.add(order1);
	        Pickup pickup = new Pickup("1", 100.0, LocalDate.now(), true, 50.0,orderList);
	       

	        Pickup expectedPickup = new Pickup("1", 100.0, LocalDate.now(), true, 50.0,orderList);
	        

	        // Mock the service's behavior
	        when(doctorimpl.makePayment(pickup)).thenReturn(expectedPickup);

	        // Call the API endpoint
	        ResponseEntity<Pickup> response = pickupController.makePayment(pickup);

	        // Verify the response
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(expectedPickup, response.getBody());

	        // Verify that the service's method was called
	        verify(doctorimpl).makePayment(pickup);
	    }
	   
	    @Test
	    void testAddPaymentDetails() {
	        // Prepare test data
	        PaymentDetails paymentDetails = new PaymentDetails("PAymentID","Orderid",100.0,LocalDate.now(),"Doctor@gmail.com");
	        // Set the necessary properties of the paymentDetails object

	        PaymentDetails expectedPaymentDetails = new PaymentDetails("PAymentID","Orderid",100.0,LocalDate.now(),"Doctor@gmail.com");
	        // Set the necessary properties of the expectedPaymentDetails object

	        // Mock the service's behavior
	        when(doctorimpl.addPaymentDetails(paymentDetails)).thenReturn(expectedPaymentDetails);

	        // Call the API endpoint
	        ResponseEntity<PaymentDetails> response = pickupController.addPaymentDetails(paymentDetails);

	        // Verify the response
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(expectedPaymentDetails, response.getBody());

	        // Verify that the service's method was called
	        verify(doctorimpl).addPaymentDetails(paymentDetails);
	    }
	    
	    @Test
	    void testGetByOrderID() {
	        // Prepare test data
	        String orderId = "12345";
	        
	        List<PaymentDetails> expectedPaymentDetailsList = new ArrayList<>();
	        
	        PaymentDetails paymentDetails1 = new PaymentDetails("PAymentID","Orderid",100.0,LocalDate.now(),"Doctor@gmail.com");
	        
	        PaymentDetails paymentDetails2 = new PaymentDetails("PAymentID","Orderid",100.0,LocalDate.now(),"Doctor@gmail.com");
	        
	        expectedPaymentDetailsList.add(paymentDetails1);
	        expectedPaymentDetailsList.add(paymentDetails2);
	        
	        when(doctorimpl.getByOrderID(orderId)).thenReturn(expectedPaymentDetailsList);

	        // Call the API endpoint
	        ResponseEntity<List<PaymentDetails>> response = pickupController.getByOrderID(orderId);

	        // Verify the response
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(expectedPaymentDetailsList, response.getBody());

	        // Verify that the service's method was called
	        verify(doctorimpl).getByOrderID(orderId);
	    }





}
