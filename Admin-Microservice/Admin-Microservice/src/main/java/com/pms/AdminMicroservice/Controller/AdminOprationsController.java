package com.pms.AdminMicroservice.Controller;
import com.pms.AdminMicroservice.Model.PaymentDetails;
import java.time.LocalDate;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pms.AdminMicroservice.Config.JwtHelper;
import com.pms.AdminMicroservice.Config.JwtRequest;
import com.pms.AdminMicroservice.Config.JwtResponse;
import com.pms.AdminMicroservice.Impl.AdminProfileImgImpl;
import com.pms.AdminMicroservice.Impl.AdminProfileImpl;
import com.pms.AdminMicroservice.Impl.DrugCatalogueServiceImpl;
import com.pms.AdminMicroservice.Impl.DrugStockServiceImpl;
import com.pms.AdminMicroservice.Impl.PickupServiceImpl;
import com.pms.AdminMicroservice.Impl.VerifyOrderServiceImpl;
import com.pms.AdminMicroservice.Impl.contactUsImpl;
import com.pms.AdminMicroservice.Model.AdminDetails;
import com.pms.AdminMicroservice.Model.AdminProfileImg;
import com.pms.AdminMicroservice.Model.ContactUs;
import com.pms.AdminMicroservice.Model.Drug;
import com.pms.AdminMicroservice.Model.DrugsStock;
import com.pms.AdminMicroservice.Model.Order;
import com.pms.AdminMicroservice.Model.Pickup;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;


@RestController
@RequestMapping("/adminOprations")
public class AdminOprationsController {
	
	
	
	@Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;
    
    @Autowired
    private AdminProfileImgImpl adminProfileImgImpl;
    
    @Autowired
    private contactUsImpl ContactUsImpl;


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
	
	@PutMapping("/updateAdmin")
	public ResponseEntity<AdminDetails>updateAdmin(@RequestBody AdminDetails obj)
	{
		return ResponseEntity.status(HttpStatus.OK).body(adminProfileImpl.updateAdmin(obj));
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
   
	 ////////////////////////////This end point is for upload the img for admin profile ////////
	 
	 @PostMapping("/addAdminProfileImg")
	 public ResponseEntity<AdminProfileImg> addAdminProfileImg(@RequestParam("id") String id,@RequestParam("file") MultipartFile file)
	 {
		 return ResponseEntity.status(HttpStatus.CREATED).body(adminProfileImgImpl.addAdminProfileImg(id, file));
	 }
	 
	 @GetMapping("/getAdminProfileImg/{id}")
	 public ResponseEntity<AdminProfileImg> addAdminProfileImg(@PathVariable String id)
	 {
		 return ResponseEntity.status(HttpStatus.OK).body(adminProfileImgImpl.getAdminProfileImg(id));
	 }
	
	
	
	///////////////////////// These End points are for DrugsCatelog //////////////////////////////
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/getalldrugs")
	@CircuitBreaker(name="drugServiceBreaker", fallbackMethod = "viewAllDrugsFallback")
	public ResponseEntity<List<Drug>>getalldrugs()
	{
		return ResponseEntity.status(HttpStatus.OK).body(catalogueServiceImpl.getalldrugs());
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/getdrugbyid/{id}")
	@CircuitBreaker(name="drugServiceBreaker", fallbackMethod = "drugByIdFallback")
	public ResponseEntity<Drug>getdrugbyid(@PathVariable String id)
	{
		return ResponseEntity.status(HttpStatus.OK).body(catalogueServiceImpl.getdrugbyid(id));
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/getdrugbyname/{name}")
	@CircuitBreaker(name="drugServiceBreaker", fallbackMethod = "drugByNameFallback")
	public ResponseEntity<List<Drug>>getdrugbyname(@PathVariable String name)
	{
		return ResponseEntity.status(HttpStatus.OK).body(catalogueServiceImpl.getdrugbyname(name));
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/createdrug")
	@CircuitBreaker(name="drugServiceBreaker", fallbackMethod = "createdrugFallback")
	public ResponseEntity<Drug>createdrug(@RequestBody Drug obj)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(catalogueServiceImpl.createdrug(obj));
	}
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/updatedrug")
	@CircuitBreaker(name="drugServiceBreaker", fallbackMethod = "updatedrugFallback")
	public ResponseEntity<Drug>updatedrug(@RequestBody Drug obj)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(catalogueServiceImpl.updatedrug(obj));
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/deletedrug/{id}")
	@CircuitBreaker(name="drugServiceBreaker", fallbackMethod = "deletedrugFallback")
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
	public ResponseEntity<List<DrugsStock>>getByDrugName(@PathVariable String name)
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
	public ResponseEntity<List<Order>>allOrders()
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
	public ResponseEntity<Order>getOrderById(@PathVariable String id)
	{
		return ResponseEntity.status(HttpStatus.OK).body(verifyOrderServiceImpl.getOrderById(id));
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/verifyOrder")
	public ResponseEntity<Order>verifyOrder(@RequestBody Order obj)
	{
		return ResponseEntity.status(HttpStatus.OK).body(verifyOrderServiceImpl.verifyOrder(obj));
	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/deleteOrder/{id}")
	public ResponseEntity<String>deleteOrder(@PathVariable String id)
	{
		return ResponseEntity.status(HttpStatus.OK).body(verifyOrderServiceImpl.deleteOrder(id));
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
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/deletePickup/{id}")
    public ResponseEntity<String> deletePickup(@PathVariable String id)
	{
		return ResponseEntity.status(HttpStatus.OK).body(pickupServiceImpl.deletePickup(id));
	}
	
	
	//////////////This endpoints for payment details 
	@GetMapping("/getAllPaymentDetails")
	public ResponseEntity<List<PaymentDetails>>getAllPaymentDetails()
	{
		return ResponseEntity.status(HttpStatus.OK).body(pickupServiceImpl.getAllPaymentDetails());
	}
	@GetMapping("/getBypaymentID/{id}")
	public ResponseEntity<PaymentDetails>getBypaymentID(@PathVariable String id)
	{
		return ResponseEntity.status(HttpStatus.OK).body(pickupServiceImpl.getBypaymentID(id));
	}
	
	@GetMapping("/getPaymentDetailsByOrderid/{id}")
	public ResponseEntity<List<PaymentDetails>>getPaymentDetailsByOrderid(@PathVariable String id)
	{
		return ResponseEntity.status(HttpStatus.OK).body(pickupServiceImpl.getByOrderID(id));
	}
	
	////////////////////////This End point is for contact us ///////////////////////////////////////////
	@GetMapping("/getAllContactus")
	public ResponseEntity<List<ContactUs>> getAllContactUs()
	{
		return ResponseEntity.status(HttpStatus.OK).body(ContactUsImpl.getAllContactUs());
	}
	
	@PostMapping("/createContactUs")
	public ResponseEntity<ContactUs> createAContactUs(@RequestBody ContactUs obj)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(ContactUsImpl.createAContactUs(obj));
		
	}
	
	@DeleteMapping("/deleteContactus/{id}")
	public ResponseEntity<String>deleteContactus(@PathVariable long id)
	{
		return ResponseEntity.status(HttpStatus.OK).body(ContactUsImpl.deleteContactus(id));
		
	}
	
	
	
	
	
	////////////////////////////Fall back methods ////////////////////////////////////////////
	//////////////For drug service 
	 public ResponseEntity<List<Drug>>viewAllDrugsFallback(Throwable throwable)
	  	{
		    Drug obj=new Drug("1234","Drug service not avilable",0,"","");
	  		List<Drug> allDrugsList=new ArrayList<>();
	  		allDrugsList.add(obj);
	  		
	  		return ResponseEntity.status(HttpStatus.OK).body(allDrugsList);
	  	}
	 public ResponseEntity<List<Drug>> drugByNameFallback(String name,Throwable throwable)
	  	{
		  Drug obj=new Drug("1234","Drug service not avilable",0,"","");
	  		List<Drug> allDrugsList=new ArrayList<>();
	  		allDrugsList.add(obj);
	  		
	  		return ResponseEntity.status(HttpStatus.OK).body(allDrugsList);
	  	}
	 
	public ResponseEntity<Drug> drugByIdFallback(String Id,Throwable throwable)
	  	{
			Drug obj=new Drug("","Drug service not avilable",0,"","");
	  		return ResponseEntity.status(HttpStatus.OK).body(obj);
	  	}
	
	
	
	public ResponseEntity<Drug>createdrugFallback(Drug obj,Throwable throwable)
	{
		Drug fallBackObject=new Drug();
		return ResponseEntity.status(HttpStatus.OK).body(fallBackObject);
	}
	
	
	public ResponseEntity<Drug>updatedrugFallback( Drug obj,Throwable throwable)
	{
		Drug fallBackObject=new Drug();
		return ResponseEntity.status(HttpStatus.OK).body(fallBackObject);
	}
	
	
	public ResponseEntity<String>deletedrugFallback(String id,Throwable throwable)
	{
		return ResponseEntity.status(HttpStatus.OK).body("Drug-Inventory is down!");
	}

	
}
