package com.softserve.edu.delivery.dao;

import com.softserve.edu.delivery.domain.Feedback;

/**
 * Created by Ivan Rudnytskyi on 12.09.2016.
 */
public interface FeedbackDao extends BaseDao<Feedback, Long> {
    Long getId(String query);
}
