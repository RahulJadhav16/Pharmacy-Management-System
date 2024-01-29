package com.pms.doctor.service.Impl;

import java.util.Date;
import java.util.List;
import java.util.Random;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.pms.doctor.service.Email.sendEmail;
import com.pms.doctor.service.Models.DoctorPersonalDetails;
import com.pms.doctor.service.Models.Otp;
import com.pms.doctor.service.Models.OtpVerifyModel;
import com.pms.doctor.service.Repository.DoctorPersonalDetailsRepository;
import com.pms.doctor.service.Repository.OPTRepo;
import com.pms.doctor.service.Service.OptVerify;


@Service
public class VerifyOptImpl implements OptVerify{
	@Autowired
	private sendEmail email;
	
	@Autowired
	private OPTRepo otpRepo;
	
	@Autowired
	private DoctorPersonalDetailsRepository DoctorRepo;
	
	private Date mailSentDate;
	
	
	

	@Override
	public String VerifyOpt(OtpVerifyModel obj) {
		
		boolean otpVerificationStataus=false;
		
		List<Otp> allObj=otpRepo.findAll();
		
		for(Otp e:allObj)
		{
			if(e.getId().equals(obj.getMailId()))
			{
				if((e.getOTP().equals(obj.getOtp() )))
				{
					
						
						String dateString =e.getOtpSentDate().toString();
						
						try {
				            // Parse the provided date string
				            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
				            Date providedDate = dateFormat.parse(dateString);

				            // Print the provided date
				            System.out.println("Provided Date: " + providedDate);

				            // Add one day to the provided date
				            Calendar calendar = Calendar.getInstance();
				            calendar.setTime(providedDate);
				            calendar.add(Calendar.DAY_OF_MONTH, 1);

				            // Get the next day's date
				            Date nextDayDate = calendar.getTime();

				       
				            System.out.println("Next Day's Date: " + nextDayDate);
				            
				            if(obj.getDateOfVerification().before(nextDayDate))
				            {
				            	otpVerificationStataus=true;
				            	
				            }
				            
				            
				            
				            
				        } catch (ParseException c) {
				           System.out.println(c);
				        }

						
						
						
						
						
						
							
					
					
					
					
				}
				
				
				
				
				
			}
		}
		
		
		
		
		if(otpVerificationStataus==true)
		{
			otpVerificationStataus=false;
			
			List<DoctorPersonalDetails> getallList=DoctorRepo.findAll();
			
			for(DoctorPersonalDetails e:getallList)
			{
				if(e.getEmail().equals(obj.getMailId()))
				{
					
					
					break;
				}
			}
			
			
			
			return "Otp verification done!!!!!!";
		}
		
		else {
			
			return "Otp Verification failed !!";
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
	}
	

	
	

	

	@Override
	public Otp SendOpt(String Email) {
		
		
		try {
			// Length of the OTP
	        int otpLength = 4;

	        // Characters allowed in the OTP
	        String allowedChars = "0123456789";

	        // Create a random object
	        Random random = new Random();

	        // StringBuilder to store the OTP
	        StringBuilder otp = new StringBuilder(otpLength);

	        // Generate each digit of the OTP
	        for (int i = 0; i < otpLength; i++) {
	            int randomIndex = random.nextInt(allowedChars.length());
	            char digit = allowedChars.charAt(randomIndex);
	            otp.append(digit);
	        }
	        
	        
	        
	        Date currentDate = new Date();
	        mailSentDate=currentDate;
	        
	        //Here I am sending mail to user about OPT
	        
	        String emailTitle="Verify Your Email from MedWise";
	        
	        String emailBody="Thank you for choosing MedWise to prioritize your health. To complete the registration process and unlock the full potential of your MedWise account, we need to verify your email address.\r\n"
	        		+ "\r\n"
	        		+ "**Your Verification Code:"+otp.toString()+"**\r\n"
	        		+ "\r\n"
	        		+ "Please use the following one-time verification code within the next 24 hours to confirm your email address. This code is crucial to ensuring the security and integrity of your MedWise account.\r\n"
	        		+ "\r\n"
	        		+ "**Note:** If you did not sign up for MedWise, please disregard this email. Your account will not be activated until you verify your email.\r\n"
	        		+ "\r\n"
	        		+ "Thank you for being a part of MedWise. We look forward to assisting you on your journey to better health.\r\n"
	        		+ "\r\n"
	        		+ "Best Regards,\r\n"
	        		+ "The MedWise Team";
	        
	        
	        
	        email.someMethod(Email,emailTitle,emailBody);
	        
	        Otp saveInstanceotp=new Otp(Email,otp.toString(),currentDate);
	        otpRepo.save(saveInstanceotp);
	        
	        
	        return saveInstanceotp;
			
		} 
		
		
		catch (Exception e) {
			
			
			 return null;
			
		}
		
		
        
        
        
        
        
        
        
        
        
        
        
		
		
		
	}

}
