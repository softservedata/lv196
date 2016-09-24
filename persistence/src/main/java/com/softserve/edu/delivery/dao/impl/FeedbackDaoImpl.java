package com.softserve.edu.delivery.dao.impl;

import com.softserve.edu.delivery.dao.FeedbackDao;
import com.softserve.edu.delivery.domain.Feedback;
import org.springframework.stereotype.Repository;

@Repository
public class FeedbackDaoImpl extends BaseDaoImpl<Feedback, Long> implements FeedbackDao {
    public FeedbackDaoImpl() {
        super(Feedback.class);
    }
}
