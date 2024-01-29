package com.pms.doctor.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.doctor.service.Models.MailVerification;
import com.pms.doctor.service.Repository.EmailVerification;
import com.pms.doctor.service.Service.doctorEmailVerificationService;


@Service
public class doctorEmailVerificationServiceImpl implements doctorEmailVerificationService{
	
	@Autowired
	private EmailVerification emailVerificationrepo;

	@Override
	public MailVerification getDoctorEmailVerification(String id) {
		
		MailVerification object=new MailVerification();
		List<MailVerification> mailverificationObjList=emailVerificationrepo.findAll();
		
		for(MailVerification e:mailverificationObjList)
		{
			if(e.getId().equals(id))
			{
				object=e;
				break;
			}
		}
		
		
		
		return object;
	}

	@Override
	public MailVerification setDoctorEmailVerification(MailVerification obj) {
		
		return emailVerificationrepo.save(obj);
	}

}
