package com.softserve.edu.delivery.repository;

import com.softserve.edu.delivery.domain.Feedback;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface FeedbackRepository extends BaseRepository<Feedback, Long> {

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

    /*------------- Find all feedbacks -----------------------*/

    List<Feedback> findByOrderByFeedbackIdDesc();

    /*------------- Find feedbacks by id -----------------------*/

    Feedback findByFeedbackId(Long id);

    List<Feedback> findByFeedbackIdGreaterThan(Long id);

    List<Feedback> findByFeedbackIdGreaterThanOrderByFeedbackIdDesc(Long id);

    List<Feedback> findByFeedbackIdLessThan(Long id);

    List<Feedback> findByFeedbackIdLessThanOrderByFeedbackIdDesc(Long id);

    /*------------- Find feedbacks by text -----------------------*/

    List<Feedback> findByTextContaining(String text);

    List<Feedback> findByRate(Integer rate);

    List<Feedback> findByRateGreaterThan(Integer rate);

    List<Feedback> findByRateLessThan(Integer rate);

    @Query("select f from Feedback f where f.user.email in " +
            "(select u.email from User u " +
            "where first_name like %?1% or last_name like %?1%)")
    List<Feedback> findByUserFirstNameOrLastName(String name);

    @Query(value = "select * from feedbacks f " +
            "join orders ord on ord.id = f.order_id " +
            "join offers off on off.order_id=ord.id and off.approved " +
            "join cars c on off.car_id=c.car_id " +
            "join users u on c.driver_id=u.email " +
            "where u.first_name like %?1% or u.last_name like %?1%", nativeQuery = true)
    List<Feedback> findByTransporterFirstNameOrLastName(String name);

    List<Feedback> findByApproved(Boolean approved);

    List<Feedback> findByCreatedOnAfter(Timestamp createdOn);

}
