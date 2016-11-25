package com.softserve.edu.delivery.repository.impl;

import com.softserve.edu.delivery.domain.Feedback;
import com.softserve.edu.delivery.domain.FeedbackFilter;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class FeedbackRepositoryCustomImpl {

    private final String FIND_BY =
            "select f from Feedback f " +
                    "join f.order ord " +
                    "join ord.offers off  " +
                    "join off.car c " +
                    "join c.driver u " +
                    "where off.approved = true and f.text like :text and f.rate >=:rate and f.user.email in " +
                    "(select u.email from User u where concat(u.firstName, ' ', u.lastName) like :userName) " +
                    "and concat(u.firstName, ' ', u.lastName) like :transporterName " +
                    "and f.createdOn >= :createdOn and (f.approved =:approved0 or f.approved =:approved1) ";

    private final String GET_COUNT =
            "select count(f) from Feedback f " +
                    "join f.order ord " +
                    "join ord.offers off  " +
                    "join off.car c " +
                    "join c.driver u " +
                    "where off.approved = true and f.text like :text and f.rate >=:rate and f.user.email in " +
                    "(select u.email from User u where concat(u.firstName, ' ', u.lastName) like :userName) " +
                    "and concat(u.firstName, ' ', u.lastName) like :transporterName " +
                    "and f.createdOn >= :createdOn and (f.approved =:approved0 or f.approved =:approved1) ";

    private final String ORDER_BY = " order by f.";
    private final String ORDER_BY_USERNAME = "order by concat(f.user.firstName, ' ', f.user.lastName) ";
    private final String ORDER_BY_TRANSPORTER_NAME = "order by concat(u.firstName, ' ', u.lastName) ";

    @PersistenceContext
    private EntityManager entityManager;

    private String countQuery;
    private String resultQuery;

    private void setQueries(String sortBy, String sortOrder) {
        switch (sortBy) {
            case "userName":
                countQuery = GET_COUNT + ORDER_BY_USERNAME + sortOrder;
                resultQuery = FIND_BY + ORDER_BY_USERNAME + sortOrder;
                break;
            case "transporterName":
                countQuery = GET_COUNT + ORDER_BY_TRANSPORTER_NAME + sortOrder;
                resultQuery = FIND_BY + ORDER_BY_TRANSPORTER_NAME + sortOrder;
                break;
            default:
                countQuery = GET_COUNT + ORDER_BY + sortBy + " " + sortOrder;
                resultQuery = FIND_BY + ORDER_BY + sortBy + " " + sortOrder;
        }
    }

    public long getTotalItemsNumber(FeedbackFilter feedbackFilter) {
        return (long) entityManager.createQuery(countQuery)
                .setParameter("text", feedbackFilter.getText())
                .setParameter("rate", feedbackFilter.getRate())
                .setParameter("userName", feedbackFilter.getUserName())
                .setParameter("transporterName", feedbackFilter.getTransporterName())
                .setParameter("createdOn", feedbackFilter.getCreatedOn())
                .setParameter("approved0", feedbackFilter.getApproved0())
                .setParameter("approved1", feedbackFilter.getApproved1())
                .getSingleResult();
    }

    @SuppressWarnings("unchecked")
    public List<Feedback> findFiltered(FeedbackFilter feedbackFilter) {

        setQueries(feedbackFilter.getSortBy(), feedbackFilter.getSortOrder());

        return entityManager.createQuery(resultQuery)
                .setParameter("text", feedbackFilter.getText())
                .setParameter("rate", feedbackFilter.getRate())
                .setParameter("userName", feedbackFilter.getUserName())
                .setParameter("transporterName", feedbackFilter.getTransporterName())
                .setParameter("createdOn", feedbackFilter.getCreatedOn())
                .setParameter("approved0", feedbackFilter.getApproved0())
                .setParameter("approved1", feedbackFilter.getApproved1())
                .setFirstResult((feedbackFilter.getCurrentPage() - 1) * feedbackFilter.getItemsPerPage())
                .setMaxResults(feedbackFilter.getItemsPerPage())
                .getResultList();
    }

}
