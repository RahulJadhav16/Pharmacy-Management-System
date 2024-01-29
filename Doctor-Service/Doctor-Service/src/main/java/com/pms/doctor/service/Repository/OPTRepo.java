package com.pms.doctor.service.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pms.doctor.service.Models.Otp;

@Repository
public interface OPTRepo extends MongoRepository<Otp, String>{
	
	
	

}
