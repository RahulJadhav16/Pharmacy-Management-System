package com.pms.AdminMicroservice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.pms.AdminMicroservice.Controller.AdminOprationsController;
import com.pms.AdminMicroservice.Impl.AdminProfileImgImpl;
import com.pms.AdminMicroservice.Impl.AdminProfileImpl;
import com.pms.AdminMicroservice.Impl.DrugCatalogueServiceImpl;
import com.pms.AdminMicroservice.Impl.DrugStockServiceImpl;
import com.pms.AdminMicroservice.Impl.PickupServiceImpl;
import com.pms.AdminMicroservice.Impl.VerifyOrderServiceImpl;
import com.pms.AdminMicroservice.Model.AdminDetails;
import com.pms.AdminMicroservice.Model.Drug;
import com.pms.AdminMicroservice.Model.DrugsStock;
import com.pms.AdminMicroservice.Model.Order;
import com.pms.AdminMicroservice.Model.PaymentDetails;
import com.pms.AdminMicroservice.Model.Pickup;

import java.time.LocalDate;

@SpringBootTest
class AdminMicroserviceApplicationTests {
	 
	 @Mock
	 private AdminProfileImgImpl adminProfileImgImpl;
	 
	 @Mock
	 private DrugCatalogueServiceImpl catalogueServiceImpl;
	 
	 @Mock
	 private DrugStockServiceImpl drugStockServiceImpl;
	 
	 @Mock
	 private VerifyOrderServiceImpl verifyOrderServiceImpl;
	 
	 @Mock
	 private PickupServiceImpl pickupServiceImpl;
	 
	 @Mock
	 private AdminProfileImpl adminProfileImpl;
	 
	 @InjectMocks
	 private AdminOprationsController adminOprationsController;
	 
	 @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }

	 
	 
	 LocalDate date= LocalDate.now();
	
	   @Test
	    public void testCreateAdmin() {
		    long id=1234;
	        AdminDetails adminDetails = new AdminDetails(id,"admin@gmail.com","password","name");
	     
	        when(adminProfileImpl.createAdmin(any(AdminDetails.class))).thenReturn(adminDetails);

	        
	        ResponseEntity<AdminDetails> response = adminOprationsController.createAdmin(adminDetails);

	        // Verify the response
	        assertEquals(HttpStatus.CREATED, response.getStatusCode());
	        assertEquals(adminDetails, response.getBody());

	        // Verify that the adminProfileImpl's method was called
	        verify(adminProfileImpl).createAdmin(adminDetails);
	    }
	  
	    @Test
	    public void testUpdateAdmin() {
	    	long id=1234;
	        AdminDetails adminDetails = new AdminDetails(id,"admin@gmail.com","password","name");
	        

	        when(adminProfileImpl.updateAdmin(any(AdminDetails.class))).thenReturn(adminDetails);

	        ResponseEntity<AdminDetails> response = adminOprationsController.updateAdmin(adminDetails);

	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(adminDetails, response.getBody());

	        // Verify that the adminProfileImpl's method was called
	        verify(adminProfileImpl).updateAdmin(adminDetails);
	    }
	    
	    /////////////Test cases for All drugs 
	    @Test
	    public void testViewAllDrugs() {
	       
	        List<Drug> drugs = new ArrayList<>();
	        drugs.add(new Drug("123","Aspirin",50,"Tablet","Pain reliever"));

	      
	        when(catalogueServiceImpl.getalldrugs()).thenReturn(drugs);

	        
	        ResponseEntity<List<Drug>> response = adminOprationsController.getalldrugs();

	        // Verify the response
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(drugs, response.getBody());

	     
	        verify(catalogueServiceImpl).getalldrugs();
	    }
	    
	    @Test
	    public void testGetDrugById() {
	        
	        String drugId = "123";
	        Drug drug = new Drug("123","Aspirin",50,"Tablet","Pain reliever");

	       
	        when(catalogueServiceImpl.getdrugbyid(drugId)).thenReturn(drug);

	       
	        ResponseEntity<Drug> response = adminOprationsController.getdrugbyid(drugId);

	    
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(drug, response.getBody());
	        verify(catalogueServiceImpl).getdrugbyid(drugId);
	    }
	    
	    @Test
	    public void testGetDrugByName() {
	        // Prepare test data
	        String drugName = "Aspirin";
	        List<Drug> drugs = new ArrayList<>();
	        
	        drugs.add(new Drug("123","Aspirin",50,"Tablet","Pain reliever"));
	       
	        when(catalogueServiceImpl.getdrugbyname(drugName)).thenReturn(drugs);

	       
	        ResponseEntity<List<Drug>> response = adminOprationsController.getdrugbyname(drugName);

	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(drugs, response.getBody());

	        verify(catalogueServiceImpl).getdrugbyname(drugName);
	    }
	    
	    @Test
	    public void testCreateDrug() {
	       
	        Drug drug = new Drug("123","Aspirin",50,"Tablet","Pain reliever");

	        
	        when(catalogueServiceImpl.createdrug(drug)).thenReturn(drug);

	        ResponseEntity<Drug> response = adminOprationsController.createdrug(drug);

	        assertEquals(HttpStatus.CREATED, response.getStatusCode());
	        assertEquals(drug, response.getBody());
	        verify(catalogueServiceImpl).createdrug(drug);
	    }
	    
	    
	    @Test
	    public void testUpdateDrug() {
	        // Prepare test data
	        Drug drug = new Drug("123","Aspirin",50,"Tablet","Pain reliever");
	        when(catalogueServiceImpl.updatedrug(drug)).thenReturn(drug);

	        
	        ResponseEntity<Drug> response = adminOprationsController.updatedrug(drug);

	        assertEquals(HttpStatus.CREATED, response.getStatusCode());
	        assertEquals(drug, response.getBody());
	        verify(catalogueServiceImpl).updatedrug(drug);
	    }
	    
	    @Test
	    public void testDeleteDrug() {
	      
	        String drugId = "123";
	        when(catalogueServiceImpl.deletedrug(drugId)).thenReturn("Drug deleted successfully");
	        ResponseEntity<String> response = adminOprationsController.deletedrug(drugId);
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals("Drug deleted successfully", response.getBody());
	        verify(catalogueServiceImpl).deletedrug(drugId);
	    }
	    
	    
	    ///////////////Test cases for drugs stock 
	    @Test
	    public void testCreateStock() {
	    	
	    	
	    	 
             DrugsStock drugsStock = new DrugsStock("12345", "supplier@gmail.com", "aspirin", 100, "A011", 100.0, date,"Not expired");

	        when(drugStockServiceImpl.createStock(any(DrugsStock.class))).thenReturn(drugsStock);

	        
	        ResponseEntity<DrugsStock> response = adminOprationsController.createStock(drugsStock);

	        // Verify the response
	        assertEquals(HttpStatus.CREATED, response.getStatusCode());
	        assertEquals(drugsStock, response.getBody());

	       
	        verify(drugStockServiceImpl).createStock(drugsStock);
	    }
	    
	    @Test
	    public void testGetAllStock() {
	        List<DrugsStock> stockList = new ArrayList<>();
	        DrugsStock drugsStock = new DrugsStock("12345", "supplier@gmail.com", "aspirin", 100, "A011", 100.0, date,"Not expired");
	        stockList.add(drugsStock);
	        when(drugStockServiceImpl.getAllStock()).thenReturn(stockList);
	        ResponseEntity<List<DrugsStock>> response = adminOprationsController.getAllStock();
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(stockList, response.getBody());
	        verify(drugStockServiceImpl).getAllStock();
	    }
	    
	    @Test
	    public void testGetByDrugName() {
	        // Prepare test data
	        String drugName = "Aspirin";
	        List<DrugsStock> stockList = new ArrayList<>();
	        DrugsStock drugsStock = new DrugsStock("12345", "supplier@gmail.com", "aspirin", 100, "A011", 100.0, date,"Not expired");
	        stockList.add(drugsStock);
	        when(drugStockServiceImpl.getByDrugName(drugName)).thenReturn(stockList);

	        ResponseEntity<List<DrugsStock>> response = adminOprationsController.getByDrugName(drugName);
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(stockList, response.getBody());
	        verify(drugStockServiceImpl).getByDrugName(drugName);
	    }
	    
	    @Test
	    public void testGetByBatchNo() {
	        // Prepare test data
	        String batchNo = "B001";
	        List<DrugsStock> stockList = new ArrayList<>();
	        DrugsStock drugsStock = new DrugsStock("12345", "supplier@gmail.com", "aspirin", 100, "B001", 100.0, date,"Not expired");
	        stockList.add(drugsStock);
	        when(drugStockServiceImpl.getByBatchNo(batchNo)).thenReturn(stockList);

	        ResponseEntity<List<DrugsStock>> response = adminOprationsController.getByBatchNo(batchNo);
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(stockList, response.getBody());
	        verify(drugStockServiceImpl).getByBatchNo(batchNo);
	    }
	    
	    @Test
	    public void testUpdateStock() {
	        DrugsStock stock = new DrugsStock("12345", "supplier@gmail.com", "aspirin", 100, "B001", 100.0, date,"Not expired");

	       
	        when(drugStockServiceImpl.updateStock(stock)).thenReturn(stock);
	        ResponseEntity<DrugsStock> response = adminOprationsController.updateStock(stock);
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(stock, response.getBody());
	        verify(drugStockServiceImpl).updateStock(stock);
	    }
	    
	    @Test
	    public void testDeleteStock() {
	        String stockId = "123";
	        when(drugStockServiceImpl.deleteStock(stockId)).thenReturn("Stock deleted successfully");
	        ResponseEntity<String> response = adminOprationsController.deleteStock(stockId);
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals("Stock deleted successfully", response.getBody());
	        verify(drugStockServiceImpl).deleteStock(stockId);
	    }

	    
	    
	    
	   ////////////// /// Test cases for orders 
	    @Test
	    public void testAllOrders() {
	        
	        List<Order> orders = new ArrayList<>();
	        
	        Order order1 = new Order("1", "D001", "Dr. John Doe", "Drug1", 5, true, LocalDate.now());
	        Order order2 = new Order("2", "D001", "Dr. John Doe", "Drug2", 3, true, LocalDate.now());
	        orders.add(order1);
	        orders.add(order2);
	        
	        when(verifyOrderServiceImpl.allOrders()).thenReturn(orders);
	        ResponseEntity<List<Order>> response = adminOprationsController.allOrders();
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(orders, response.getBody());
	        verify(verifyOrderServiceImpl).allOrders();
                   }
	    
	    @Test
	    public void testGetOrderByDoctorId() {
	        
	        String doctorId = "D001";
	        List<Order> orders = new ArrayList<>();
	        Order order1 = new Order("1", "D001", "Dr. John Doe", "Drug1", 5, true, LocalDate.now());
	        Order order2 = new Order("2", "D001", "Dr. John Doe", "Drug2", 3, true, LocalDate.now());
	        orders.add(order1);
	        orders.add(order2);
	        
	        when(verifyOrderServiceImpl.getOrderByDoctorId(doctorId)).thenReturn(orders);
	        ResponseEntity<List<Order>> response = adminOprationsController.getOrderByDoctorId(doctorId);
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(orders, response.getBody());
	        verify(verifyOrderServiceImpl).getOrderByDoctorId(doctorId);
	    }
	    
	    @Test
	    public void testGetOrderById() {
	        // Prepare test data
	        String orderId = "1";
	        Order order = new Order("1", "D001", "Dr. John Doe", "Drug1", 5, true, LocalDate.now());
	        when(verifyOrderServiceImpl.getOrderById(orderId)).thenReturn(order);
	        ResponseEntity<Order> response = adminOprationsController.getOrderById(orderId);
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(order, response.getBody());
	        verify(verifyOrderServiceImpl).getOrderById(orderId);
	    }
	    
	    @Test
	    public void testVerifyOrder() {
	        Order order = new Order("1", "D001", "Dr. John Doe", "Drug1", 5, true, LocalDate.now());
	        when(verifyOrderServiceImpl.verifyOrder(order)).thenReturn(order);
	        ResponseEntity<Order> response = adminOprationsController.verifyOrder(order);
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(order, response.getBody());
 
	        verify(verifyOrderServiceImpl).verifyOrder(order);
	    }
	    
	    @Test
	    public void testDeleteOrder() {
	        
	        String orderId = "1";
	        when(verifyOrderServiceImpl.deleteOrder(orderId)).thenReturn("Order deleted successfully");
	        ResponseEntity<String> response = adminOprationsController.deleteOrder(orderId);
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals("Order deleted successfully", response.getBody());
	        verify(verifyOrderServiceImpl).deleteOrder(orderId);
	    }
	    
	    
	    ///////// Test cases for Pickupservice 
	    @Test
	    public void testViewAllPickups() {
	    
	        List<Pickup> pickups = new ArrayList<>();

	   
	     ArrayList<Order> orders1 = new ArrayList<>();
	     
	     Order order1 = new Order("orderId1", "doctorId1", "doctorName1", "drugName1", 1, true, LocalDate.now());
	     orders1.add(order1);
	     Pickup pickup1 = new Pickup("1", 100.0, LocalDate.now(), true, 50.0, orders1);

	     ArrayList<Order> orders2 = new ArrayList<>();
	     
	     Order order2 = new Order("orderId2", "doctorId2", "doctorName2", "drugName2", 2, true, LocalDate.now());
	     orders2.add(order2);
	     Pickup pickup2 = new Pickup("2", 150.0, LocalDate.now(), true, 100.0, orders2);

	     pickups.add(pickup1);
	     pickups.add(pickup2);
	        
	        when(pickupServiceImpl.getAllPickups()).thenReturn(pickups);

	      
	        ResponseEntity<List<Pickup>> response = adminOprationsController.getAllPickups();

	       
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(pickups, response.getBody());

	        
	        verify(pickupServiceImpl).getAllPickups();
	    }
	    
	    @Test
	    public void testGetPickupByDoctorId() {
	    	   // Preparing  test data
	        String doctorId = "doctorId1";
	        List<Pickup> pickups = new ArrayList<>();
	        ArrayList<Order> orders1 = new ArrayList<>();
		     Order order1 = new Order("orderId1", "doctorId1", "doctorName1", "drugName1", 1, true, LocalDate.now());
		     orders1.add(order1);
		     Pickup pickup1 = new Pickup("1", 100.0, LocalDate.now(), true, 50.0, orders1);
		     ArrayList<Order> orders2 = new ArrayList<>();
		     Order order2 = new Order("orderId2", "doctorId1", "doctorName2", "drugName2", 2, true, LocalDate.now());
		     orders2.add(order2);
		     Pickup pickup2 = new Pickup("2", 150.0, LocalDate.now(), true, 100.0, orders2);
		     pickups.add(pickup1);
		     pickups.add(pickup2);
	        
	       

	      
	        when(pickupServiceImpl.getByDoctorId(doctorId)).thenReturn(pickups);
	        ResponseEntity<List<Pickup>> response = adminOprationsController.getByDoctorId(doctorId);
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(pickups, response.getBody());
	        verify(pickupServiceImpl).getByDoctorId(doctorId);
	    }
	    
	    @Test
	    public void testGetByPickupId() {
	        // Prepare test data
	        String pickupId = "P001";
	        ArrayList<Order> orders1 = new ArrayList<>();
		    Order order1 = new Order("orderId1", "doctorId1", "doctorName1", "drugName1", 1, true, LocalDate.now());
		    orders1.add(order1);
	        Pickup pickup = new Pickup("P001", 100.0, LocalDate.now(), true, 50.0, orders1);
	       
	        when(pickupServiceImpl.getByPickupId(pickupId)).thenReturn(pickup);
	        ResponseEntity<Pickup> response = adminOprationsController.getByPickupId(pickupId);
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(pickup, response.getBody());
	        verify(pickupServiceImpl).getByPickupId(pickupId);
	    }
	    
	    @Test
	    public void testDeletePickup() {
	       
	        String pickupId = "P001";
	        when(pickupServiceImpl.deletePickup(pickupId)).thenReturn("Pickup deleted successfully");
	        ResponseEntity<String> response = adminOprationsController.deletePickup(pickupId);
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals("Pickup deleted successfully", response.getBody());
	        verify(pickupServiceImpl).deletePickup(pickupId);
	    }
	    
	    
	    ///// test cases for payment
	    @Test
	    public void testGetAllPaymentDetails() {
	        // Prepare test data
	        List<PaymentDetails> paymentDetailsList = new ArrayList<>();
	        PaymentDetails payment=new PaymentDetails("12312","orderid",100.0,date,"doctor@gmail.com");
	        paymentDetailsList.add(payment);
	       
	        when(pickupServiceImpl.getAllPaymentDetails()).thenReturn(paymentDetailsList);

	        
	        ResponseEntity<List<PaymentDetails>> response = adminOprationsController.getAllPaymentDetails();
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(paymentDetailsList, response.getBody());
	        verify(pickupServiceImpl).getAllPaymentDetails();
	    }
	    
	    @Test
	    public void testGetBypaymentID() {
	        String paymentId = "PAY001";
	        PaymentDetails paymentDetails = new PaymentDetails("PAY001","orderid",100.0,date,"doctor@gmail.com");
	       
	        when(pickupServiceImpl.getBypaymentID(paymentId)).thenReturn(paymentDetails);

	        ResponseEntity<PaymentDetails> response = adminOprationsController.getBypaymentID(paymentId);

	       
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(paymentDetails, response.getBody());
	        verify(pickupServiceImpl).getBypaymentID(paymentId);
	    }
	    
	    @Test
	    public void testGetPaymentDetailsByOrderid() {
	        String orderId = "ORD001";
	        List<PaymentDetails> paymentDetailsList = new ArrayList<>();
	        PaymentDetails payment=new PaymentDetails("12312","ORD001",100.0,date,"doctor@gmail.com");
	        paymentDetailsList.add(payment);
	       	        
	        when(pickupServiceImpl.getByOrderID(orderId)).thenReturn(paymentDetailsList);
	        ResponseEntity<List<PaymentDetails>> response = adminOprationsController.getPaymentDetailsByOrderid(orderId);

	        
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(paymentDetailsList, response.getBody());

	        
	        verify(pickupServiceImpl).getByOrderID(orderId);
	        }

	    
	    
	    

}
