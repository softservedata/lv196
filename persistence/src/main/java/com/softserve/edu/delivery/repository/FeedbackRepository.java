package com.softserve.edu.delivery.repository;

import com.softserve.edu.delivery.domain.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FeedbackRepository extends BaseRepository<Feedback, Long>, JpaRepository<Feedback, Long> {

    @Query("select concat(u.firstName, ' ', u.lastName) from User u " +
            "join u.cars c " +
            "join c.offers off " +
            "join off.order ord " +
            "where ord.id = :id and off.approved = true")
    Optional<String> getApprovedDriverName(@Param("id") Long id);

    @Query("select u.email from User u " +
            "join u.cars c " +
            "join c.offers off " +
            "join off.order ord " +
            "where ord.id = :id and off.approved = true")
    Optional<String> getApprovedDriverEmail(@Param("id") Long id);

    @Query("select f from Feedback f where f.order.id = :id")
    List<Feedback> getFeedbackByOrderId(@Param("id") Long id);

}
