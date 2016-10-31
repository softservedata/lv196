package com.softserve.edu.delivery.service.impl;

import com.softserve.edu.delivery.domain.Notification;
import com.softserve.edu.delivery.domain.NotificationStatus;
import com.softserve.edu.delivery.domain.User;
import com.softserve.edu.delivery.dto.FeedbackDTO;
import com.softserve.edu.delivery.dto.NotificationDto;
import com.softserve.edu.delivery.dto.OfferDtoForList;
import com.softserve.edu.delivery.repository.NotificationRepository;
import com.softserve.edu.delivery.repository.OfferRepository;
import com.softserve.edu.delivery.repository.UserRepository;
import com.softserve.edu.delivery.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private OfferRepository offerRepository;
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
                .map(NotificationDto::notificationToNotificationDto)
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
                .map(NotificationDto::notificationToNotificationDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<NotificationDto> findNotificationByEmailAndWarningStatus(String email) {
        return notificationRepository
                .findNotificationByEmailAndStatus(email, NotificationStatus.WARNING.getName())
                .stream()
                .map(NotificationDto::notificationToNotificationDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<NotificationDto> findNotificationByEmailAndSuccessStatus(String email) {
        return notificationRepository
                .findNotificationByEmailAndStatus(email, NotificationStatus.SUCCESS.getName())
                .stream()
                .map(NotificationDto::notificationToNotificationDto)
                .collect(Collectors.toList());
    }

    @Override
    public void removeOrder(Long orderId) {
        offerRepository.getAllOffersByOrderId(orderId)
                .stream()
                .map(OfferDtoForList::offerToOfferDto)
                .forEach(offer ->
                        addNotification("Warning",
                                " Dear " + offer.getDriverName() + " We are sorry, but Customer " + offer.getCustomerName() + " cancel Order №" + offer.getOrderId() + "\t&#8195;<br> " +
                                " Дорогий " + offer.getDriverName() + " ми перепрошуємо, але користувач " + offer.getCustomerName() + " відмінив Ордер №" + offer.getOrderId(),
                                offer.getDriverEmail())
                );
    }

    @Override
    public void updateFeedback(FeedbackDTO feedbackDTO) {
        addNotification("Info",
                " Dear " + feedbackDTO.getUserName() + " your feedback for Driver " + feedbackDTO.getTransporterName() +
                        " was moderated. For now, your feedback status - approved = " + feedbackDTO.getApproved() + "\t&#8195;<br>" +
                " Дорогий " + feedbackDTO.getUserName() + " Ваш відгук на водія " + feedbackDTO.getTransporterName() +
                        " був змодерований. На данний момент, статус вашого відгука - approved = " + feedbackDTO.getApproved()
                ,feedbackDTO.getUserEmail());
    }

    @Override
    public void changeOfferStatus(OfferDtoForList offerDto){
        addNotification("Info",
                " Customer " + offerDto.getCustomerName() + " approved your offer for his Order" + "\t&#8195;<br>" +
                " Користувач " + offerDto.getCustomerName() + " затвердив вашу заявку на цей Ордер"
                , offerDto.getDriverEmail());
    }

    @Override
    public void changeUserStatus(String email, Boolean status) {
        User user = userRepository.findOneOpt(email)
                .orElseThrow(() -> new IllegalArgumentException("No such user with email: " + email));
        addNotification("Warning",
                " Dear " + user.getFirstName() + " " + user.getLastName() +
                " your status was changed by admin or moderator. For now your status - is blocked = " + user.getBlocked() + "\t&#8195;<br>" +
                " Дорогий " + user.getFirstName() + " " + user.getLastName() +
                        " ваш статус був змуненний Адміном чи Модератором. На данний момент ваш статус - Заблокованний = " + user.getBlocked()
                , email);
    }

}
