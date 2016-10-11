package com.softserve.edu.delivery.service.sender;

public interface EmailSender {

    boolean send(String receiver, String subject, String content);

}
