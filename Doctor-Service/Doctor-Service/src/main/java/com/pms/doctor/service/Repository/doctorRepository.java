package com.pms.doctor.service.Repository;

import javax.print.Doc;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pms.doctor.service.Models.Doctor;

@Repository
public interface doctorRepository extends MongoRepository<Doctor,String>{
	
	Doctor findByEmail(String email);

}
