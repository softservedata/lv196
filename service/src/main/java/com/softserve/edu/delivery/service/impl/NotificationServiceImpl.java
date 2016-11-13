package com.softserve.edu.delivery.service.impl;

import com.softserve.edu.delivery.domain.Notification;
import com.softserve.edu.delivery.domain.NotificationStatus;
import com.softserve.edu.delivery.domain.Order;
import com.softserve.edu.delivery.domain.User;
import com.softserve.edu.delivery.dto.FeedbackDto;
import com.softserve.edu.delivery.dto.NotificationDto;
import com.softserve.edu.delivery.dto.OfferDto;
import com.softserve.edu.delivery.repository.NotificationRepository;
import com.softserve.edu.delivery.repository.OfferRepository;
import com.softserve.edu.delivery.repository.OrderRepository;
import com.softserve.edu.delivery.repository.UserRepository;
import com.softserve.edu.delivery.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
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
    private OrderRepository orderRepository;
    @Autowired
    private Environment env;
    @Autowired
    TemplateEngine templateEngine;
    @Autowired
    private JavaMailSender mailSender;

//    Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class.getName());
    private static Locale currentLocal;
    private static ResourceBundle res;

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
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> sendEmail(notification));
        try {
            System.out.println("shutdown executor for sendEmail");
            executor.shutdown();
            executor.awaitTermination(10, TimeUnit.SECONDS);
        }
        catch (InterruptedException e) {
            System.err.println("tasks sendEmail interrupted");
        }
        finally {
            if (!executor.isTerminated()) {
                System.err.println("cancel non-finished tasks sendEmail");
            }
            executor.shutdownNow();
            System.out.println("shutdown finished");
        }


    }

    private void sendEmail(Notification notification){
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(env.getProperty("spring.mail.username"));
            messageHelper.setTo(notification.getUser().getEmail());
            messageHelper.setSubject("Notification from Delivery");
            String text = buildHtmlTemplate(notification.getMessage());
            messageHelper.setText(text, true);
        };
        mailSender.send(messagePreparator);
    }

    private String buildHtmlTemplate(String notification) {
        Context context = new Context();
        context.setVariable("notification", notification);
        return templateEngine.process("notification", context);
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
    public void setLanguage(String mylocale) {
        currentLocal = new Locale(mylocale);
        res = ResourceBundle.getBundle("notification", currentLocal);
    }

    @Override
    public void removeOrder(Long orderId) {
        offerRepository.getAllOffersByOrderId(orderId)
                .stream()
                .map(OfferDto::offerToOfferDto)
                .forEach(offer ->
                        addNotification("Warning",
                                res.getString("dear") + " " + offer.getDriverName() +  res.getString("we_are_sorry") + " " + offer.getCustomerName() + res.getString("cancel_order") + " " + offer.getOrderId()
                                ,offer.getDriverEmail())
                );
    }

    @Override
    public void updateFeedback(FeedbackDto feedbackDTO) {
        addNotification("Info",
                res.getString("dear") + " " + feedbackDTO.getUserName() + res.getString("your_feedback") + " " + feedbackDTO.getTransporterName() +
                        res.getString("feedback_status") + " " + feedbackDTO.getApproved()
                ,feedbackDTO.getUserEmail());
    }

    @Override
    public void changeOfferStatus(OfferDto offerDto){
        addNotification("Info",
                res.getString("customer") + " " + offerDto.getCustomerName() + res.getString("approved_your_offer")
                , offerDto.getDriverEmail());
    }

    @Override
    public void changeUserStatus(String email, Boolean status) {
        User user = userRepository.findOneOpt(email)
                .orElseThrow(() -> new IllegalArgumentException("No such user with email: " + email));
        addNotification("Warning",
                res.getString("dear") + " " + user.getFirstName() + " " + user.getLastName() +
                res.getString("your_user_status") + " " + user.getBlocked()
                , email);
    }

    @Override
    public void addOffer(Long orderId, String email){
        User user = userRepository.findOneOpt(email)
                .orElseThrow(() -> new IllegalArgumentException("No such user with email: " + email));
        Order order = orderRepository.findOneOpt(orderId)
                .orElseThrow(() -> new IllegalArgumentException("No such user with email: " + email));
        addNotification("Info",
                res.getString("driver") + " " + user.getFirstName() + " " + user.getLastName() + res.getString("send_offer")
                , order.getCustomer().getEmail());
    }

}
