package com.pms.pickup.service.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pms.pickup.service.Impl.PaymentService;
import com.pms.pickup.service.Model.PaymentRequest;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/payment")
public class PaymentController {
	
	@Autowired
	PaymentService service;

	@PostMapping
	public ResponseEntity<String> completePayment(@RequestBody PaymentRequest request) throws StripeException {
		String chargeId= service.charge(request);
		return chargeId!=null? new ResponseEntity<String>(chargeId,HttpStatus.OK):
			new ResponseEntity<String>("Please check the credit card details entered",HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public String handleError(StripeException ex) {
		return ex.getMessage();
	}
}
