package com.pms.doctor.service.Controller;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Base64Utils;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.pms.doctor.service.Config.JwtHelper;
import com.pms.doctor.service.Config.JwtRequest;
import com.pms.doctor.service.Config.JwtResponse;
import com.pms.doctor.service.Exception.UserNotFoundByIDException;
import com.pms.doctor.service.Impl.DoctorPersonalDetailsImpl;
import com.pms.doctor.service.Impl.doctorDetailsImpl;
import com.pms.doctor.service.Impl.doctorProfileImgImpl;
import com.pms.doctor.service.Impl.doctorServiceImpl;
import com.pms.doctor.service.Models.Doctor;
import com.pms.doctor.service.Models.DoctorPersonalDetails;
import com.pms.doctor.service.Models.DoctorProfileImg;
import com.pms.doctor.service.Models.Drug;

import com.pms.doctor.service.Models.Order;
import com.pms.doctor.service.Models.Pickup;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/doctor")
public class doctorController {
	
	@Autowired
	private doctorProfileImgImpl doctorProfileImgImpl;
	
	
	
	@Autowired
	private doctorDetailsImpl doctorDetailsService;
	
	@Autowired
	private doctorServiceImpl doctorService;

	@Autowired
	private DoctorPersonalDetailsImpl doctorPersonalDetailsImpl;
	
	@Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;


    @Autowired
    private JwtHelper helper;

    private Logger logger = LoggerFactory.getLogger(doctorController.class);
	
    
     //To view all the drugs
  	@PreAuthorize("hasRole('DOCTOR')")
  	@GetMapping("/viewAllDrugs")
  	@CircuitBreaker(name="drugServiceBreaker", fallbackMethod = "viewAllDrugsFallback")
  	public ResponseEntity<List<Drug>>viewAllDrugs()
  	{
  		List<Drug> allDrugsList=doctorService.viewAllDrugs();
  		
  		return ResponseEntity.status(HttpStatus.OK).body(allDrugsList);
  	}
  	
  	//To get the drug by  name
  	@PreAuthorize("hasRole('DOCTOR')")
  	@GetMapping("/drugByName/{name}")
  	@CircuitBreaker(name="drugServiceBreaker", fallbackMethod = "drugByNameFallback")
  	public ResponseEntity<List<Drug>> drugByName(@PathVariable String name)
  	{
  		return ResponseEntity.status(HttpStatus.OK).body(doctorService.drugByName(name));
  	}
  	
  	//To get the drug by  Id
  	@PreAuthorize("hasRole('DOCTOR')")
  	@GetMapping("/drugById/{Id}")
  	@CircuitBreaker(name="drugServiceBreaker", fallbackMethod = "drugByIdFallback")
  	public ResponseEntity<Drug> drugById(@PathVariable String Id)
  	{
  		return ResponseEntity.status(HttpStatus.OK).body(doctorService.drugById(Id));
  	}
  	
  	/////////////////////////////// Orders Section /////////////////////////////////
  	//To view all Orders
  	@PreAuthorize("hasRole('DOCTOR')")
  	@GetMapping("/viewAllOrders/{doctorId}")
  	@CircuitBreaker(name="orderServiceBreaker", fallbackMethod = "viewAllOrdersFallback")
  	public ResponseEntity<List<Order>>viewAllOrders(@PathVariable String doctorId)
  	{
  		return ResponseEntity.status(HttpStatus.OK).body(doctorService.viewAllOrders(doctorId));
  	}
  	
  	//To place order
  	@PreAuthorize("hasRole('DOCTOR')")
  	@PostMapping("/addOrder")
  	@CircuitBreaker(name="orderServiceBreaker", fallbackMethod = "addOrderFallback")
  	public ResponseEntity<Order> addOrder(@RequestBody Order orderObj)
  	{
  		return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.addOrder(orderObj));
  	}
  	
  	//Delete order
  	@PreAuthorize("hasRole('DOCTOR')")
  	@DeleteMapping("/deleteOrder/{id}") 
  	@CircuitBreaker(name="orderServiceBreaker", fallbackMethod = "deleteOrderFallback")
  	public ResponseEntity<String> deleteOrder(@PathVariable String id)
  	{
  		return ResponseEntity.status(HttpStatus.OK).body(doctorService.deleteOrder(id));
  	}
  	
  	//////////////////////////// pickup section ///////////////////////////////////
  	
  	//viewAllPickups
  	@PreAuthorize("hasRole('DOCTOR')")
  	@GetMapping("/viewAllPickups/{id}")
  	@CircuitBreaker(name="pickupServiceBreaker", fallbackMethod = "viewAllPickupsFallback")
  	public ResponseEntity<List<Pickup>>viewAllPickups(@PathVariable String id)
  	{
  		return ResponseEntity.status(HttpStatus.OK).body(doctorService.viewAllPickups(id));
  	}
  	
  	//Make payment 
  	@PreAuthorize("hasRole('DOCTOR')")
  	@PutMapping("/makePayment")
  	@CircuitBreaker(name="pickupServiceBreaker", fallbackMethod = "makePaymentFallback")
  	public ResponseEntity<Pickup>makePayment(@RequestBody Pickup obj)
  	{
  		return ResponseEntity.status(HttpStatus.OK).body(doctorService.makePayment(obj));
  	}
    
    
	@GetMapping("/getDetails/{doctorId}")
	public ResponseEntity<DoctorPersonalDetails> getDetails(@PathVariable String doctorId)
	{
		return ResponseEntity.status(HttpStatus.OK).body(doctorPersonalDetailsImpl.getDetails(doctorId));
	}
	
	
	//////////////////////////////////  For login and Registration ///////////////////////////////////////////////
	
    //////////////////////////For profile img Upload ///////////////////
	@PostMapping("/uploadProfileImg")
	public ResponseEntity<DoctorProfileImg> uploadImg(@RequestParam("id") String id,@RequestParam("file") MultipartFile file)
	{

	return ResponseEntity.status(HttpStatus.CREATED).body(doctorProfileImgImpl.uploadImg(id, file));

	}
	
	
	@GetMapping("/getProfileImg/{id}")
	public ResponseEntity<DoctorProfileImg>getProfileImg(@PathVariable String id)
	{
		return ResponseEntity.status(HttpStatus.OK).body(doctorProfileImgImpl.getProfileImg(id));
	}

	
	
	@GetMapping("/getDoctorId/{id}")
	public String getDoctoridBymail(@PathVariable String id)
	{
		return doctorDetailsService.getDoctoridBymail(id);
	}
	
	
	@PostMapping("/create")
	public ResponseEntity<Doctor> addDetails(@RequestBody Doctor doctorobj)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(doctorDetailsService.addDetails(doctorobj));
	}
	
	
	@PutMapping("/update")
	public ResponseEntity<Doctor> updateDetails(@RequestBody Doctor doctorobj)
	{
		return ResponseEntity.status(HttpStatus.OK).body(doctorDetailsService.updateDetails(doctorobj));
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
	 
	 ////////////////////////////Fallback Methods ////////////////////////////////////////////
	 //for drug inventory
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
		
		//////////////////////////// fall backs for order 
		
		public ResponseEntity<List<Order>>viewAllOrdersFallback(String doctorId,Throwable throwable)
	  	{
			List<Order> allordersList=new ArrayList<>();
	  		return ResponseEntity.status(HttpStatus.OK).body(allordersList);
	  	}
		
	
	  	public ResponseEntity<Order> addOrderFallback(Order orderObj,Throwable throwable)
	  	{
	  		Order object=new Order();
	  		return ResponseEntity.status(HttpStatus.OK).body(object);
	  	}
	  	
	  	
	  	/////for delete order 
	  	public ResponseEntity<String> deleteOrderFallback(String id,Throwable throwable)
	  	{
	  		return ResponseEntity.status(HttpStatus.OK).body("Service Unavilable");
	  	}
	  	
	 ////////////////////////////////////////// fallback for pickup
	  	
	  	
	  	public ResponseEntity<List<Pickup>>viewAllPickupsFallback(String id,Throwable throwable)
	  	{
	  		List<Pickup> allPickup=new ArrayList<>();
	  		return ResponseEntity.status(HttpStatus.OK).body(allPickup);
	  	}
	  	
	  	
	  	public ResponseEntity<Pickup>makePaymentFallback(Pickup obj,Throwable throwable)
	  	{
	  		Pickup object=new Pickup();
	  		
	  		return ResponseEntity.status(HttpStatus.OK).body(object);
	  	}
	  	
	  	
	  	






    
     









}
