package com.pms.order.service;


import com.pms.order.service.Controller.OrderServiceAdminController;
import com.pms.order.service.Controller.OrderServiceController;
import com.pms.order.service.Impl.orderServiceAdminImpl;
import com.pms.order.service.Impl.orderServiceImpl;
import com.pms.order.service.Model.order;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class OrderServiceApplicationTests {

    @Mock
    private orderServiceImpl orderService;
    
    @Mock
    private orderServiceAdminImpl orderServiceadminImpl;

    @InjectMocks
    private OrderServiceController orderController;
    
    @InjectMocks
    private OrderServiceAdminController orderAdminController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateOrder() {
        // Prepare test data
    	order order1 = new order("1", "D001", "Dr. John Doe", "Drug1", 5, true, LocalDate.now());

        // Mock the orderService's behavior
        when(orderService.createOrder(order1)).thenReturn(order1);

        // Call the API endpoint
        ResponseEntity<order> response = orderController.createOrder(order1);

        // Verify the response
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(order1, response.getBody());

        // Verify that the orderService's method was called
        verify(orderService).createOrder(order1);
    }

    @Test
    void testGetOrders() {
        // Prepare test data
        String id = "123";
       
        List<order> expectedOrders = new ArrayList<>();
        // Add some orders to the expected list
        order order1 = new order("1", "D001", "Dr. John Doe", "Drug1", 5, true, LocalDate.now());
        order order2 = new order("2", "D001", "Dr. John Doe", "Drug2", 3, true, LocalDate.now());
        expectedOrders.add(order1);
        expectedOrders.add(order2);

        // Mock the orderService's behavior
        when(orderService.getOrders(id)).thenReturn(expectedOrders);

        // Call the API endpoint
        ResponseEntity<List<order>> response = orderController.getOrders(id);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedOrders, response.getBody());

        // Verify that the orderService's method was called
        verify(orderService).getOrders(id);
    }

    @Test
    void testGetOrderByOrderId() {
        // Prepare test data
        String orderId = "123";
        order expectedOrder = new order("1", "D001", "Dr. John Doe", "Drug1", 5, true, LocalDate.now());

        // Mock the orderService's behavior
        when(orderService.getOrderByOrderId(orderId)).thenReturn(expectedOrder);

        // Call the API endpoint
        ResponseEntity<order> response = orderController.getOrderByOrderId(orderId);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedOrder, response.getBody());

        // Verify that the orderService's method was called
        verify(orderService).getOrderByOrderId(orderId);
    }

    @Test
    void testUpdateOrder() {
        // Prepare test data
        order order1 = new order("1", "D001", "Dr. John Doe", "Drug1", 5, true, LocalDate.now());

        // Mock the orderService's behavior
        when(orderService.updateOrder(order1)).thenReturn(order1);

        // Call the API endpoint
        ResponseEntity<order> response = orderController.updateOrder(order1);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(order1, response.getBody());

        // Verify that the orderService's method was called
        verify(orderService).updateOrder(order1);
    }

    @Test
    void testDeleteOrder() {
        // Prepare test data
        String id = "1";

        // Mock the orderService's behavior
        when(orderService.deleteOrder(id)).thenReturn("Order deleted");

        // Call the API endpoint
        ResponseEntity<String> response = orderController.deleteOrder(id);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Order deleted", response.getBody());

        // Verify that the orderService's method was called
        verify(orderService).deleteOrder(id);
    }
    
    
    ////// Test cases for admin /////////////////////////////
    
    @Test
    void testGetAllOrders() {
        // Prepare test data
    	List<order> expectedOrders = new ArrayList<>();
        // Add some orders to the expected list
        order order1 = new order("1", "D001", "Dr. John Doe", "Drug1", 5, true, LocalDate.now());
        order order2 = new order("2", "D001", "Dr. John Doe", "Drug2", 3, true, LocalDate.now());
        expectedOrders.add(order1);
        expectedOrders.add(order2);
        

        // Mock the orderService's behavior
        when(orderServiceadminImpl.getAllOrders()).thenReturn(expectedOrders);

        // Call the API endpoint
        ResponseEntity<List<order>> response = orderAdminController.getAllOrders();

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedOrders, response.getBody());

        // Verify that the orderService's method was called
        verify(orderServiceadminImpl).getAllOrders();
    }

    @Test
    void testGetOrderById() {
        // Prepare test data
        String id = "1";
        order expectedOrder = new order("1", "D001", "Dr. John Doe", "Drug1", 5, true, LocalDate.now());

        // Mock the orderService's behavior
        when(orderServiceadminImpl.getOrderById(id)).thenReturn(expectedOrder);

        // Call the API endpoint
        ResponseEntity<order> response = orderAdminController.getOrderById(id);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedOrder, response.getBody());

        // Verify that the orderService's method was called
        verify(orderServiceadminImpl).getOrderById(id);
    }
//
    @Test
    void testGetOrderByDoctorId() {
        // Prepare test data
        String doctorId = "D001";
        List<order> expectedOrders = new ArrayList<>();
        // Add some orders to the expected list
        order order1 = new order("1", "D001", "Dr. John Doe", "Drug1", 5, true, LocalDate.now());
        order order2 = new order("2", "D001", "Dr. John Doe", "Drug2", 3, true, LocalDate.now());
        expectedOrders.add(order1);
        expectedOrders.add(order2);
        

        // Mock the orderService's behavior
        when(orderServiceadminImpl.getOrderByDoctorId(doctorId)).thenReturn(expectedOrders);

        // Call the API endpoint
        ResponseEntity<List<order>> response = orderAdminController.getOrderByDoctorId(doctorId);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedOrders, response.getBody());

        // Verify that the orderService's method was called
        verify(orderServiceadminImpl).getOrderByDoctorId(doctorId);
    }
//
    @Test
    void testUpdateOrderStatus() {
        // Prepare test data
        order orderObj = new order("1", "D001", "Dr. John Doe", "Drug1", 5, true, LocalDate.now());

        // Mock the orderService's behavior
        when(orderServiceadminImpl.updateOrderStatus(orderObj)).thenReturn(orderObj);

        // Call the API endpoint
        ResponseEntity<order> response = orderAdminController.updateOrderStatus(orderObj);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(orderObj, response.getBody());

        // Verify that the orderService's method was called
        verify(orderServiceadminImpl).updateOrderStatus(orderObj);
    }
//
    @Test
    void testAddToPickup() {
        // Mock the orderService's behavior
        when(orderServiceadminImpl.addToPickup()).thenReturn("Order added to pickup");

        // Call the API endpoint
        ResponseEntity<String> response = orderAdminController.addToPickup();

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Order added to pickup", response.getBody());

        // Verify that the orderService's method was called
        verify(orderServiceadminImpl).addToPickup();
    }

   



}
