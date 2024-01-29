package com.pms.doctor.service.Config;


import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pms.doctor.service.Impl.EmailSenderService;

//This is email sending config 
@Component
public class EmailSender {

    private final EmailSenderService senderService;

    @Autowired
    public EmailSender(EmailSenderService senderService) {
        this.senderService = senderService;
    }

    public void sendEmail(String sendto, String mailBody, String mailTitle) throws MessagingException {
        senderService.sendSimpleEmail(sendto, mailBody, mailTitle);
    }
}
