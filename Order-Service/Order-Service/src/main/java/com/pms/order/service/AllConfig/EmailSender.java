package com.pms.order.service.AllConfig;

import com.pms.order.service.Impl.EmailSenderService;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
