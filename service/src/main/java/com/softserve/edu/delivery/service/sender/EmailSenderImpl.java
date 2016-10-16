package com.softserve.edu.delivery.service.sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailSenderImpl implements EmailSender {

    Logger logger = LoggerFactory.getLogger(EmailSenderImpl.class.getName());

    @Autowired
    private Environment env;

    @Autowired
    private EmailAuthenticator auth;

    private Properties props = new Properties();

    private Session mailSession;

    @PostConstruct
    protected void init() {
        props.put("mail.smtp.auth", env.getProperty("mail.smtp.auth"));
        props.put("mail.smtp.starttls.enable", env.getProperty("mail.smtp.starttls.enable"));
        props.put("mail.smtp.host", env.getProperty("mail.smtp.host"));
        props.put("mail.smtp.port", env.getProperty("mail.smtp.port"));
        props.put("mail.mime.charset", env.getProperty("mail.mime.charset"));

        this.mailSession = Session.getInstance(this.props, this.auth);
    }

    /**
     * Method sends emails to receiver
     * @param receiver - email address of receiver
     * @param subject - subject of message
     * @param content - text message to client
     * @return true if email was send
     * */
    @Override
    public boolean send(String receiver, String subject, String content) {
        logger.info("In method EmailSenderImpl.send()");
        try {
            InternetAddress from = new InternetAddress(this.auth.getEmail());
            InternetAddress to = new InternetAddress(receiver);

            Message message = new MimeMessage(this.mailSession);
            message.setFrom(from);
            message.setRecipient(Message.RecipientType.TO, to);
            message.setSubject(subject);
            message.setText(content);

            Transport.send(message);
        }catch (MessagingException ex) {
            logger.error("Some problem in EmailSenderImpl.send() To receive message to: " + receiver + ". Exception: " + ex);
            return false;
        }
        logger.info("Out of method EmailSenderImpl.send()");
        return true;
    }
}