package com.softserve.edu.delivery.repository;

import com.softserve.edu.delivery.domain.Feedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.Optional;

public interface FeedbackRepository extends BaseRepository<Feedback, Long>, JpaRepository<Feedback, Long> {

    String FIND_BY =
            "select f from Feedback f " +
                    "join f.order ord " +
                    "join ord.offers off  " +
                    "join off.car c " +
                    "join c.driver u " +
                    "where off.approved = true and f.text like ?1 and f.rate >= ?2 and f.user.email in " +
                    "(select u.email from User u where concat(u.firstName, ' ', u.lastName) like ?3) " +
                    "and concat(u.firstName, ' ', u.lastName) like ?4 " +
                    "and f.createdOn >= ?5 and (f.approved = ?6 or f.approved = ?7) ";

    String ORDER_BY_USERNAME =
            "order by concat(f.user.firstName, ' ', f.user.lastName) ";

    String ORDER_BY_TRANSPORTER_NAME =
            "order by concat(u.firstName, ' ', u.lastName) ";

    String DESC = " desc ";

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

    @Query("select count(f.feedbackId)" +
            "from Feedback f where f.order.id = :id")
    Integer countFeedbacks(@Param("id") Long id);

    /*------------- Find all feedbacks -----------------------*/

    @Query(FIND_BY)
    Page<Feedback> findFiltered(String text, Integer rate, String userName, String transporterName,
                                Timestamp createdOn, Boolean approved0, Boolean approved1,
                                Pageable pageable);

    @Query(FIND_BY + ORDER_BY_USERNAME)
    Page<Feedback> findFilteredOrderByUserName(String text, Integer rate, String userName,
                                               String transporterName, Timestamp createdOn,
                                               Boolean approved0, Boolean approved1, Pageable pageable);

    @Query(FIND_BY + ORDER_BY_USERNAME + DESC)
    Page<Feedback> findFilteredOrderByUserNameDesc(String text, Integer rate, String userName,
                                                   String transporterName, Timestamp createdOn,
                                                   Boolean approved0, Boolean approved1, Pageable pageable);

    @Query(FIND_BY + ORDER_BY_TRANSPORTER_NAME)
    Page<Feedback> findFilteredOrderByTransporterName(String text, Integer rate, String userName,
                                                      String transporterName, Timestamp createdOn,
                                                      Boolean approved0, Boolean approved1, Pageable pageable);

    @Query(FIND_BY + ORDER_BY_TRANSPORTER_NAME + DESC)
    Page<Feedback> findFilteredOrderByTransporterNameDesc(String text, Integer rate, String userName,
                                                          String transporterName, Timestamp createdOn,
                                                          Boolean approved0, Boolean approved1, Pageable pageable);

}
