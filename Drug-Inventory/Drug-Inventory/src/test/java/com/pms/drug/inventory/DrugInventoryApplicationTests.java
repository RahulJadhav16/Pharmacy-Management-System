package com.pms.drug.inventory;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

import com.pms.drug.inventory.Controller.DrugController;
import com.pms.drug.inventory.Impl.drugInventoryImpl;
import com.pms.drug.inventory.Model.Drug;
import com.pms.drug.inventory.Repository.drugRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class DrugInventoryApplicationTests {
	@Mock
    private drugInventoryImpl drugImpl;
	
	@Mock
    private drugRepository drugRepository;

    @InjectMocks
    private  DrugController drugController;
    


    @BeforeEach
    void setUp() {
    	
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAll() {
        // Prepare test data
        List<Drug> expectedDrugs = new ArrayList<>();
        // Add some drugs to the expected list
        Drug drug1 = new Drug("1", "Paracetamol", 50, "Tablet", "Paracetamol is a medication used to treat fever and mild to moderate pain.");
        
        expectedDrugs.add(drug1);
         

        // Mock the drugImpl's behavior
        when(drugImpl.getAll()).thenReturn(expectedDrugs);

        // Call the API endpoint
        ResponseEntity<List<Drug>> response = drugController.getAll();

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedDrugs, response.getBody());

        // Verify that the drugImpl's method was called
        verify(drugImpl).getAll();
    }
    

    @Test
    void testGetDrugsByName() {
        // Prepare test data
        String name = "Paracetamol";

        List<Drug> expectedDrugs = new ArrayList<>();

        Drug drug1 = new Drug("1", "Paracetamol", 50, "Tablet", "Paracetamol is a medication used to treat fever and mild to moderate pain.");

        expectedDrugs.add(drug1);

        // Mock the drugImpl's behavior
        when(drugImpl.getDrugsByName(name)).thenReturn(expectedDrugs);

        // Call the API endpoint
        ResponseEntity<List<Drug>> response = drugController.getDrugsByName(name);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedDrugs, response.getBody());

        // Verify that the drugImpl's method was called
        verify(drugImpl).getDrugsByName(name);
    }
    
    


    @Test
    void testGetDrugsById() {
        // Prepare test data
        String id = "123";
        Drug expectedDrug = new Drug("1", "Paracetamol", 50, "Tablet", "Paracetamol is a medication used to treat fever and mild to moderate pain.");

        // Mock the drugImpl's behavior
        when(drugImpl.getDrugById(id)).thenReturn(expectedDrug);

        // Call the API endpoint
        ResponseEntity<Drug> response = drugController.getDrugsById(id);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedDrug, response.getBody());

        // Verify that the drugImpl's method was called
        verify(drugImpl).getDrugById(id);
    }
//
    @Test
    void testAddDrug() {
        // Prepare test data
        Drug drug = new Drug("1", "Paracetamol", 50, "Tablet", "Paracetamol is a medication used to treat fever and mild to moderate pain.");
       ;
        // Mock the drugImpl's behavior
        when(drugImpl.addDrug(drug)).thenReturn(drug);

        // Call the API endpoint
        ResponseEntity<Drug> response = drugController.addDrug(drug);

        // Verify the response
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(drug, response.getBody());
     
        // Verify that the drugImpl's method was called
        verify(drugImpl).addDrug(drug);
    }

    @Test
    void testUpdateDrug() {
        // Prepare test data
        Drug drug = new Drug("1", "Paracetamol", 50, "Tablet", "Paracetamol is a medication used to treat fever and mild to moderate pain.");

        // Mock the drugImpl's behavior
        when(drugImpl.updateDrug(drug)).thenReturn(drug);

        // Call the API endpoint
        ResponseEntity<Drug> response = drugController.updateDrug(drug);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(drug, response.getBody());

        // Verify that the drugImpl's method was called
        verify(drugImpl).updateDrug(drug);
    }

    @Test
    void testDeleteDrug() {
        // Prepare test data
        String id = "123";

        // Mock the drugImpl's behavior
        when(drugImpl.deleteDrug(id)).thenReturn("Drug deleted");

        // Call the API endpoint
        ResponseEntity<String> response = drugController.deleteDrug(id);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Drug deleted", response.getBody());

        // Verify that the drugImpl's method was called
        verify(drugImpl).deleteDrug(id);
    }


}
