package com.softserve.edu.delivery.service;

import com.softserve.edu.delivery.dto.NotificationDto;

import java.util.List;

/**
 * Created by Taras Kurdiukov on 22.10.2016.
 */
public interface NotificationService {

    void addNotification(String status, String message, String email);
    void removeNotification(Long notificationId);
    List<NotificationDto> findAllNotificationByEmail(String email);
    void changeNotificationStatus(String email);
    Integer countNewNotification(String email);
    List<NotificationDto> findNotificationByEmailAndInfoStatus(String email);
    List<NotificationDto> findNotificationByEmailAndWarningStatus(String email);
    List<NotificationDto> findNotificationByEmailAndSuccessStatus(String email);
}
