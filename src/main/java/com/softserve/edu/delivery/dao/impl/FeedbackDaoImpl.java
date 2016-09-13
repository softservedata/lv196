package com.softserve.edu.delivery.dao.impl;

import com.softserve.edu.delivery.dao.FeedbackDao;
import com.softserve.edu.delivery.domain.Feedback;

/**
 * Created by Ivan Rudnytskyi on 12.09.2016.
 */
public class FeedbackDaoImpl extends BaseDaoImpl<Feedback> implements FeedbackDao {
    public FeedbackDaoImpl() {
        super(Feedback.class);
    }
}
