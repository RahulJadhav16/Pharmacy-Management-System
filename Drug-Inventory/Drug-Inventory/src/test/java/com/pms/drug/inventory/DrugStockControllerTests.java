package com.pms.drug.inventory;

import com.pms.drug.inventory.Controller.DrugStockController;
import com.pms.drug.inventory.Impl.DrugsStockServiceImpl;
import com.pms.drug.inventory.Model.DrugsStock;
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

public class DrugStockControllerTests {
    @Mock
    private DrugsStockServiceImpl drugsService;

    @InjectMocks
    private DrugStockController drugStockController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAll() {
        // Prepare test data
        List<DrugsStock> expectedStock = new ArrayList<>();
        // Add some drugs stock to the expected list
        DrugsStock stock1 = new DrugsStock("1", "supplier@example.com", "Paracetamol", 100, "ABC123", 10.5, LocalDate.now(), "Available");
        expectedStock.add(stock1);

        // Mock the drugsService's behavior
        when(drugsService.getAll()).thenReturn(expectedStock);

        // Call the API endpoint
        ResponseEntity<List<DrugsStock>> response = drugStockController.getAll();

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedStock, response.getBody());

        // Verify that the drugsService's method was called
        verify(drugsService).getAll();
    }

    @Test
    void testGetDrugByName() {
        // Prepare test data
        String name = "Paracetamol";
        List<DrugsStock> expectedStock = new ArrayList<>();
        // Add some drugs stock to the expected list
        DrugsStock stock1 = new DrugsStock("1", "supplier@example.com", "Paracetamol", 100, "ABC123", 10.5, LocalDate.now(), "Available");
        expectedStock.add(stock1);

        // Mock the drugsService's behavior
        when(drugsService.getByDrugName(name)).thenReturn(expectedStock);

        // Call the API endpoint
        ResponseEntity<List<DrugsStock>> response = drugStockController.getDrugByNme(name);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedStock, response.getBody());

        // Verify that the drugsService's method was called
        verify(drugsService).getByDrugName(name);
    }
    
    @Test
    void testGetByExpireDate() {
        // Prepare test data
        LocalDate date = LocalDate.now();
        List<DrugsStock> expectedDrugs = new ArrayList<>();
        // Add some drugs to the expected list
        DrugsStock drug1 = new DrugsStock("1", "supplier1@example.com", "Paracetamol", 50, "B123", 10.0, date, "Active");
        expectedDrugs.add(drug1);

        // Mock the drugsService's behavior
        when(drugsService.getByExpireDate(date)).thenReturn(expectedDrugs);

        // Call the API endpoint
        ResponseEntity<List<DrugsStock>> response = drugStockController.getByExpireDate(date);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedDrugs, response.getBody());

        // Verify that the drugsService's method was called
        verify(drugsService).getByExpireDate(date);
    }

    @Test
    void testGetByBatchNo() {
        // Prepare test data
        String batchNo = "B123";
        List<DrugsStock> expectedDrugs = new ArrayList<>();
        // Add some drugs to the expected list
        DrugsStock drug1 = new DrugsStock("1", "supplier1@example.com", "Paracetamol", 50, batchNo, 10.0, LocalDate.now(), "Active");
        expectedDrugs.add(drug1);

        // Mock the drugsService's behavior
        when(drugsService.getByBatchNo(batchNo)).thenReturn(expectedDrugs);

        // Call the API endpoint
        ResponseEntity<List<DrugsStock>> response = drugStockController.getByBatchNo(batchNo);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedDrugs, response.getBody());

        // Verify that the drugsService's method was called
        verify(drugsService).getByBatchNo(batchNo);
    }

    @Test
    void testCreateDrugsStock() {
        // Prepare test data
        DrugsStock drugsStock = new DrugsStock("1", "supplier1@example.com", "Paracetamol", 50, "B123", 10.0, LocalDate.now(), "Active");

        // Mock the drugsService's behavior
        when(drugsService.createDrugsStock(drugsStock)).thenReturn(drugsStock);

        // Call the API endpoint
        ResponseEntity<DrugsStock> response = drugStockController.createDrugsStock(drugsStock);

        // Verify the response
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(drugsStock, response.getBody());

        // Verify that the drugsService's method was called
        verify(drugsService).createDrugsStock(drugsStock);
    }

    @Test
    void testUpdateDrugsStock() {
        // Prepare test data
        DrugsStock drugsStock = new DrugsStock("1", "supplier1@example.com", "Paracetamol", 50, "B123", 10.0, LocalDate.now(), "Active");

        // Mock the drugsService's behavior
        when(drugsService.updateDrugsStock(drugsStock)).thenReturn(drugsStock);

        // Call the API endpoint
        ResponseEntity<DrugsStock> response = drugStockController.updateDrugsStock(drugsStock);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(drugsStock, response.getBody());

        // Verify that the drugsService's method was called
        verify(drugsService).updateDrugsStock(drugsStock);
    }

    @Test
    void testDeleteDrugsStocById() {
        // Prepare test data
        String id = "1";

        // Mock the drugsService's behavior
        when(drugsService.deleteDrugsStocById(id)).thenReturn("Deleted");

        // Call the API endpoint
        ResponseEntity<String> response = drugStockController.deleteDrugsStocById(id);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Deleted", response.getBody());

        // Verify that the drugsService's method was called
        verify(drugsService).deleteDrugsStocById(id);
    }

    

}

