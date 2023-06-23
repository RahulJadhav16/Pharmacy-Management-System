package com.pms.pickup.service.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mongodb.client.MongoDatabase;
import com.pms.pickup.service.Model.PaymentDetails;

@Repository
public interface paymentDetailsRepo extends MongoRepository<PaymentDetails,String> {

}
