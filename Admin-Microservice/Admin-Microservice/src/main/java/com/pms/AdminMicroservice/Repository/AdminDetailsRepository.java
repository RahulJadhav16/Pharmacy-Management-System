package com.pms.AdminMicroservice.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pms.AdminMicroservice.Model.AdminDetails;

@Repository
public interface AdminDetailsRepository extends MongoRepository<AdminDetails, String>{

}
