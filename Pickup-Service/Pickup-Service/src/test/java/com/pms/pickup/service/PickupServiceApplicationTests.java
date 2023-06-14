package com.pms.pickup.service;

import com.pms.pickup.service.Model.Order;
import com.pms.pickup.service.Controller.pickupControllerDoctor;
import com.pms.pickup.service.Controller.pickupControllerAdmin;
import com.pms.pickup.service.Impl.pickupServiceImpl;
import com.pms.pickup.service.Model.Pickup;
import com.pms.pickup.service.Service.pickupServiceDoctor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PickupServiceApplicationTests{
    @Mock
    private pickupServiceDoctor pickupService;

    @InjectMocks
    private pickupControllerDoctor pickupController;
    
    @Mock
    private pickupServiceImpl pickupServiceadmin;

    @InjectMocks
    private pickupControllerAdmin pickupControlleradmin;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllPickups() {
        // Prepare test data
        String id = "doctor_id";
        
        List<Pickup> expectedPickups = new ArrayList<>();

        
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
        
        // Mock the pickupService's behavior
        when(pickupService.getAllPickups(id)).thenReturn(expectedPickups);

        // Call the API endpoint
        ResponseEntity<List<Pickup>> response = pickupController.getAllPickups(id);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedPickups, response.getBody());

        // Verify that the pickupService's method was called
        verify(pickupService).getAllPickups(id);
    }

    @Test
    void testMakePayment() {
        // Prepare test data
        
        ArrayList<Order> orders1 = new ArrayList<>();
        // Add some orders to orders1 list
        Order order1 = new Order("orderId1", "doctorId1", "doctorName1", "drugName1", 1, true, LocalDate.now());
        orders1.add(order1);
        Pickup pickup = new Pickup("1", 100.0, LocalDate.now(), true, 50.0, orders1);

        // Mock the pickupService's behavior
        when(pickupService.makePayment(pickup)).thenReturn(pickup);

        // Call the API endpoint
        ResponseEntity<Pickup> response = pickupController.makePayment(pickup);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pickup, response.getBody());

        // Verify that the pickupService's method was called
        verify(pickupService).makePayment(pickup);
    }
    
    ////////////////////////////////// For admin ///////////////////////////////////////////////
    
    
    @Test
    void testGetAllPickupsAdmin() {
        // Prepare test data
        List<Pickup> expectedPickups = new ArrayList<>();
        // Add some pickups to the expected list
        Pickup pickup1 = new Pickup("1", 100.0, LocalDate.now(), true, 50.0, new ArrayList<>());
        Pickup pickup2 = new Pickup("2", 200.0, LocalDate.now(), true, 150.0, new ArrayList<>());
        expectedPickups.add(pickup1);
        expectedPickups.add(pickup2);

        // Mock the pickupService's behavior
        when(pickupServiceadmin.getAll()).thenReturn(expectedPickups);

        // Call the API endpoint
        ResponseEntity<List<Pickup>> response = pickupControlleradmin.getAll();

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedPickups, response.getBody());

        // Verify that the pickupService's method was called
        verify(pickupServiceadmin).getAll();
    }
    
    @Test
    void testGetByPickupId() {
        // Prepare test data
        String pickupId = "1";
        Pickup expectedPickup = new Pickup(pickupId, 100.0, LocalDate.now(), true, 50.0, new ArrayList<>());

        // Mock the pickupService's behavior
        when(pickupServiceadmin.getByPickupId(pickupId)).thenReturn(expectedPickup);

        // Call the API endpoint
        ResponseEntity<Pickup> response = pickupControlleradmin.getByPickupId(pickupId);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedPickup, response.getBody());

        // Verify that the pickupService's method was called
        verify(pickupServiceadmin).getByPickupId(pickupId);
    }
    
    @Test
    void testGetByDoctorId() {
        // Prepare test data
        String doctorId = "1";
        List<Pickup> expectedPickups = new ArrayList<>();
        // Add some pickups to the expected list
        Pickup pickup1 = new Pickup("1", 100.0, LocalDate.now(), true, 50.0, new ArrayList<>());
        Pickup pickup2 = new Pickup("2", 200.0, LocalDate.now(), true, 150.0, new ArrayList<>());
        expectedPickups.add(pickup1);
        expectedPickups.add(pickup2);

        // Mock the pickupService's behavior
        when(pickupServiceadmin.getByDoctorId(doctorId)).thenReturn(expectedPickups);

        // Call the API endpoint
        ResponseEntity<List<Pickup>> response = pickupControlleradmin.getByDoctorId(doctorId);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedPickups, response.getBody());

        // Verify that the pickupService's method was called
        verify(pickupServiceadmin).getByDoctorId(doctorId);
    }
    
    @Test
    void testGetPickupPaymentDone() {
        // Prepare test data
        List<Pickup> expectedPickups = new ArrayList<>();
        // Add some pickups to the expected list
        Pickup pickup1 = new Pickup("1", 100.0, LocalDate.now(), true, 50.0, new ArrayList<>());
        Pickup pickup2 = new Pickup("2", 200.0, LocalDate.now(), true, 150.0, new ArrayList<>());
        expectedPickups.add(pickup1);
        expectedPickups.add(pickup2);

        
        when(pickupServiceadmin.getPickupPaymentDone()).thenReturn(expectedPickups);

        // Call the API endpoint
        ResponseEntity<List<Pickup>> response = pickupControlleradmin.getPickupPaymentDone();

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedPickups, response.getBody());

        // Verify that the pickupService's method was called
        verify(pickupServiceadmin).getPickupPaymentDone();
    }
    @Test
    void testGetPickupPaymentNotDone() {
        
        List<Pickup> expectedPickups = new ArrayList<>();
        
        Pickup pickup1 = new Pickup("1", 100.0, LocalDate.now(), false, 0.0, new ArrayList<>());
        Pickup pickup2 = new Pickup("2", 200.0, LocalDate.now(), false, 0.0, new ArrayList<>());
        expectedPickups.add(pickup1);
        expectedPickups.add(pickup2);

        // Mock the pickupService
        when(pickupServiceadmin.getPickupPaymentNotDone()).thenReturn(expectedPickups);

        // Call the API endpoint
        ResponseEntity<List<Pickup>> response = pickupControlleradmin.getPickupPaymentNotDone();

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedPickups, response.getBody());

        // Verify that the pickupService's method was called
        verify(pickupServiceadmin).getPickupPaymentNotDone();
    }
    
    
    
    
    
    
    
    
}




