package com.pms.pickup.service.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pms.pickup.service.Model.Pickup;

@Repository
public interface pickupRepository extends MongoRepository<Pickup, String> {

}
