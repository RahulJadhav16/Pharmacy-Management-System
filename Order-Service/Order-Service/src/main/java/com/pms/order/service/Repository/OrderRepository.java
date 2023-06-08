package com.pms.order.service.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pms.order.service.Model.order;

@Repository
public interface OrderRepository extends MongoRepository<order, String>{
	
	List<order> findByDoctorId(String doctorId);
	

}
