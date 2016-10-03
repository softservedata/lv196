package com.softserve.edu.delivery.dao.impl;

import com.softserve.edu.delivery.dao.FeedbackDao;
import com.softserve.edu.delivery.domain.Feedback;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class FeedbackDaoImpl extends BaseDaoImpl<Feedback, Long> implements FeedbackDao {

    public FeedbackDaoImpl() {
        super(Feedback.class);
    }

    @Override
    public List<Feedback> findAllFeedbacksInRange(Long idFrom, int number) {
        return getEntityManager()
                .createQuery("select f from Feedback f where f.id >=:idFrom")
                .setParameter("idFrom", idFrom)
                .setMaxResults(number)
                .getResultList();
    }

    @Override
    public Optional<String> getApprovedDriverName(Long id){
        return getEntityManager()
                .createQuery("select concat(u.firstName, ' ', u.lastName) from User u " +
                        "join u.cars c " +
                        "join c.offers off " +
                        "join off.order ord " +
                        "where ord.id = :id and off.approved = true", String.class)
                .setParameter("id", id)
                .getResultList()
                .stream()
                .findFirst();
    }
}
