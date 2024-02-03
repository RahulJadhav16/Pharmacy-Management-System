package com.pms.doctor.service.Impl;

import com.pms.doctor.service.Email.sendEmail;
import com.pms.doctor.service.Exception.EmailIdAlreadyExistsException;
import com.pms.doctor.service.Models.Doctor;
import com.pms.doctor.service.Repository.doctorRepository;
import com.pms.doctor.service.Service.DoctorForgetPasswordService;

import java.util.List;

import org.passay.CharacterRule;  
import org.passay.EnglishCharacterData;  
import org.passay.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DoctorForgetPasswordImpl implements DoctorForgetPasswordService{
	
	@Autowired 
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private doctorRepository doctorRepo;
	
	@Autowired
	private sendEmail emailSend;

	@Override
	public String genratePassword(String email) {
		
		boolean Emailcheck=false;	
		
		Doctor doctorobj=new Doctor();
		
		String userName="";
		List<Doctor> doctorList=doctorRepo.findAll();
		for(Doctor e:doctorList)
		{
			if (e.getEmail().toLowerCase().equals(email.toLowerCase()))
			{
				Emailcheck=true;
				userName=e.getName();
				doctorobj=e;
				break;
			}
		}
		
		if(Emailcheck)
		{
			  // create character rule for lower case  
	        CharacterRule LCR = new CharacterRule(EnglishCharacterData.LowerCase);  
	        // set number of lower case characters  
	        LCR.setNumberOfCharacters(2);  
	  
	        // create character rule for upper case  
	        CharacterRule UCR = new CharacterRule(EnglishCharacterData.UpperCase);  
	        // set number of upper case characters  
	        UCR.setNumberOfCharacters(2);  
	  
	        // create character rule for digit  
	        CharacterRule DR = new CharacterRule(EnglishCharacterData.Digit);  
	        // set number of digits  
	        DR.setNumberOfCharacters(2);  
	  
	       
	          
	        // create instance of the PasswordGenerator class   
	        PasswordGenerator passGen = new PasswordGenerator();  
	          
	        // call generatePassword() method of PasswordGenerator class to get Passay generated password  
	        String password = passGen.generatePassword(6, LCR, UCR, DR);  
	          
	        
	        //Here I am sending mail to user with updated password
	        
	        String emailTitle="Your Password Reset Request - New Password Inside";
	        
	        String emailBody="Dear "+userName+" ,\r\n"
	        		+ "We hope this email finds you well.\r\n"
	        		+ "\r\n"
	        		+ "We would like to inform you that we have received your request to reset your password for your MedWise account. In response to your request, we have generated a new password for your account. Please find your new login credentials below:\r\n"
	        		+ "\r\n"
	        		+ "Username:"+email+"\r\n"
	        		+ "New Password:"+password+"\r\n"
	        		+ "For security reasons, we recommend that you log in using this new password as soon as possible and change it to a password of your choice. To reset your password, please follow these steps:\r\n"
	        		+ "\r\n"
	        		+ "Visit our login page at http://localhost:3000/doctor.\r\n"
	        		+ "Enter your username and the new password provided above.\r\n"
	        		+ "Once logged in, navigate to your account settings.\r\n"
	        		+ "Choose the option to change your password.\r\n"
	        		+ "Follow the on-screen instructions to set a new, secure password.\r\n"
	        		+ "\r\n"
	        		+ "\r\n"
	        		+ "Thank you for your prompt attention to this matter.\r\n"
	        		+ "\r\n"
	        		+ "Best regards,\r\n"
	        		+ "MedWise Team";
	        
	      ////////Hashing new password
			doctorobj.setPassword(passwordEncoder.encode(password));
			doctorRepo.save(doctorobj);
	        
	        
	        
	        
	        
	        
	        emailSend.someMethod(email,emailTitle,emailBody);
	        
	        return password;  
			
		}
		else {
			return "user not registered";
		}
		
	
	
		
		
		
		
		
		
		

	}

}
