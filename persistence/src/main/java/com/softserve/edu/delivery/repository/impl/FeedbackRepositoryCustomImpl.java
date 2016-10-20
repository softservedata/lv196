package com.softserve.edu.delivery.repository.impl;

import com.softserve.edu.delivery.domain.Feedback;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Timestamp;
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
    private final String ORDER_BY_USERNAME =
            "order by concat(f.user.firstName, ' ', f.user.lastName) ";
    private final String ORDER_BY_TRANSPORTER_NAME =
            "order by concat(u.firstName, ' ', u.lastName) ";
    private long totalItemsNumber;
    @PersistenceContext
    private EntityManager entityManager;

    private String countQuery;
    private String resultQuery;

    private void setQueries(int sortType, String sortBy, String sortOrder) {
        switch (sortType) {
            case 1:
                countQuery = GET_COUNT + ORDER_BY_USERNAME + sortOrder;
                resultQuery = FIND_BY + ORDER_BY_USERNAME + sortOrder;
                break;
            case 2:
                countQuery = GET_COUNT + ORDER_BY_TRANSPORTER_NAME + sortOrder;
                resultQuery = FIND_BY + ORDER_BY_TRANSPORTER_NAME + sortOrder;
                break;
            default:
                countQuery = GET_COUNT + ORDER_BY + sortBy + " " + sortOrder;
                resultQuery = FIND_BY + ORDER_BY + sortBy + " " + sortOrder;
        }
    }

    public long getTotalItemsNumber() {
        return totalItemsNumber;
    }

    public List<Feedback> findFiltered(String text, Integer rate, String userName, String transporterName,
                                       Timestamp createdOn, Boolean approved0, Boolean approved1, int currentPage,
                                       int itemsPerPage, int sortType, String sortBy, String sortOrder) {

        setQueries(sortType, sortBy, sortOrder);

        totalItemsNumber = (long) entityManager.createQuery(countQuery)
                .setParameter("text", text)
                .setParameter("rate", rate)
                .setParameter("userName", userName)
                .setParameter("transporterName", transporterName)
                .setParameter("createdOn", createdOn)
                .setParameter("approved0", approved0)
                .setParameter("approved1", approved1)
                .getSingleResult();

        return entityManager.createQuery(resultQuery)
                .setParameter("text", text)
                .setParameter("rate", rate)
                .setParameter("userName", userName)
                .setParameter("transporterName", transporterName)
                .setParameter("createdOn", createdOn)
                .setParameter("approved0", approved0)
                .setParameter("approved1", approved1)
                .setFirstResult((currentPage - 1) * itemsPerPage)
                .setMaxResults(itemsPerPage)
                .getResultList();
    }
}
