package com.softserve.edu.delivery.dao;

import com.softserve.edu.delivery.domain.Feedback;

import java.util.List;
import java.util.Optional;

/**
 * Created by Ivan Rudnytskyi on 12.09.2016.
 */
public interface FeedbackDao extends BaseDao<Feedback, Long> {
    List<Feedback> findAllFeedbacksInRange(Long idFrom, int number);
    Optional<String> getApprovedDriverName(Long id);
    Optional<String> getApprovedDriverEmail(Long id);
}
