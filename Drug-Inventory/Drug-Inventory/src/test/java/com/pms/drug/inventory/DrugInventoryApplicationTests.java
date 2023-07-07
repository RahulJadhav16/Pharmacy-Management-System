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
import com.pms.drug.inventory.Impl.drugImagesImpl;
import com.pms.drug.inventory.Impl.drugInventoryImpl;
import com.pms.drug.inventory.Model.Drug;
import com.pms.drug.inventory.Model.DrugImges;
import com.pms.drug.inventory.Repository.drugRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DrugInventoryApplicationTests {
	@Mock
    private drugInventoryImpl drugImpl;

	@Mock
    private drugRepository drugRepository;

	@Mock
	private drugImagesImpl drugImagesImpl;

    @InjectMocks
    private  DrugController drugController;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAll() {

        List<Drug> expectedDrugs = new ArrayList<>();

        Drug drug1 = new Drug("1", "Paracetamol", 50, "Tablet", "Paracetamol is a medication used to treat fever and mild to moderate pain.");

        expectedDrugs.add(drug1);

        when(drugImpl.getAll()).thenReturn(expectedDrugs);

        ResponseEntity<List<Drug>> response = drugController.getAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedDrugs, response.getBody());

        verify(drugImpl).getAll();
    }

    @Test
    void testGetDrugsByName() {

        String name = "Paracetamol";

        List<Drug> expectedDrugs = new ArrayList<>();

        Drug drug1 = new Drug("1", "Paracetamol", 50, "Tablet", "Paracetamol is a medication used to treat fever and mild to moderate pain.");

        expectedDrugs.add(drug1);

        when(drugImpl.getDrugsByName(name)).thenReturn(expectedDrugs);

        ResponseEntity<List<Drug>> response = drugController.getDrugsByName(name);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedDrugs, response.getBody());

        verify(drugImpl).getDrugsByName(name);
    }

    @Test
    void testGetDrugsById() {

        String id = "123";
        Drug expectedDrug = new Drug("1", "Paracetamol", 50, "Tablet", "Paracetamol is a medication used to treat fever and mild to moderate pain.");

        when(drugImpl.getDrugById(id)).thenReturn(expectedDrug);

        ResponseEntity<Drug> response = drugController.getDrugsById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedDrug, response.getBody());

        verify(drugImpl).getDrugById(id);
    }

    @Test
    void testAddDrug() {

        Drug drug = new Drug("1", "Paracetamol", 50, "Tablet", "Paracetamol is a medication used to treat fever and mild to moderate pain.");
       ;

        when(drugImpl.addDrug(drug)).thenReturn(drug);

        ResponseEntity<Drug> response = drugController.addDrug(drug);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(drug, response.getBody());

        verify(drugImpl).addDrug(drug);
    }

    @Test
    void testUpdateDrug() {

        Drug drug = new Drug("1", "Paracetamol", 50, "Tablet", "Paracetamol is a medication used to treat fever and mild to moderate pain.");

        when(drugImpl.updateDrug(drug)).thenReturn(drug);

        ResponseEntity<Drug> response = drugController.updateDrug(drug);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(drug, response.getBody());

        verify(drugImpl).updateDrug(drug);
    }

    @Test
    void testDeleteDrug() {

        String id = "123";

        when(drugImpl.deleteDrug(id)).thenReturn("Drug deleted");

        ResponseEntity<String> response = drugController.deleteDrug(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Drug deleted", response.getBody());

        verify(drugImpl).deleteDrug(id);
    }

    @Test
    public void testAddDrugImg() {
        String id = "12345";
        MultipartFile file = mock(MultipartFile.class);
        DrugImges drugImg = new DrugImges();

        when(drugImagesImpl.addDrugImg(id, file)).thenReturn(drugImg);

        ResponseEntity<DrugImges> response = drugController.addDrugImg(id, file);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(drugImg, response.getBody());

        verify(drugImagesImpl).addDrugImg(id, file);
    }

    @Test
    public void testGetDrugImg() {

        String id = "12345";
        DrugImges drugImg = new DrugImges();

        when(drugImagesImpl.getDrugImg(id)).thenReturn(drugImg);

        ResponseEntity<DrugImges> response = drugController.getDrugImg(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(drugImg, response.getBody());

        verify(drugImagesImpl).getDrugImg(id);
    }

    @Test
    public void testGetAllDrugImg() {

        List<DrugImges> drugImgsList = new ArrayList<>();
        DrugImges drugImg1 = new DrugImges();
        DrugImges drugImg2 = new DrugImges();
        drugImgsList.add(drugImg1);
        drugImgsList.add(drugImg2);

        when(drugImagesImpl.getAllDrugImg()).thenReturn(drugImgsList);

        ResponseEntity<List<DrugImges>> response = drugController.getAllDrugImg();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(drugImgsList, response.getBody());

        verify(drugImagesImpl).getAllDrugImg();
    }

    @Test
    public void testDeleteImg() {

        String id = "12345";

        when(drugImagesImpl.deleteImg(id)).thenReturn("Image deleted successfully");

        ResponseEntity<String> response = drugController.deleteImg(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Image deleted successfully", response.getBody());

        verify(drugImagesImpl).deleteImg(id);
    }

}