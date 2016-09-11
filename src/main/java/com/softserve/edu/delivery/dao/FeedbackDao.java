package com.softserve.edu.delivery.dao;

import com.softserve.edu.delivery.domain.Feedback;

/**
 * Created by Ivan Rudnytskyi on 11.09.2016.
 */

public class FeedbackDao extends AbstractDao<Feedback> {
    public FeedbackDao() {
        super(Feedback.class);
    }
}
