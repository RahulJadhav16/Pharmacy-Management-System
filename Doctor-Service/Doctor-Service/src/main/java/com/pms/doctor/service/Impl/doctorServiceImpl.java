package com.pms.doctor.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pms.doctor.service.Models.Drug;
import com.pms.doctor.service.Models.Order;
import com.pms.doctor.service.Repository.doctorRepository;
import com.pms.doctor.service.Service.doctorService;

public class doctorServiceImpl implements doctorService {
	
	@Autowired
	private doctorRepository repo;
	

	@Override
	public List<Drug> viewAllDrugs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Drug drugByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Drug drugById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> viewAllOrders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order addOrder(Order orderObj) {
		// TODO Auto-generated method stub
		return null;
	}

}
