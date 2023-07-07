package com.pms.doctor.service;
import com.pms.doctor.service.Impl.*;
import com.pms.doctor.service.Config.CustomUserDetailsService;
import com.pms.doctor.service.Config.JwtAuthenticationEntryPoint;
import com.pms.doctor.service.Config.JwtRequest;
import com.pms.doctor.service.Controller.doctorController;
import com.pms.doctor.service.Models.Doctor;
import com.pms.doctor.service.Models.DoctorPersonalDetails;
import com.pms.doctor.service.Models.DoctorProfileImg;
import com.pms.doctor.service.Models.Drug;
import com.pms.doctor.service.Models.Order;
import com.pms.doctor.service.Models.Pickup;
import com.pms.doctor.service.Service.doctorPersonalDetailsService;
import com.pms.doctor.service.Exception.DrugNotFoundByname;
import com.pms.doctor.service.Exception.DrugNotFoundById;
import com.pms.doctor.service.Impl.DoctorPersonalDetailsImpl;
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
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class doctorControllerTests {

    @Mock
    private doctorServiceImpl doctorService;
    
    @Mock
    private Doctor doctor;
    
    @Mock
    private DoctorProfileImg doctorProfileImg;
    
    @Mock
    private doctorPersonalDetailsService doctorDetailsService;

    @Mock
    private DoctorPersonalDetailsImpl doctorPersonalDetailsImpl;
    
    @Mock
    private doctorDetailsImpl doctorDetailsImpl;

    @Mock
    private doctorProfileImgImpl doctorProfileImgImpl;

    @InjectMocks
    private  doctorController doctorController;

    @BeforeEach
    void setUp() {
    	
        MockitoAnnotations.openMocks(this);
    }
    
    
    // This is for doctor profile 
    @Test
    public void testGetDetails() {
        //test data
        String doctorId = "D001";
        DoctorPersonalDetails details = new DoctorPersonalDetails(doctorId,"Suraj","9087678767","Doctor@gmail.com","address");
       
        when(doctorPersonalDetailsImpl.getDetails(doctorId)).thenReturn(details);
        ResponseEntity<DoctorPersonalDetails> response = doctorController.getDetails(doctorId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(details, response.getBody());
        verify(doctorPersonalDetailsImpl).getDetails(doctorId);
        }
    
    
    @Test
    public void testAddDetails() {
        // Prepare test data
        Doctor doctor = new Doctor("D101","suraj","9087678767","Doctor@gmail.com","password","address");
        // Set necessary properties of the doctor object

        // Mock the doctorDetailsService's behavior
        when(doctorDetailsImpl.addDetails(doctor)).thenReturn(doctor);

        // Call the API endpoint
        ResponseEntity<Doctor> response = doctorController.addDetails(doctor);

        // Verify the response
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(doctor, response.getBody());

        // Verify that the doctorDetailsService's method was called
        verify(doctorDetailsImpl).addDetails(doctor);
    }
    
    
    
    @Test
    public void testUpdateDetails() {
       
        Doctor doctor = new Doctor("D101","suraj","9087678767","Doctor@gmail.com","password","address");
        when(doctorDetailsImpl.updateDetails(doctor)).thenReturn(doctor);
        ResponseEntity<Doctor> response = doctorController.updateDetails(doctor);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(doctor, response.getBody());
        verify(doctorDetailsImpl).updateDetails(doctor);
    }
    
    @Test
    public void testUploadImg() {
        // Prepare test data
    	String id = "D001";
        byte[] fileContent = "Test file content".getBytes();
        String fileName = "test-file.txt";
        String contentType = "text/plain";
        MockMultipartFile file = new MockMultipartFile(fileName, fileName, contentType, fileContent);
        DoctorProfileImg profileImg = new DoctorProfileImg();
        when(doctorProfileImgImpl.uploadImg(id, file)).thenReturn(profileImg);
        ResponseEntity<DoctorProfileImg> response = doctorController.uploadImg(id, file);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(profileImg, response.getBody());

       
        verify(doctorProfileImgImpl).uploadImg(id, file);
        }
    
    @Test
    public void testGetProfileImg() {
        
        String id = "D001";
        byte[] fileContent = "Test file content".getBytes();
        DoctorProfileImg profileImg = new DoctorProfileImg(id,fileContent);
        
        when(doctorProfileImgImpl.getProfileImg(id)).thenReturn(profileImg);
        ResponseEntity<DoctorProfileImg> response = doctorController.getProfileImg(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(profileImg, response.getBody());
        verify(doctorProfileImgImpl).getProfileImg(id);
    }
    
    @Test
    public void testGetDoctoridBymail() {
        // Prepare test data
        String email = "doctor@abc.com";
        String expectedDoctorId = "D001";
        when(doctorDetailsImpl.getDoctoridBymail(email)).thenReturn(expectedDoctorId);
        String response = doctorController.getDoctoridBymail(email);
        assertEquals(expectedDoctorId, response);
        verify(doctorDetailsImpl).getDoctoridBymail(email);
    }
    
    
    
    
    
    @Test
    public void testExceptionHandler() {
        String message = doctorController.exceptionHandler();
        assertEquals("Credentials Invalid !!", message);
    }
    ///This test cases for Drugs section 
    @Test
    public void testViewAllDrugs() {
        List<Drug> drugs = new ArrayList<>();
        drugs.add(new Drug("123","Aspirin",50,"Tablet","Pain reliever"));
        when(doctorService.viewAllDrugs()).thenReturn(drugs);
        ResponseEntity<List<Drug>> response = doctorController.viewAllDrugs();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(drugs, response.getBody());
        verify(doctorService).viewAllDrugs();
    }
    
    @Test
    public void testDrugByName_ValidName() {
       
        String drugName = "Aspirin";
        List<Drug> drugs = new ArrayList<>();
        drugs.add(new Drug("1", "Aspirin", 10, "Type1", "Category1"));
        drugs.add(new Drug("2", "Aspirin", 20, "Type2", "Category2"));

       
        when(doctorService.drugByName(drugName)).thenReturn(drugs);

        // Call the API endpoint
        ResponseEntity<List<Drug>> response = doctorController.drugByName(drugName);

        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(drugs, response.getBody());

        // Verify that the doctorService's method was called
        verify(doctorService).drugByName(drugName);
    }
    
   
    







    
    @Test
    public void testDrugById_ValidId() {
        
        String drugId = "1";
        Drug drug = new Drug("1", "Aspirin", 10, "Type1", "Category1");

       
        when(doctorService.drugById(drugId)).thenReturn(drug);
        ResponseEntity<Drug> response = doctorController.drugById(drugId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(drug, response.getBody());

        verify(doctorService).drugById(drugId);
    }



    
    
    //////////////////////// Test cases for Order ////////////////////////
    
    @Test
    public void testViewAllOrders() {
        
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
    public void testAddOrder() {
       
        Order orderObj = new Order("1", "D001", "Dr. John Doe", "Drug1", 5, true, LocalDate.now());
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
    public void testDeleteOrder() {
       
        String orderId = "1";

        
        when(doctorService.deleteOrder(orderId)).thenReturn("Order deleted successfully");

      
        ResponseEntity<String> response = doctorController.deleteOrder(orderId);

       
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Order deleted successfully", response.getBody());

       
        verify(doctorService).deleteOrder(orderId);
    }
    
         ///////////////// pickup section ///////////////////////////////////
    
    @Test
    public void testViewAllPickups() {
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
    public void testMakePayment() {
        
        Pickup pickupObj = new Pickup();
        
        ArrayList<Order> orders1 = new ArrayList<>();
        
        Order order1 = new Order("orderId1", "doctorId1", "doctorName1", "drugName1", 1, true, LocalDate.now());
        orders1.add(order1);
        pickupObj = new Pickup("1", 100.0, LocalDate.now(), true, 50.0, orders1);
        when(doctorService.makePayment(pickupObj)).thenReturn(pickupObj);
        ResponseEntity<Pickup> response = doctorController.makePayment(pickupObj);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pickupObj, response.getBody());
        verify(doctorService).makePayment(pickupObj);
    }
    
    ///////////////////////// Test case for fallback method
    
    @Test
    public void testViewAllDrugsFallback() {
        Throwable throwable = new Throwable();
        ResponseEntity<List<Drug>> response = doctorController.viewAllDrugsFallback(throwable);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<Drug> drugsList = response.getBody();
        
        assertEquals(1, drugsList.size());
        Drug drug = drugsList.get(0);
        assertEquals("1234", drug.getId());
        assertEquals("Drug service not avilable",drug.getName());
        assertEquals(0,drug.getPrice());
        assertEquals("",drug.getType());
        assertEquals("",drug.getCategory());
        
        
    }
    
    @Test
    public void testdrugByNameFallback() {
        Throwable throwable = new Throwable();
        ResponseEntity<List<Drug>> response = doctorController.drugByNameFallback("Tablet",throwable);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<Drug> drugsList = response.getBody();
        
        assertEquals(1, drugsList.size());
        Drug drug = drugsList.get(0);
        assertEquals("1234", drug.getId());
        assertEquals("Drug service not avilable",drug.getName());
        assertEquals(0,drug.getPrice());
        assertEquals("",drug.getType());
        assertEquals("",drug.getCategory());
        
        
    } 
    
    @Test
    public void testdrugByIdFallback() {
        Throwable throwable = new Throwable();
        ResponseEntity<Drug> response = doctorController.drugByIdFallback("Tablet",throwable);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Drug drug = response.getBody();
        
        
        assertEquals("1234", drug.getId());
        assertEquals("Drug service not avilable",drug.getName());
        assertEquals(0,drug.getPrice());
        assertEquals("",drug.getType());
        assertEquals("",drug.getCategory());
        
        
    }
    
    @Test
    public void testViewAllOrdersFallback() {
        String doctorId = "D001";
        Throwable throwable = new Throwable();
        ResponseEntity<List<Order>> response = doctorController.viewAllOrdersFallback(doctorId, throwable);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<Order> ordersList = response.getBody();
        assertEquals(0, ordersList.size());
    }
    
    @Test
    public void testAddOrderFallback() {
        Order orderObj = new Order("orderId1", "doctorId1", "doctorName1", "drugName1", 1, true, LocalDate.now());
        Throwable throwable = new Throwable();
        ResponseEntity<Order> response = doctorController.addOrderFallback(orderObj, throwable);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        
    }
    
    @Test
    public void testdeleteOrderFallback() {
        Throwable throwable = new Throwable();
        ResponseEntity<String> response = doctorController.deleteOrderFallback("1234", throwable);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        
    }
    
    @Test
    public void testviewAllPickupsFallback() {
        String doctorId = "D001";
        Throwable throwable = new Throwable();
        ResponseEntity<List<Pickup>> response = doctorController.viewAllPickupsFallback(doctorId, throwable);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<Pickup> pickupList = response.getBody();
        assertEquals(0, pickupList.size());
    }
    
    @Test
    public void testmakePaymentFallback() {
        String doctorId = "D001";
        Throwable throwable = new Throwable();
        Pickup pickupObj = new Pickup();
        
        ArrayList<Order> orders1 = new ArrayList<>();
        
        Order order1 = new Order("orderId1", "doctorId1", "doctorName1", "drugName1", 1, true, LocalDate.now());
        orders1.add(order1);
        pickupObj = new Pickup("1", 100.0, LocalDate.now(), true, 50.0, orders1);
        ResponseEntity<Pickup> response = doctorController.makePaymentFallback(pickupObj, throwable);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        
    }










    
   


   
}

