package com.softserve.edu.delivery.dto;

import com.softserve.edu.delivery.domain.*;

import java.sql.Timestamp;
import java.util.Objects;


public class NotificationDto {


    private Long notificationId;
    private String email;
    private boolean readed;
    private String message;
    private Timestamp time;
    private String notificationStatus;


    public NotificationDto(){}

    public static NotificationDto notificationToNotificationDto(Notification notification){
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setNotificationId(notification.getNotificationId())
                .setEmail(notification.getUser().getEmail())
                .setMessage(notification.getMessage())
                .setNotificationStatus(notification.getNotificationStatus().getName())
                .setReaded(notification.isReaded())
                .setTime(notification.getTime());
        return notificationDto;
    }

    public Long getNotificationId() {
        return notificationId;
    }

    public NotificationDto setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public NotificationDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public boolean isReaded() {
        return readed;
    }

    public NotificationDto setReaded(boolean readed) {
        this.readed = readed;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public NotificationDto setMessage(String message) {
        this.message = message;
        return this;
    }

    public Timestamp getTime() {
        return time;
    }

    public NotificationDto setTime(Timestamp time) {
        this.time = time;
        return this;
    }

    public String getNotificationStatus() {
        return notificationStatus;
    }

    public NotificationDto setNotificationStatus(String notificationStatus) {
        this.notificationStatus = notificationStatus;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationDto notificationDto = (NotificationDto) o;
        return Objects.equals(notificationId, notificationDto.notificationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(notificationId);
    }

    @Override
    public String toString() {
        return "Notification [id=" + notificationId + ", message=" + message + ", time=" + time + ", status=" + notificationStatus + "]";
    }
}
