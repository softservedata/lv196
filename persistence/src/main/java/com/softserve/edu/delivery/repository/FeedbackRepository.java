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

    Feedback findByFeedbackId(Long id);

    List<Feedback> findByTextContaining(String text);

    List<Feedback> findByFeedbackIdGreaterThan(Long id);

    List<Feedback> findByFeedbackIdLessThan(Long id);

    List<Feedback> findByRate (Integer rate);

    List <Feedback> findByRateGreaterThan (Integer rate);

    List <Feedback> findByRateLessThan (Integer rate);

    @Query("select f from Feedback f where f.user.email in " +
            "(select u.email from User u " +
            "where first_name like %?1% or last_name like %?1%)")
    List <Feedback> findByUserFirstNameOrLastName(String name);

    @Query("select f from Feedback f")// where f.order.id in " +
//            "(select ord.id from Order ord " +
//            "join ord.offers off " +
//            "join off.cars c " +
//            "join c.users u " +
//            "where off.approved and (u.first_name like %?1% or u.last_name like %?1%))")
    List <Feedback> findByTransporterFirstNameOrLastName(String name);

}
