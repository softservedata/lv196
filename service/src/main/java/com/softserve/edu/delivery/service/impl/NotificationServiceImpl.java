package com.softserve.edu.delivery.service.impl;

import com.softserve.edu.delivery.domain.Notification;
import com.softserve.edu.delivery.domain.NotificationStatus;
import com.softserve.edu.delivery.domain.User;
import com.softserve.edu.delivery.dto.NotificationDto;
import com.softserve.edu.delivery.repository.NotificationRepository;
import com.softserve.edu.delivery.repository.UserRepository;
import com.softserve.edu.delivery.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Taras Kurdiukov on 22.10.2016.
 */
@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    @Autowired
    private Environment env;
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void addNotification(String status,String message,String email) {
        User user = userRepository.findOneOpt(email)
                .orElseThrow(() -> new IllegalArgumentException("No such user with email: " + email));
        Notification notification = new Notification();
        if (status.equals(NotificationStatus.SUCCESS.getName())){
                notification.setNotificationStatus(NotificationStatus.SUCCESS);
        }
        else if (status.equals(NotificationStatus.WARNING.getName())){
                notification.setNotificationStatus(NotificationStatus.WARNING);
        }
        else {
            notification.setNotificationStatus(NotificationStatus.INFO);
        }
                notification.setUser(user)
                .setMessage(message)
                .setReaded(false)
                .setTime(new Timestamp(new Date().getTime()));

        notificationRepository.save(notification);

        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(env.getProperty("spring.mail.username"));
            messageHelper.setTo(email);
            messageHelper.setSubject("Notification from Delivery");
            String text = status + " notification: " + message;
            messageHelper.setText(text, true);
        };
        mailSender.send(messagePreparator);
    }

    @Override
    public void removeNotification(Long notificationId) {
        notificationRepository.removeByNotificationId(notificationId);
    }

    public List<NotificationDto> findAllNotificationByEmail(String email) {
        changeNotificationStatus(email);
        return notificationRepository
                .findNotificationByEmail(email)
                .stream()
                .map(notification -> {
                    NotificationDto dto = NotificationDto.notificationToNotificationDto(notification);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void changeNotificationStatus(String email) {
        notificationRepository.changeNotificationStatusByUserEmail(email);
    }

    @Override
    public Integer countNewNotification(String email){
        return notificationRepository.countNewNotification(email);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NotificationDto> findNotificationByEmailAndInfoStatus(String email) {
        return notificationRepository
                .findNotificationByEmailAndStatus(email, NotificationStatus.INFO.getName())
                .stream()
                .map(notification -> {
                    NotificationDto dto = NotificationDto.notificationToNotificationDto(notification);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<NotificationDto> findNotificationByEmailAndWarningStatus(String email) {
        return notificationRepository
                .findNotificationByEmailAndStatus(email, NotificationStatus.WARNING.getName())
                .stream()
                .map(notification -> {
                    NotificationDto dto = NotificationDto.notificationToNotificationDto(notification);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<NotificationDto> findNotificationByEmailAndSuccessStatus(String email) {
        return notificationRepository
                .findNotificationByEmailAndStatus(email, NotificationStatus.SUCCESS.getName())
                .stream()
                .map(notification -> {
                    NotificationDto dto = NotificationDto.notificationToNotificationDto(notification);
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
