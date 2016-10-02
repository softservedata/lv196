package com.softserve.edu.delivery.dao;

import com.softserve.edu.delivery.domain.Feedback;
import com.softserve.edu.delivery.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Ivan Rudnytskyi on 12.09.2016.
 */
public interface FeedbackDao extends BaseDao<Feedback, Long> {
    Long getId(String query);
    String getApprovedDriverName(Long id);
    List<User> getUsersByRole(String userRole);
}
