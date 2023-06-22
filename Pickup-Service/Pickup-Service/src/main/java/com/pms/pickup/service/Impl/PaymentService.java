package com.pms.pickup.service.Impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pms.pickup.service.Model.PaymentRequest;
import com.pms.pickup.service.Repository.pickupRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;


import jakarta.annotation.PostConstruct;

@Service
public class PaymentService {
	@Autowired
	private pickupRepository repo;

	@Value("${STRIPE_SECRET_KEY}")
	private String secretKey;
	
    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }
	public String charge(PaymentRequest chargeRequest) throws StripeException {
		 Map<String, Object> chargeParams = new HashMap<>();
	     chargeParams.put("amount", chargeRequest.getAmount());
	     chargeParams.put("currency", PaymentRequest.Currency.INR);
	     chargeParams.put("source", chargeRequest.getToken().getId());
	     
		Charge charge = Charge.create(chargeParams);
		
	     
		return charge.getId();
	}
}
