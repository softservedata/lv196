package com.softserve.edu.delivery.repository;

import com.softserve.edu.delivery.domain.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

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

    @Query("select f from Feedback f where f.order.id = :id " +
            "and f.user.email = :email")
    List<Feedback> getFeedbackByOrderIdAndEmail(@Param("id") Long id,
                                                @Param("email") String email);

    @Query("select f from Feedback f where f.order.id = :id " +
            "and f.user.userRole = 'CUSTOMER'")
    Feedback getCustomerFeedback(@Param("id") Long orderId);

}
