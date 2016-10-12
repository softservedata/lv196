package com.softserve.edu.delivery.service.listener;

import com.softserve.edu.delivery.service.event.OnRegistrationCompleteEvent;
import com.softserve.edu.delivery.service.sender.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class RegistrationListener{

    @Autowired
    private EmailSender emailSender;

    @Autowired
    private Environment env;

    @EventListener
    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        String receiverEmail = event.getEmail();
        String token = event.getToken();

        String subject = env.getProperty("mail.registration.subject");
        String message = env.getProperty("mail.registration.message") + ": " + event.getUrl() + "?token=" + token;

        emailSender.send(receiverEmail, subject, message);
    }
}