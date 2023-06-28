package com.pms.AdminMicroservice.Controller;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pms.AdminMicroservice.Config.JwtHelper;
import com.pms.AdminMicroservice.Config.JwtRequest;
import com.pms.AdminMicroservice.Config.JwtResponse;
import com.pms.AdminMicroservice.Impl.AdminProfileImpl;
import com.pms.AdminMicroservice.Impl.DrugCatalogueServiceImpl;
import com.pms.AdminMicroservice.Impl.DrugStockServiceImpl;
import com.pms.AdminMicroservice.Impl.PickupServiceImpl;
import com.pms.AdminMicroservice.Impl.VerifyOrderServiceImpl;
import com.pms.AdminMicroservice.Model.AdminDetails;
import com.pms.AdminMicroservice.Model.Drug;
import com.pms.AdminMicroservice.Model.DrugsStock;
import com.pms.AdminMicroservice.Model.Order;
import com.pms.AdminMicroservice.Model.Pickup;


@RestController
@RequestMapping("/adminOprations")
public class AdminOprationsController {
	
	@Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;


    @Autowired
    private JwtHelper helper;

    private Logger logger = LoggerFactory.getLogger(AdminOprationsController.class);
	
	@Autowired
	private DrugCatalogueServiceImpl catalogueServiceImpl;
	
	@Autowired
	private DrugStockServiceImpl drugStockServiceImpl;
	
	@Autowired
	private VerifyOrderServiceImpl verifyOrderServiceImpl;
	
	
	@Autowired
	private PickupServiceImpl pickupServiceImpl;
	
	@Autowired
	private AdminProfileImpl adminProfileImpl;
	
	/*
	  This End points are public and for login purpose
  
	 */
	/////////////////////////////////////////////////////////////////////////////////////
	@PostMapping("/create")
	public ResponseEntity<AdminDetails>createAdmin(@RequestBody AdminDetails obj)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(adminProfileImpl.createAdmin(obj));
	}
	
	@PostMapping("/auth/login")
	public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

        this.doAuthenticate(request.getEmail(), request.getPassword());


        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.helper.generateToken(userDetails);

        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .username(userDetails.getUsername()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
	
	@GetMapping("/getAdminDetails/{email}")
	public AdminDetails getAdminDetails(@PathVariable String email)
	{
		return adminProfileImpl.getAdminDetails(email);
		
	}
	
	private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }
	
	 @ExceptionHandler(BadCredentialsException.class)
	    public String exceptionHandler() {
	        return "Credentials Invalid !!";
	    }
	
	
	
	
	/////////////////// These End points are for DrugsCatelog //////////////////////////////
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/getalldrugs")
	public ResponseEntity<List<Drug>>getalldrugs()
	{
		return ResponseEntity.status(HttpStatus.OK).body(catalogueServiceImpl.getalldrugs());
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/getdrugbyid/{id}")
	public ResponseEntity<Drug>getdrugbyid(@PathVariable String id)
	{
		return ResponseEntity.status(HttpStatus.OK).body(catalogueServiceImpl.getdrugbyid(id));
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/getdrugbyname/{name}")
	public ResponseEntity<List<Drug>>getdrugbyname(@PathVariable String name)
	{
		return ResponseEntity.status(HttpStatus.OK).body(catalogueServiceImpl.getdrugbyname(name));
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/createdrug")
	public ResponseEntity<Drug>createdrug(@RequestBody Drug obj)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(catalogueServiceImpl.createdrug(obj));
	}
	
	@PutMapping("/updatedrug")
	public ResponseEntity<Drug>updatedrug(@RequestBody Drug obj)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(catalogueServiceImpl.updatedrug(obj));
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/deletedrug/{id}")
	public ResponseEntity<String>deletedrug(@PathVariable String id)
	{
		return ResponseEntity.status(HttpStatus.OK).body(catalogueServiceImpl.deletedrug(id));
	}
	
	//////////////////////////////  These endpoints are for Drug Stock ////////////////////////
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/getAllStock")
	public ResponseEntity<List<DrugsStock>>getAllStock()
	{
		return ResponseEntity.status(HttpStatus.OK).body(drugStockServiceImpl.getAllStock());
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/getByDrugName/{name}")
	ResponseEntity<List<DrugsStock>>getByDrugName(@PathVariable String name)
	{
		return ResponseEntity.status(HttpStatus.OK).body(drugStockServiceImpl.getByDrugName(name));
	}
	
	@GetMapping("/getByExpireDate/{date}")
	public ResponseEntity<List<DrugsStock>>getByExpireDate(@PathVariable LocalDate date)
	{
		return ResponseEntity.status(HttpStatus.OK).body(drugStockServiceImpl.getByExpireDate(date));
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/getByBatchNo/{name}")
	public ResponseEntity<List<DrugsStock>>getByBatchNo(@PathVariable String name)
	{
		return ResponseEntity.status(HttpStatus.OK).body(drugStockServiceImpl.getByBatchNo(name));
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/createStock")
	public ResponseEntity<DrugsStock>createStock(@RequestBody DrugsStock obj)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(drugStockServiceImpl.createStock(obj));
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/updateStock")
	public ResponseEntity<DrugsStock>updateStock(@RequestBody DrugsStock obj)
	{
		return ResponseEntity.status(HttpStatus.OK).body(drugStockServiceImpl.updateStock(obj));
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/deleteStock/{id}")
	public ResponseEntity<String>deleteStock(@PathVariable String id)
	{
		return ResponseEntity.status(HttpStatus.OK).body(drugStockServiceImpl.deleteStock(id));
	}
	
	
	/////////////////////////These end points are for verify the order /////////////////////
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/allOrders")
	ResponseEntity<List<Order>>allOrders()
	{
		return ResponseEntity.status(HttpStatus.OK).body(verifyOrderServiceImpl.allOrders());
	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/getOrderByDoctorId/{id}")
	public ResponseEntity<List<Order>>getOrderByDoctorId(@PathVariable String id)
	{
		return ResponseEntity.status(HttpStatus.OK).body(verifyOrderServiceImpl.getOrderByDoctorId(id));
	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/getOrderById/{id}")
	ResponseEntity<Order>getOrderById(@PathVariable String id)
	{
		return ResponseEntity.status(HttpStatus.OK).body(verifyOrderServiceImpl.getOrderById(id));
	}
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/verifyOrder")
	public ResponseEntity<Order>verifyOrder(@RequestBody Order obj)
	{
		return ResponseEntity.status(HttpStatus.OK).body(verifyOrderServiceImpl.verifyOrder(obj));
	}
	
	
	
	//////////////////////////////These endpoints are for pickupService /////////////////////
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/getAllPickups")
	public ResponseEntity<List<Pickup>>getAllPickups()
	{
		return ResponseEntity.status(HttpStatus.OK).body(pickupServiceImpl.getAllPickups());
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/getByDoctorId/{id}")
	public ResponseEntity<List<Pickup>>getByDoctorId(@PathVariable String id)
	{
		return ResponseEntity.status(HttpStatus.OK).body(pickupServiceImpl.getByDoctorId(id));
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/getByPickupId/{id}")
	public ResponseEntity<Pickup>getByPickupId(@PathVariable String id)
	{
		return ResponseEntity.status(HttpStatus.OK).body(pickupServiceImpl.getByPickupId(id));
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/getPickupPaymentDone")
	public ResponseEntity<List<Pickup>>getPickupPaymentDone()
	{
		return ResponseEntity.status(HttpStatus.OK).body(pickupServiceImpl.getPickupPaymentDone());
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/getPickupPaymentNotDone")
	public ResponseEntity<List<Pickup>>getPickupPaymentNotDone()
	{
		return ResponseEntity.status(HttpStatus.OK).body(pickupServiceImpl.getPickupPaymentNotDone());
	}

	
}
