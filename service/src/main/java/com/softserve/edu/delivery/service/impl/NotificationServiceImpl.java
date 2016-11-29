package com.softserve.edu.delivery.service.impl;

import com.softserve.edu.delivery.domain.*;
import com.softserve.edu.delivery.dto.FeedbackDto;
import com.softserve.edu.delivery.dto.NotificationDto;
import com.softserve.edu.delivery.dto.OfferDto;
import com.softserve.edu.delivery.dto.UserProfileDto;
import com.softserve.edu.delivery.repository.NotificationRepository;
import com.softserve.edu.delivery.repository.OfferRepository;
import com.softserve.edu.delivery.repository.OrderRepository;
import com.softserve.edu.delivery.repository.UserRepository;
import com.softserve.edu.delivery.service.NotificationService;
import com.softserve.edu.delivery.service.strings.BundleKeyStrings;
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
import java.util.stream.Collectors;

@Service
@Transactional
public class NotificationServiceImpl implements NotificationService, BundleKeyStrings {

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
    private TemplateEngine templateEngine;
    @Autowired
    private JavaMailSender mailSender;

    private final Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class.getName());
    private static Locale currentLocal;
    private static ResourceBundle res;
    private static ExecutorService executor = Executors.newSingleThreadExecutor();
    private static final String PROPERTY = "spring.mail.username";

    @Override
    public void addNotification(NotificationStatus status, String message, String email) {
        User user = userRepository.findOneOpt(email)
                .orElseThrow(() -> new IllegalArgumentException("No such user with email: " + email));
        Notification notification = new Notification();
        notification
                .setNotificationStatus(status)
                .setUser(user)
                .setMessage(message)
                .setReaded(false)
                .setTime(new Timestamp(new Date().getTime()));
            notificationRepository.save(notification);
        try {
            sendEmail(notification);
        }
        catch (RuntimeException e) {
            logger.error("tasks sendEmail interrupted");
        }
    }

    private void sendEmail(Notification notification){
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(env.getProperty(PROPERTY));
            messageHelper.setTo(notification.getUser().getEmail());
            messageHelper.setSubject(res.getString(NOTIFICATION_FROM_DELIVERY));
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
        executor.submit(() -> offerRepository.getAllOffersByOrderId(orderId)
                .stream()
                .map(OfferDto::offerToOfferDto)
                .forEach(offer ->
                        addNotification(NotificationStatus.WARNING, new StringBuilder()
                                .append(res.getString(DEAR))
                                .append(offer.getDriverName())
                                .append(res.getString(WE_ARE_SORRY))
                                .append(offer.getCustomerName())
                                .append(res.getString(CANCEL_ORDER))
                                .append(offer.getOrderId())
                                .toString(), offer.getDriverEmail())
                ));
    }

    @Override
    public void updateFeedback(FeedbackDto feedbackDTO) {
        executor.submit(() -> addNotification(NotificationStatus.INFO, new StringBuilder()
                .append(res.getString(DEAR))
                .append(feedbackDTO.getUserName())
                .append(res.getString(YOUR_FEEDBACK_FOR))
                .append(feedbackDTO.getTransporterName())
                .append(res.getString(WAS_MODERATED))
                .append(feedbackDTO.getApproved())
                .toString(), feedbackDTO.getUserEmail()));
    }

    @Override
    public void changeOfferStatus(OfferDto offerDto){
        executor.submit(() -> addNotification(NotificationStatus.INFO, new StringBuilder()
                        .append(res.getString(CUSTOMER))
                        .append(offerDto.getCustomerName())
                        .append(res.getString(APPROVED_YOUR_OFFER))
                        .toString(), offerDto.getDriverEmail()));
    }

    @Override
    public void changeUserStatus(String email, Boolean status) {
        executor.submit(() -> {
            User user = userRepository.findOneOpt(email)
                .orElseThrow(() -> new IllegalArgumentException("No such user with email: " + email));
            addNotification(NotificationStatus.WARNING, new StringBuilder()
                    .append(res.getString(DEAR))
                    .append(user.getFirstName())
                    .append(" ")
                    .append(user.getLastName())
                    .append(res.getString(YOUR_USER_STATUS))
                    .append(user.getBlocked())
                    .toString(), email);
        });
    }

    @Override
    public void addOffer(Long orderId, String email){
        executor.submit(() -> {
            User user = userRepository.findOneOpt(email)
                .orElseThrow(() -> new IllegalArgumentException("No such user with email: " + email));
            Order order = orderRepository.findOneOpt(orderId)
                .orElseThrow(() -> new IllegalArgumentException("No such order with id: " + orderId));
            addNotification(NotificationStatus.INFO, new StringBuilder()
                    .append(res.getString(DRIVER))
                    .append(user.getFirstName())
                    .append(" ")
                    .append(user.getLastName())
                    .append(res.getString(SEND_OFFER))
                    .toString(), order.getCustomer().getEmail());
        });
    }

    @Override
    public void approveDelivery(Long orderId){
        executor.submit(() -> {
            Order order = orderRepository.findOneOpt(orderId)
                    .orElseThrow(() -> new IllegalArgumentException("No such order with id: " + orderId));
            Offer offer = offerRepository.getOfferByOrderIdAndStatus(orderId);
            User user = offer.getCar().getDriver();
            addNotification(NotificationStatus.INFO, new StringBuilder()
                    .append(res.getString(DRIVER))
                    .append(user.getFirstName())
                    .append(" ")
                    .append(user.getLastName())
                    .append(res.getString(APPROVE_DELIVERY))
                    .toString(), order.getCustomer().getEmail());
        });
    }

    public void changeUserRole(UserProfileDto user){
        executor.submit(() -> addNotification(NotificationStatus.INFO, new StringBuilder()
                .append(res.getString(DEAR))
                .append(user.getFirstName())
                .append(" ")
                .append(user.getLastName())
                .append(res.getString(YOUR_ROLE_CHANGED))
                .append(user.getRole())
                .toString(), user.getEmail()));
    }

}
