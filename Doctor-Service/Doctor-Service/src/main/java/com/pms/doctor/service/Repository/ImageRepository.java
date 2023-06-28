package com.pms.doctor.service.Repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pms.doctor.service.Models.DoctorProfileImg;

@Repository
public interface ImageRepository extends MongoRepository<DoctorProfileImg, String> {
	
	
}
