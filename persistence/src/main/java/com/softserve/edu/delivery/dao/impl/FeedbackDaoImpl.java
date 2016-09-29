package com.softserve.edu.delivery.dao.impl;

import com.softserve.edu.delivery.dao.FeedbackDao;
import com.softserve.edu.delivery.domain.Feedback;

/**
 * Created by Ivan Rudnytskyi on 12.09.2016.
 */
public class FeedbackDaoImpl extends BaseDaoImpl<Feedback, Long> implements FeedbackDao {
    public FeedbackDaoImpl() {
        super(Feedback.class);
    }

    @Override
    public Long getId(String query) {
        return (Long) super.getEntityManager().createQuery(query).getSingleResult();
    }
}
