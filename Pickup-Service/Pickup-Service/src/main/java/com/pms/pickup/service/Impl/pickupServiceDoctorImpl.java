package com.pms.pickup.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.pickup.service.Model.Pickup;
import com.pms.pickup.service.Repository.pickupRepository;
import com.pms.pickup.service.Service.pickupServiceDoctor;

@Service
public class pickupServiceDoctorImpl implements pickupServiceDoctor{
    
	@Autowired
	private pickupRepository repo;
	
	@Override
	public Pickup makePayment(Pickup obj) {
		
		
		return repo.save(obj);
	}

	@Override
	public List<Pickup> getAllPickups(String doctorId) {
		
		List<Pickup> allPickupList=repo.findAll();
		List<Pickup> pickupBydoctor=new ArrayList<>();
		 String pickupid;
		for(Pickup e:allPickupList)
		{
			String doctorIdInsideList=e.getOrders().get(0).getDoctorId();
			if(doctorIdInsideList.equals(doctorId))
			{
				pickupBydoctor.add(e);
				
			}
		}
		
		return pickupBydoctor;
	}

}
