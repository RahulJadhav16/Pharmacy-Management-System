package com.pms.doctor.service;
import com.pms.doctor.service.Controller.doctorController;
import com.pms.doctor.service.Models.Drug;
import com.pms.doctor.service.Models.Order;
import com.pms.doctor.service.Models.Pickup;
import com.pms.doctor.service.Impl.doctorServiceImpl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


class doctorControllerTests {

    @Mock
    private doctorServiceImpl doctorService;

    @InjectMocks
    private  doctorController doctorController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testViewAllDrugs() {
        // Prepare test data
        List<Drug> drugs = new ArrayList<>();
        drugs.add(new Drug("123","Aspirin",50,"Tablet","Pain reliever"));
       

        // Mock the doctorService's behavior
        when(doctorService.viewAllDrugs()).thenReturn(drugs);

        // Call the API endpoint
        ResponseEntity<List<Drug>> response = doctorController.viewAllDrugs();

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(drugs, response.getBody());

        // Verify that the doctorService's method was called
        verify(doctorService).viewAllDrugs();
    }
    
    @Test
    void testDrugByName_ValidName() {
        // Prepare test data
        String drugName = "Aspirin";
        List<Drug> drugs = new ArrayList<>();
        drugs.add(new Drug("1", "Aspirin", 10, "Type1", "Category1"));
        drugs.add(new Drug("2", "Aspirin", 20, "Type2", "Category2"));

        // Mock the doctorService's behavior
        when(doctorService.drugByName(drugName)).thenReturn(drugs);

        // Call the API endpoint
        ResponseEntity<List<Drug>> response = doctorController.drugByName(drugName);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(drugs, response.getBody());

        // Verify that the doctorService's method was called
        verify(doctorService).drugByName(drugName);
    }
    
    @Test
    void testDrugByName_NoMatch() {
        // Prepare test data
        String drugName = "NonexistentDrug";

        // Mock the doctorService's behavior
        when(doctorService.drugByName(drugName)).thenReturn(Collections.emptyList());

        // Call the API endpoint
        ResponseEntity<List<Drug>> response = doctorController.drugByName(drugName);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Collections.emptyList(), response.getBody());

        // Verify that the doctorService's method was called
        verify(doctorService).drugByName(drugName);
    }
    
    @Test
    void testDrugById_ValidId() {
        // Prepare test data
        String drugId = "1";
        Drug drug = new Drug("1", "Aspirin", 10, "Type1", "Category1");

        // Mock the doctorService's behavior
        when(doctorService.drugById(drugId)).thenReturn(drug);

        // Call the API endpoint
        ResponseEntity<Drug> response = doctorController.drugById(drugId);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(drug, response.getBody());

        // Verify that the doctorService's method was called
        verify(doctorService).drugById(drugId);
    }



    
    @Test
    void testDrugById_NoMatch() {
        // Prepare test data
        String drugId = "999";

        // Mock the doctorService's behavior
        when(doctorService.drugById(drugId)).thenReturn(null);

        // Call the API endpoint
        ResponseEntity<Drug> response = doctorController.drugById(drugId);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNull(response.getBody());

        // Verify that the doctorService's method was called
        verify(doctorService).drugById(drugId);
    }
    
    
    //////////////////////// Test cases for Order ////////////////////////
    
    @Test
    void testViewAllOrders() {
        // Prepare test data
        String doctorId = "1";
        List<Order> expectedOrders = new ArrayList<>();
        // Add some orders to the expected list
        Order order1 = new Order("1", "D001", "Dr. John Doe", "Drug1", 5, true, LocalDate.now());
        Order order2 = new Order("2", "D001", "Dr. John Doe", "Drug2", 3, true, LocalDate.now());
        expectedOrders.add(order1);
        expectedOrders.add(order2);

        // Mock the doctorService's behavior
        when(doctorService.viewAllOrders(doctorId)).thenReturn(expectedOrders);

        // Call the API endpoint
        ResponseEntity<List<Order>> response = doctorController.viewAllOrders(doctorId);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedOrders, response.getBody());

        // Verify that the doctorService's method was called
        verify(doctorService).viewAllOrders(doctorId);
    }
    
    @Test
    void testAddOrder() {
        // Prepare test data
        Order orderObj = new Order("1", "D001", "Dr. John Doe", "Drug1", 5, true, LocalDate.now());
        

        // Mock the doctorService's behavior
        when(doctorService.addOrder(orderObj)).thenReturn(orderObj);

        // Call the API endpoint
        ResponseEntity<Order> response = doctorController.addOrder(orderObj);

        // Verify the response
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(orderObj, response.getBody());

        // Verify that the doctorService's method was called
        verify(doctorService).addOrder(orderObj);
    }
    
    @Test
    void testDeleteOrder() {
        // Prepare test data
        String orderId = "1";

        // Mock the doctorService's behavior
        when(doctorService.deleteOrder(orderId)).thenReturn("Order deleted successfully");

        // Call the API endpoint
        ResponseEntity<String> response = doctorController.deleteOrder(orderId);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Order deleted successfully", response.getBody());

        // Verify that the doctorService's method was called
        verify(doctorService).deleteOrder(orderId);
    }
    
         ///////////////// pickup section ///////////////////////////////////
    
    @Test
    void testViewAllPickups() {
        // Prepare test data
        String id = "1";
        List<Pickup> expectedPickups = new ArrayList<>();

     // Create and add Pickup objects to the expectedPickups list
     ArrayList<Order> orders1 = new ArrayList<>();
     // Add some orders to orders1 list
     Order order1 = new Order("orderId1", "doctorId1", "doctorName1", "drugName1", 1, true, LocalDate.now());
     orders1.add(order1);
     Pickup pickup1 = new Pickup("1", 100.0, LocalDate.now(), true, 50.0, orders1);

     ArrayList<Order> orders2 = new ArrayList<>();
     // Add some orders to orders2 list
     Order order2 = new Order("orderId2", "doctorId2", "doctorName2", "drugName2", 2, true, LocalDate.now());
     orders2.add(order2);
     Pickup pickup2 = new Pickup("2", 150.0, LocalDate.now(), true, 100.0, orders2);

     expectedPickups.add(pickup1);
     expectedPickups.add(pickup2);
     


        // Mock the doctorService's behavior
        when(doctorService.viewAllPickups(id)).thenReturn(expectedPickups);

        // Call the API endpoint
        ResponseEntity<List<Pickup>> response = doctorController.viewAllPickups(id);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedPickups, response.getBody());

        // Verify that the doctorService's method was called
        verify(doctorService).viewAllPickups(id);
    }
    
    
    @Test
    void testMakePayment() {
        // Prepare test data
        Pickup pickupObj = new Pickup();
        // Set the necessary properties of the pickupObj
        ArrayList<Order> orders1 = new ArrayList<>();
        // Add some orders to orders1 list
        Order order1 = new Order("orderId1", "doctorId1", "doctorName1", "drugName1", 1, true, LocalDate.now());
        orders1.add(order1);
        pickupObj = new Pickup("1", 100.0, LocalDate.now(), true, 50.0, orders1);
        

        // Mock the doctorService's behavior
        when(doctorService.makePayment(pickupObj)).thenReturn(pickupObj);

        // Call the API endpoint
        ResponseEntity<Pickup> response = doctorController.makePayment(pickupObj);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pickupObj, response.getBody());

        // Verify that the doctorService's method was called
        verify(doctorService).makePayment(pickupObj);
    }









    
   


   
}

