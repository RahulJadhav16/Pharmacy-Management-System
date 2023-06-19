package com.pms.doctor.service.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pms.doctor.service.Models.DoctorPersonalDetails;

@Repository
public interface DoctorPersonalDetailsRepository extends MongoRepository<DoctorPersonalDetails, String>{

	DoctorPersonalDetails findByEmail(String email);
}
