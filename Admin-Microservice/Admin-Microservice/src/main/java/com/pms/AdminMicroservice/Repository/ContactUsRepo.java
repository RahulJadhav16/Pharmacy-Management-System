package com.pms.AdminMicroservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pms.AdminMicroservice.Model.ContactUs;

@Repository
public interface ContactUsRepo extends JpaRepository<ContactUs, Long> {
	

}
