package com.pms.AdminMicroservice.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pms.AdminMicroservice.Model.PaymentDetails;
import com.pms.AdminMicroservice.Model.Pickup;
import com.pms.AdminMicroservice.Service.PickupService;

@Service
public class PickupServiceImpl implements PickupService{
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public List<Pickup> getAllPickups() {
		String url="http://PICKUP-SERVICE/pickupAdmin/getAllPickups";
		List<Pickup> response =restTemplate.getForObject(url, ArrayList.class);
		return response;
	}

	@Override
	public List<Pickup> getByDoctorId(String doctorId) {
		String url="http://PICKUP-SERVICE/pickupAdmin/getByDoctorId/"+doctorId;
		List<Pickup> response =restTemplate.getForObject(url, ArrayList.class);
		return response;
	}

	@Override
	public Pickup getByPickupId(String pickupId) {
		// TODO Auto-generated method stub
		String url="http://PICKUP-SERVICE/pickupAdmin/getByPickupId/"+pickupId;
		Pickup response =restTemplate.getForObject(url, Pickup.class);
		return response;
	}

	@Override
	public List<Pickup> getPickupPaymentDone() {
		String url="http://PICKUP-SERVICE/pickupAdmin/getPickupPaymentDone";
		List<Pickup> response =restTemplate.getForObject(url, ArrayList.class);
		return response;
	}

	@Override
	public List<Pickup> getPickupPaymentNotDone() {
		// TODO Auto-generated method stub
		String url="http://PICKUP-SERVICE/pickupAdmin/getPickupPaymentNotDone";
		List<Pickup> response =restTemplate.getForObject(url, ArrayList.class);
		return response;
	}

	@Override
	public List<PaymentDetails> getAllPaymentDetails() {
		// TODO Auto-generated method stub
		String url="http://PICKUP-SERVICE/pickupAdmin/getAllPaymentDetails";
		List<PaymentDetails> response =restTemplate.getForObject(url, ArrayList.class);
		return response;
	}

	@Override
	public PaymentDetails getBypaymentID(String id) {
		// TODO Auto-generated method stub
		String url="http://PICKUP-SERVICE/pickupAdmin/getBypaymentID/"+id;
		PaymentDetails response =restTemplate.getForObject(url, PaymentDetails.class);
		return response;
	}

	@Override
	public List<PaymentDetails> getByOrderID(String id) {
		// TODO Auto-generated method stub
		String url="http://PICKUP-SERVICE/pickupAdmin/getByOrderID/"+id;
		List<PaymentDetails>response =restTemplate.getForObject(url, ArrayList.class);
		return response;
	}

	@Override
	public String deletePickup(String id) {
		// TODO Auto-generated method stub
		// Create a RestTemplate instance
		RestTemplate restTemplate = new RestTemplate();

		String url = "http://PICKUP-SERVICE/pickupAdmin/deletePickup/" + id;

		// Send the DELETE request
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, null, String.class);

		// Get the response body
		String responseBody = response.getBody();

		return responseBody;
	}
	
	

}
