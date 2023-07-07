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
        
        List<DrugsStock> expectedStock = new ArrayList<>();
        DrugsStock stock1 = new DrugsStock("1", "supplier@example.com", "Paracetamol", 100, "ABC123", 10.5, LocalDate.now(), "Available");
        expectedStock.add(stock1);

        when(drugsService.getAll()).thenReturn(expectedStock);

       
        ResponseEntity<List<DrugsStock>> response = drugStockController.getAll();

        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedStock, response.getBody());

        
        verify(drugsService).getAll();
    }

    @Test
    void testGetDrugByName() {

        String name = "Paracetamol";
        List<DrugsStock> expectedStock = new ArrayList<>();

        DrugsStock stock1 = new DrugsStock("1", "supplier@example.com", "Paracetamol", 100, "ABC123", 10.5, LocalDate.now(), "Available");
        expectedStock.add(stock1);

        when(drugsService.getByDrugName(name)).thenReturn(expectedStock);

        ResponseEntity<List<DrugsStock>> response = drugStockController.getDrugByNme(name);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedStock, response.getBody());

        verify(drugsService).getByDrugName(name);
    }
    
    @Test
    void testGetByExpireDate() {

        LocalDate date = LocalDate.now();
        List<DrugsStock> expectedDrugs = new ArrayList<>();

        DrugsStock drug1 = new DrugsStock("1", "supplier1@example.com", "Paracetamol", 50, "B123", 10.0, date, "Active");
        expectedDrugs.add(drug1);

        when(drugsService.getByExpireDate(date)).thenReturn(expectedDrugs);

        ResponseEntity<List<DrugsStock>> response = drugStockController.getByExpireDate(date);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedDrugs, response.getBody());

        verify(drugsService).getByExpireDate(date);
    }

    @Test
    void testGetByBatchNo() {

        String batchNo = "B123";
        List<DrugsStock> expectedDrugs = new ArrayList<>();

        DrugsStock drug1 = new DrugsStock("1", "supplier1@example.com", "Paracetamol", 50, batchNo, 10.0, LocalDate.now(), "Active");
        expectedDrugs.add(drug1);

        when(drugsService.getByBatchNo(batchNo)).thenReturn(expectedDrugs);

        ResponseEntity<List<DrugsStock>> response = drugStockController.getByBatchNo(batchNo);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedDrugs, response.getBody());

        verify(drugsService).getByBatchNo(batchNo);
    }

    @Test
    void testCreateDrugsStock() {

        DrugsStock drugsStock = new DrugsStock("1", "supplier1@example.com", "Paracetamol", 50, "B123", 10.0, LocalDate.now(), "Active");

        when(drugsService.createDrugsStock(drugsStock)).thenReturn(drugsStock);

        ResponseEntity<DrugsStock> response = drugStockController.createDrugsStock(drugsStock);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(drugsStock, response.getBody());

        verify(drugsService).createDrugsStock(drugsStock);
    }

    @Test
    void testUpdateDrugsStock() {

        DrugsStock drugsStock = new DrugsStock("1", "supplier1@example.com", "Paracetamol", 50, "B123", 10.0, LocalDate.now(), "Active");

        when(drugsService.updateDrugsStock(drugsStock)).thenReturn(drugsStock);

        ResponseEntity<DrugsStock> response = drugStockController.updateDrugsStock(drugsStock);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(drugsStock, response.getBody());

        verify(drugsService).updateDrugsStock(drugsStock);
    }

    @Test
    void testDeleteDrugsStocById() {

        String id = "1";

        when(drugsService.deleteDrugsStocById(id)).thenReturn("Deleted");

        ResponseEntity<String> response = drugStockController.deleteDrugsStocById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Deleted", response.getBody());

        verify(drugsService).deleteDrugsStocById(id);
    }
    
    

    

}

