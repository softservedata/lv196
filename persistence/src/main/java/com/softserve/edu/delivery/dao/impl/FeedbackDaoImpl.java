package com.softserve.edu.delivery.dao.impl;

import com.softserve.edu.delivery.dao.FeedbackDao;
import com.softserve.edu.delivery.domain.Feedback;
import com.softserve.edu.delivery.domain.Order;
import javax.persistence.EntityManager;
import javax.persistence.Query;



/**
 * Created by Ivan Rudnytskyi on 12.09.2016.
 */
public class FeedbackDaoImpl extends BaseDaoImpl<Feedback, Long> implements FeedbackDao {
    public FeedbackDaoImpl() {
        super(Feedback.class);
    }

    @Override
    public Feedback findApprovedFeedbackByOrder(Order order) {
        Feedback feedback;
        try {
            EntityManager em = super.getEntityManager();
            Query query =
                    em.createQuery("select f from Feedback f where f.order.id = :id and f.approved = :isApproved");
            query.setParameter("id", order.getId());
            query.setParameter("isApproved", true);
            feedback = (Feedback) query.getSingleResult();
        }
        catch (Exception e) {
            feedback = new Feedback();
            feedback.setText("No approved feedback");
        }
        return feedback;
    }
}
