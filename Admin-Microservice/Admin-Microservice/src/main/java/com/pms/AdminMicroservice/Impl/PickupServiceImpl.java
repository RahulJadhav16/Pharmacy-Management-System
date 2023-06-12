package com.pms.AdminMicroservice.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pms.AdminMicroservice.Model.Pickup;
import com.pms.AdminMicroservice.Service.PickupService;

@Service
public class PickupServiceImpl implements PickupService{
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public List<Pickup> getAll() {
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
	
	

}
