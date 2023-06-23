package com.pms.pickup.service.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



import jakarta.mail.MessagingException;

//this class will send the mail
@Component
public class sendEmail {
	private final EmailSender emailSender;
	@Autowired
    public sendEmail(EmailSender emailSender) {
        this.emailSender = emailSender;
    }
	
	public void someMethod(String sendto,String mailBody,String mailTitle) {
        

        try {
            emailSender.sendEmail(sendto, mailBody, mailTitle);
        } catch (MessagingException e) {
            
        }
    }


}
