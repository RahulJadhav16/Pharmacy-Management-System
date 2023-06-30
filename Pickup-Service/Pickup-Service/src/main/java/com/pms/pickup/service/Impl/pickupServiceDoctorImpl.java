package com.pms.pickup.service.Impl;
import com.pms.pickup.service.Config.sendEmail;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.pms.pickup.service.Model.PaymentDetails;
import com.pms.pickup.service.Model.Pickup;
import com.pms.pickup.service.Repository.paymentDetailsRepo;
import com.pms.pickup.service.Repository.pickupRepository;
import com.pms.pickup.service.Service.pickupServiceDoctor;

@Service
public class pickupServiceDoctorImpl implements pickupServiceDoctor{
    
	@Autowired
	private pickupRepository repo;
	
	@Autowired
	private paymentDetailsRepo paymentRepo;
	
	@Autowired
	private sendEmail email;
	
	@Override
	public Pickup makePayment(Pickup obj) {
		
		
		return repo.save(obj);
	}

	@Override
	public List<Pickup> getAllPickups(String doctorId) {
		
		List<Pickup> allPickupList=repo.findAll();
		List<Pickup> pickupBydoctor=new ArrayList<>();
		 String pickupid;
		for(Pickup e:allPickupList)
		{
			String doctorIdInsideList=e.getOrders().get(0).getDoctorId();
			if(doctorIdInsideList.equals(doctorId))
			{
				pickupBydoctor.add(e);
				
			}
		}
		
		//If money paid one by one then 
		
		double money =0;		
		for(Pickup e:pickupBydoctor)
		{
			try {
			List<PaymentDetails> paymentDetails=paymentRepo.findByOrderId(e.getPickupId());

			for(PaymentDetails p:paymentDetails)
			{
				
				money= money+p.getAmountPaid();
				e.setMoneyPaid(money);
				
			}
			
			
			}
			catch (Exception exce) {
				
				System.out.println(exce);
				
				
				
			}
		}
		
		
		return pickupBydoctor;
	}
    
	
	//This method will get auto called by make payment method
	@Override
	public PaymentDetails addPaymentDetails(PaymentDetails obj) {
		// TODO Auto-generated method stub
		LocalDate dateToday=LocalDate.now();
		obj.setPaymentDate(dateToday);
		//Sending mail to user 
		PaymentDetails paymentInfo=paymentRepo.save(obj);
		
		
		String emailTitle="Payment Confirmation and Delivery Update - Thank You for Choosing MedWise";
		
		String emailBody="We hope this email finds you in good health.We are writing to inform you that we have received your payment for the recent purchase you made with MedWise. \r\n"
				+ "\r\n"
				+ "We are pleased to inform you that our dedicated team has processed your payment successfully. Our delivery partners have been promptly notified, and they are fully prepared to expedite the shipping process. Within the next 24 hours, you can expect the arrival of your order at the provided shipping address.\r\n"
				+ "Payment details:\r\n"
				+ "Payment id:"+paymentInfo.getPaymentId()+"\r\n"
				+ "Order id:"+obj.getOrderId()+"\r\n"
				+ "Amount Paid:"+obj.getAmountPaid()+"\r\n"
				+ "Payment Date:"+obj.getPaymentDate()+"\r\n"
				+ "\r\n"
				+ "Thank you for your trust and continued support.\r\n"
				+ "Warm regards,\r\n"
				+ "MedWise Customer Support ";
		
		
		
		email.someMethod(obj.getDoctorMail(),emailTitle,emailBody);
		return paymentInfo;
	}

	@Override
	public List<PaymentDetails> getAllPaymentDetails() {
		// TODO Auto-generated method stub
		return paymentRepo.findAll();
	}

	@Override
	public PaymentDetails getBypaymentID(String id) {
		// TODO Auto-generated method stub
		return paymentRepo.findById(id).orElse(null);
	}

}
