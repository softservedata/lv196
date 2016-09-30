package com.softserve.edu.delivery.dao.impl;

import com.softserve.edu.delivery.dao.FeedbackDao;
import com.softserve.edu.delivery.domain.Feedback;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FeedbackDaoImpl extends BaseDaoImpl<Feedback, Long> implements FeedbackDao {
    public FeedbackDaoImpl() {
        super(Feedback.class);
    }

    @Override
    public Long getId(String query) {
        return (Long) super.getEntityManager().createQuery(query).getSingleResult();
    }

    @Override
    public String getApprovedDriverName(Long id){
        String query = "select u.first_name, u.last_name from orders ord " +
                "join offer of on " +
                "ord.order_id=of.order_id " +
                "join cars c on " +
                "of.car_id=c.car_id " +
                "join users u on " +
                "c.driver_id=u.email " +
                "where of.isApproved and ord.order_id=?1";
        List<Object[]> approvedDriverName = new ArrayList<>();
        approvedDriverName = super.getEntityManager().createNativeQuery(query)
                .setParameter(1, id)
                .getResultList();

        return approvedDriverName.get(0)[0] + " " + approvedDriverName.get(0)[1];
    }
}
