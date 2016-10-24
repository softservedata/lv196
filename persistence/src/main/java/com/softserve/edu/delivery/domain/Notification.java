package com.softserve.edu.delivery.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private boolean readed;

    private String message;

    private Timestamp time;

    @Enumerated(EnumType.STRING)
    private NotificationStatus notificationStatus;


    public Long getNotificationId() {
        return notificationId;
    }

    public Notification setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Notification setUser(User user) {
        this.user = user;
        return this;
    }

    public boolean isReaded() {
        return readed;
    }

    public Notification setReaded(boolean readed) {
        this.readed = readed;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Notification setMessage(String message) {
        this.message = message;
        return this;
    }

    public Timestamp getTime() {
        return time;
    }

    public Notification setTime(Timestamp time) {
        this.time = time;
        return this;
    }

    public NotificationStatus getNotificationStatus() {
        return notificationStatus;
    }

    public Notification setNotificationStatus(NotificationStatus notificationStatus) {
        this.notificationStatus = notificationStatus;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notification notification = (Notification) o;
        return Objects.equals(notificationId, notification.notificationId);
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
