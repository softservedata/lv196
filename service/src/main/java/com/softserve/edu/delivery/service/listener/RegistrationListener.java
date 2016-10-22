package com.softserve.edu.delivery.service.listener;

import com.softserve.edu.delivery.service.event.OnRegistrationCompleteEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
public class RegistrationListener{

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Environment env;

    @Autowired
    TemplateEngine templateEngine;

    @EventListener
    public void confirmRegistration(OnRegistrationCompleteEvent event) {
        sendEmail(event);
    }

    private void sendEmail(OnRegistrationCompleteEvent event) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(env.getProperty("spring.mail.username"));
            messageHelper.setTo(event.getEmail());
            messageHelper.setSubject("Email confirmation");

            String confirmationUrl = event.getUrl() + "?token=" + event.getToken();
            String text = buildHtmlTemplate(confirmationUrl);

            messageHelper.setText(text, true);
        };
        mailSender.send(messagePreparator);
    }

    private String buildHtmlTemplate(String confirmUrl) {
        Context context = new Context();
        context.setVariable("url", confirmUrl);
        return templateEngine.process("email", context);
    }
}