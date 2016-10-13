package com.softserve.edu.delivery.repository;

import com.softserve.edu.delivery.domain.Feedback;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;
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
                    "and f.createdOn >= ?5 and ";

    String APPROVED_UNDEFINED =
            "(f.approved = ?6 or f.approved = ?7) ";

    String APPROVED_DEFINED =
            "f.approved = ?6 ";

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

    /*------------- Find all feedbacks -----------------------*/

    @Query(FIND_BY + APPROVED_UNDEFINED)
    List<Feedback> findFilteredStatusUndefined(String text, Integer rate, String userName, String transporterName,
                                                        Timestamp createdOn, Boolean approved0, Boolean approved1, Sort sort);

    @Query(FIND_BY + APPROVED_DEFINED)
    List<Feedback> findFilteredStatusDefined(String text, Integer rate, String userName, String transporterName,
                                               Timestamp createdOn, Boolean approved, Sort sort);

    @Query(FIND_BY + APPROVED_UNDEFINED + ORDER_BY_USERNAME)
    List<Feedback> findFilteredOrderByUserNameStatusUndefined(String text, Integer rate, String userName, String transporterName,
                                                              Timestamp createdOn, Boolean approved0, Boolean approved1);

    @Query(FIND_BY + APPROVED_DEFINED + ORDER_BY_USERNAME)
    List<Feedback> findFilteredOrderByUserNameStatusDefined(String text, Integer rate, String userName, String transporterName,
                                                              Timestamp createdOn, Boolean approved0);

    @Query(FIND_BY + APPROVED_UNDEFINED + ORDER_BY_USERNAME + DESC)
    List<Feedback> findFilteredOrderByUserNameStatusUndefinedDesc(String text, Integer rate, String userName, String transporterName,
                                                              Timestamp createdOn, Boolean approved0, Boolean approved1);

    @Query(FIND_BY + APPROVED_DEFINED + ORDER_BY_USERNAME + DESC)
    List<Feedback> findFilteredOrderByUserNameStatusDefinedDesc(String text, Integer rate, String userName, String transporterName,
                                                            Timestamp createdOn, Boolean approved0);

    @Query(FIND_BY + APPROVED_UNDEFINED + ORDER_BY_TRANSPORTER_NAME)
    List<Feedback> findFilteredOrderByTransporterNameStatusUndefined(String text, Integer rate, String userName, String transporterName,
                                                              Timestamp createdOn, Boolean approved0, Boolean approved1);

    @Query(FIND_BY + APPROVED_DEFINED + ORDER_BY_TRANSPORTER_NAME)
    List<Feedback> findFilteredOrderByTransporterNameStatusDefined(String text, Integer rate, String userName, String transporterName,
                                                            Timestamp createdOn, Boolean approved0);

    @Query(FIND_BY + APPROVED_UNDEFINED + ORDER_BY_TRANSPORTER_NAME + DESC)
    List<Feedback> findFilteredOrderByTransporterNameStatusUndefinedDesc(String text, Integer rate, String userName, String transporterName,
                                                                  Timestamp createdOn, Boolean approved0, Boolean approved1);

    @Query(FIND_BY + APPROVED_DEFINED + ORDER_BY_TRANSPORTER_NAME + DESC)
    List<Feedback> findFilteredOrderByTransporterNameStatusDefinedDesc(String text, Integer rate, String userName, String transporterName,
                                                                Timestamp createdOn, Boolean approved0);
}
