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

    	order order1 = new order("1", "D001", "Dr. John Doe","abc@exmple.com","mg.road", "Drug1", 5, true, LocalDate.now());

        when(orderService.createOrder(order1)).thenReturn(order1);

        ResponseEntity<order> response = orderController.createOrder(order1);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(order1, response.getBody());

        verify(orderService).createOrder(order1);
    }

    @Test
    void testGetOrders() {

        String id = "123";

        List<order> expectedOrders = new ArrayList<>();

        order order1 = new order("1", "D001", "Dr. John Doe","abc@exmple.com","mg.road", "Drug1", 5, true, LocalDate.now());
        order order2 = new order("2", "D001", "Dr. John Doe","abc@exmple.com","mg.road", "Drug1", 5, true, LocalDate.now());
        expectedOrders.add(order1);
        expectedOrders.add(order2);

        when(orderService.getOrders(id)).thenReturn(expectedOrders);

        ResponseEntity<List<order>> response = orderController.getOrders(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedOrders, response.getBody());

        verify(orderService).getOrders(id);
    }

    @Test
    void testGetOrderByOrderId() {

        String orderId = "123";
        order expectedOrder = new order("1", "D001", "Dr. John Doe","abc@exmple.com","mg.road", "Drug1", 5, true, LocalDate.now());

        when(orderService.getOrderByOrderId(orderId)).thenReturn(expectedOrder);

        ResponseEntity<order> response = orderController.getOrderByOrderId(orderId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedOrder, response.getBody());

        verify(orderService).getOrderByOrderId(orderId);
    }

    @Test
    void testUpdateOrder() {

        order order1 = new order("1", "D001", "Dr. John Doe","abc@exmple.com","mg.road", "Drug1", 5, true, LocalDate.now());

        when(orderService.updateOrder(order1)).thenReturn(order1);

        ResponseEntity<order> response = orderController.updateOrder(order1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(order1, response.getBody());

        verify(orderService).updateOrder(order1);
    }

    @Test
    void testDeleteOrder() {

        String id = "1";

        when(orderService.deleteOrder(id)).thenReturn("Order deleted");

        ResponseEntity<String> response = orderController.deleteOrder(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Order deleted", response.getBody());

        verify(orderService).deleteOrder(id);
    }

    @Test
    void testGetAllOrders() {

    	List<order> expectedOrders = new ArrayList<>();

        order order1 = new order("1", "D001", "Dr. John Doe","abc@exmple.com","mg.road", "Drug1", 5, true, LocalDate.now());
        order order2 = new order("2", "D001", "Dr. John Doe","abc@exmple.com","mg.road", "Drug1", 5, true, LocalDate.now());
        expectedOrders.add(order1);
        expectedOrders.add(order2);

        when(orderServiceadminImpl.getAllOrders()).thenReturn(expectedOrders);

        ResponseEntity<List<order>> response = orderAdminController.getAllOrders();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedOrders, response.getBody());

        verify(orderServiceadminImpl).getAllOrders();
    }

    @Test
    void testGetOrderById() {

        String id = "1";
        order expectedOrder = new order("1", "D001", "Dr. John Doe","abc@exmple.com","mg.road", "Drug1", 5, true, LocalDate.now());

        when(orderServiceadminImpl.getOrderById(id)).thenReturn(expectedOrder);

        ResponseEntity<order> response = orderAdminController.getOrderById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedOrder, response.getBody());

        verify(orderServiceadminImpl).getOrderById(id);
    }

    @Test
    void testGetOrderByDoctorId() {

        String doctorId = "D001";
        List<order> expectedOrders = new ArrayList<>();

        order order1 = new order("1", "D001", "Dr. John Doe","abc@exmple.com","mg.road", "Drug1", 5, true, LocalDate.now());
        order order2 = new order("2", "D001", "Dr. John Doe","abc@exmple.com","mg.road", "Drug1", 5, true, LocalDate.now());
        expectedOrders.add(order1);
        expectedOrders.add(order2);

        when(orderServiceadminImpl.getOrderByDoctorId(doctorId)).thenReturn(expectedOrders);

        ResponseEntity<List<order>> response = orderAdminController.getOrderByDoctorId(doctorId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedOrders, response.getBody());

        verify(orderServiceadminImpl).getOrderByDoctorId(doctorId);
    }

    @Test
    void testUpdateOrderStatus() {

        order orderObj = new order("1", "D001", "Dr. John Doe","abc@exmple.com","mg.road", "Drug1", 5, true, LocalDate.now());

        when(orderServiceadminImpl.updateOrderStatus(orderObj)).thenReturn(orderObj);

        ResponseEntity<order> response = orderAdminController.updateOrderStatus(orderObj);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(orderObj, response.getBody());

        verify(orderServiceadminImpl).updateOrderStatus(orderObj);
    }

    @Test
    void testAddToPickup() {

        when(orderServiceadminImpl.addToPickup()).thenReturn("Order added to pickup");

        ResponseEntity<String> response = orderAdminController.addToPickup();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Order added to pickup", response.getBody());

        verify(orderServiceadminImpl).addToPickup();
    }

    @Test
    public void testDeleteOrderAdmin() {

        String id = "12345";

        when(orderService.deleteOrder(id)).thenReturn("Order deleted successfully");

        ResponseEntity<String> response = orderController.deleteOrder(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Order deleted successfully", response.getBody());

        verify(orderService).deleteOrder(id);
    }

}