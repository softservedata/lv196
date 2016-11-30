package com.softserve.edu.delivery.service;

import com.softserve.edu.delivery.domain.NotificationStatus;
import com.softserve.edu.delivery.dto.FeedbackDto;
import com.softserve.edu.delivery.dto.NotificationDto;
import com.softserve.edu.delivery.dto.OfferDto;
import com.softserve.edu.delivery.dto.UserProfileDto;

import java.util.List;

public interface NotificationService {

    void addNotification(NotificationStatus status, String message, String email);
    void removeNotification(Long notificationId);
    List<NotificationDto> findAllNotificationByEmail(String email);
    void changeNotificationStatus(String email);
    Integer countNewNotification(String email);

    void removeOrder(Long orderId);
    void changeOfferStatus(OfferDto offerDto);
    void updateFeedback(FeedbackDto feedbackDTO);
    void changeUserStatus(String email, Boolean status);
    void addOffer(Long orderId, String email);
    void setLanguage(String mylocale);
    void driverFinishRoute(Long orderId);
    void customerApproveDelivery(Long orderId);
    void changeUserRole(UserProfileDto user);

}
