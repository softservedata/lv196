package com.softserve.edu.delivery.repository;


import com.softserve.edu.delivery.domain.Notification;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface NotificationRepository extends BaseRepository<Notification, Long> {

    @Query("select count(n.notificationId) from Notification n where n.user.email = :email and n.readed = false")
    Integer countNewNotification(@Param("email") String email);

    @Modifying
    @Query("update Notification n set n.readed = true where n.user.email = :email")
    void changeNotificationStatusByUserEmail(@Param("email") String email);

    Long removeByNotificationId(Long notificationId);

    @Query("select n from Notification n where n.user.email = :email")
    List<Notification> findNotificationByEmail(@Param("email") String email);

    @Query("select n from Notification n where n.user.email = :email and n.notificationStatus = :status")
    List<Notification> findNotificationByEmailAndStatus (@Param("email") String email, @Param("status") String status );
}
