package com.pms.pickup.service;

import com.pms.pickup.service.Model.PaymentDetails;
import com.pms.pickup.service.Controller.pickupControllerDoctor;
import com.pms.pickup.service.Controller.pickupControllerAdmin;
import com.pms.pickup.service.Impl.pickupServiceDoctorImpl;
import com.pms.pickup.service.Impl.pickupServiceImpl;
import com.pms.pickup.service.Model.Pickup;
import com.pms.pickup.service.Service.pickupServiceDoctor;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PickupServiceAdminTests{
	  @Mock
	    private pickupServiceDoctor pickupService;

	  @Mock
	  private pickupServiceDoctorImpl doctorimpl;

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
	    void testGetAllPickupsAdmin() {

	        List<Pickup> expectedPickups = new ArrayList<>();

	        Pickup pickup1 = new Pickup("1", 100.0, LocalDate.now(), true, 50.0, new ArrayList<>());
	        Pickup pickup2 = new Pickup("2", 200.0, LocalDate.now(), true, 150.0, new ArrayList<>());
	        expectedPickups.add(pickup1);
	        expectedPickups.add(pickup2);

	        when(pickupServiceadmin.getAll()).thenReturn(expectedPickups);

	        ResponseEntity<List<Pickup>> response = pickupControlleradmin.getAll();

	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(expectedPickups, response.getBody());

	        verify(pickupServiceadmin).getAll();
	    }

	    @Test
	    void testGetByPickupId() {

	        String pickupId = "1";
	        Pickup expectedPickup = new Pickup(pickupId, 100.0, LocalDate.now(), true, 50.0, new ArrayList<>());

	        when(pickupServiceadmin.getByPickupId(pickupId)).thenReturn(expectedPickup);

	        ResponseEntity<Pickup> response = pickupControlleradmin.getByPickupId(pickupId);

	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(expectedPickup, response.getBody());

	        verify(pickupServiceadmin).getByPickupId(pickupId);
	    }

	    @Test
	    void testGetByDoctorId() {

	        String doctorId = "1";
	        List<Pickup> expectedPickups = new ArrayList<>();

	        Pickup pickup1 = new Pickup("1", 100.0, LocalDate.now(), true, 50.0, new ArrayList<>());
	        Pickup pickup2 = new Pickup("2", 200.0, LocalDate.now(), true, 150.0, new ArrayList<>());
	        expectedPickups.add(pickup1);
	        expectedPickups.add(pickup2);

	        when(pickupServiceadmin.getByDoctorId(doctorId)).thenReturn(expectedPickups);

	        ResponseEntity<List<Pickup>> response = pickupControlleradmin.getByDoctorId(doctorId);

	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(expectedPickups, response.getBody());

	        verify(pickupServiceadmin).getByDoctorId(doctorId);
	    }

	    @Test
	    void testGetPickupPaymentDone() {

	        List<Pickup> expectedPickups = new ArrayList<>();

	        Pickup pickup1 = new Pickup("1", 100.0, LocalDate.now(), true, 50.0, new ArrayList<>());
	        Pickup pickup2 = new Pickup("2", 200.0, LocalDate.now(), true, 150.0, new ArrayList<>());
	        expectedPickups.add(pickup1);
	        expectedPickups.add(pickup2);

	        when(pickupServiceadmin.getPickupPaymentDone()).thenReturn(expectedPickups);

	        ResponseEntity<List<Pickup>> response = pickupControlleradmin.getPickupPaymentDone();

	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(expectedPickups, response.getBody());

	        verify(pickupServiceadmin).getPickupPaymentDone();
	    }
	    @Test
	    void testGetPickupPaymentNotDone() {

	        List<Pickup> expectedPickups = new ArrayList<>();

	        Pickup pickup1 = new Pickup("1", 100.0, LocalDate.now(), false, 0.0, new ArrayList<>());
	        Pickup pickup2 = new Pickup("2", 200.0, LocalDate.now(), false, 0.0, new ArrayList<>());
	        expectedPickups.add(pickup1);
	        expectedPickups.add(pickup2);

	        when(pickupServiceadmin.getPickupPaymentNotDone()).thenReturn(expectedPickups);

	        ResponseEntity<List<Pickup>> response = pickupControlleradmin.getPickupPaymentNotDone();

	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(expectedPickups, response.getBody());

	        verify(pickupServiceadmin).getPickupPaymentNotDone();
	    }

	    @Test
	    void testDeletePickup() {

	        String pickupId = "pickupId";

	        when(pickupServiceadmin.deletePickup(pickupId)).thenReturn("Pickup deleted successfully");

	        ResponseEntity<String> response = pickupControlleradmin.deletePickup(pickupId);

	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals("Pickup deleted successfully", response.getBody());

	        verify(pickupServiceadmin).deletePickup(pickupId);
	    }

	    @Test
	    void testCreateSinglePickup() {

	        Pickup pickupObj = new Pickup("1232", 100.0, LocalDate.now(), true, 50.0, new ArrayList<>());

	        Pickup expectedPickup = new Pickup("1232", 100.0, LocalDate.now(), true, 50.0, new ArrayList<>());

	        when(pickupServiceadmin.createSinglePickup(pickupObj)).thenReturn(expectedPickup);

	        ResponseEntity<Pickup> response = pickupControlleradmin.createSinglePickup(pickupObj);

	        assertEquals(HttpStatus.CREATED, response.getStatusCode());
	        assertEquals(expectedPickup, response.getBody());

	        verify(pickupServiceadmin).createSinglePickup(pickupObj);
	    }

	    @Test
	    void testGetAllPaymentDetails() {
	    	LocalDate date =LocalDate.now();
	        String orderId = "12345";
	        List<PaymentDetails> expectedPaymentDetailsList = new ArrayList<>();
	        PaymentDetails payment=new PaymentDetails("id","orderid",100.0,date,"doctor@gmail.com");
	        expectedPaymentDetailsList.add(payment);

	        when(doctorimpl.getAllPaymentDetails()).thenReturn(expectedPaymentDetailsList);

	        ResponseEntity<List<PaymentDetails>> response = pickupControlleradmin.getAllPaymentDetails();

	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(expectedPaymentDetailsList, response.getBody());

	        verify(doctorimpl).getAllPaymentDetails();
	    }

	    @Test
	    void testGetPaymentDetailsById() {

	        String paymentId = "12345";
	        PaymentDetails expectedPaymentDetails = new PaymentDetails();

	        when(doctorimpl.getBypaymentID(paymentId)).thenReturn(expectedPaymentDetails);

	        ResponseEntity<PaymentDetails> response = pickupControlleradmin.getBypaymentID(paymentId);

	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(expectedPaymentDetails, response.getBody());

	        verify(doctorimpl).getBypaymentID(paymentId);
	    }

	    @Test
	    void testGetPaymentDetailsByOrderId() {
	    	LocalDate date =LocalDate.now();
	        String orderId = "12345";
	        List<PaymentDetails> expectedPaymentDetailsList = new ArrayList<>();
	        PaymentDetails payment=new PaymentDetails("id","orderid",100.0,date,"doctor@gmail.com");
	        expectedPaymentDetailsList.add(payment);

	        when(doctorimpl.getByOrderID(orderId)).thenReturn(expectedPaymentDetailsList);

	        ResponseEntity<List<PaymentDetails>> response = pickupControlleradmin.getByOrderID(orderId);

	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(expectedPaymentDetailsList, response.getBody());

	        verify(doctorimpl).getByOrderID(orderId);
	    }

}