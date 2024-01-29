package com.pms.doctor.service.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pms.doctor.service.Models.MailVerification;
import com.pms.doctor.service.Models.OtpVerifyModel;

@Repository
public interface EmailVerification extends MongoRepository<MailVerification, String>{

}
