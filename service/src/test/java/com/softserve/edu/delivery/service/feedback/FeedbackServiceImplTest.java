package com.softserve.edu.delivery.service.feedback;

import com.softserve.edu.delivery.dao.OrderDao;
import com.softserve.edu.delivery.dao.UserDao;
import com.softserve.edu.delivery.dao.impl.OrderDaoImpl;
import com.softserve.edu.delivery.dao.impl.UserDaoImpl;
import com.softserve.edu.delivery.domain.Feedback;
import com.softserve.edu.delivery.domain.Order;
import com.softserve.edu.delivery.domain.User;
import com.softserve.edu.delivery.dto.FeedbackDTO;
import com.softserve.edu.delivery.service.FeedbackService;
import com.softserve.edu.delivery.utils.Jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.NoSuchElementException;

/**
 * Created by Ivan Rudnytskyi on 17.09.2016.
 */
public class FeedbackServiceImplTest {

    static EntityManager entityManager;
    static EntityTransaction tx;
    static FeedbackService fsi = null;/*new FeedbackServiceImpl();*/

    public FeedbackServiceImplTest() {
    }

    public FeedbackServiceImplTest(EntityManager entityManager, FeedbackService fsi, EntityTransaction tx) {
        this.entityManager = entityManager;
        this.fsi = fsi;
        this.tx = tx;
    }

    /**
     * @return object of of FeedbackDTO.class
     * <p>
     * creates an object of FeedbackDTO.class - for testing
     */
    static FeedbackDTO createFeedbackDTO() {
        FeedbackDTO feedbackDTO = new FeedbackDTO();
        feedbackDTO.setFeedbackId(getRandomFeedbackId());
        feedbackDTO.setRate(20);
        feedbackDTO.setApproved(true);
        feedbackDTO.setText("text");
        feedbackDTO.setUser(getUser("email0@gmail.com"));
        feedbackDTO.setOrder(getOrder(1L));
        return feedbackDTO;
    }

    /**
     * @return object of of FeedbackDTO.class
     * <p>
     * creates an object of FeedbackDTO.class - for testing. Id is not created, since in some tests
     * it must be missing (save to bd)
     */
    static FeedbackDTO createFeedbackDTOWithoutId() {
        FeedbackDTO feedbackDTO = new FeedbackDTO();
        feedbackDTO.setRate(20);
        feedbackDTO.setApproved(false);
        feedbackDTO.setText("text");
        feedbackDTO.setOrder(getOrder(1L));
        feedbackDTO.setUser(getUser("email0@gmail.com"));

        return feedbackDTO;
    }

    /**
     * @return object of of Feedback.class
     * <p>
     * creates an object of Feedback.class - for testing
     */
    static Feedback createFeedback() {
        Feedback feedback = new Feedback();
        feedback.setFeedbackId(getRandomFeedbackId());
        feedback.setRate(35);
        feedback.setApproved(false);
        feedback.setText("some text");
        feedback.setUser(getUser("email0@gmail.com"));
        feedback.setOrder(getOrder(1L));
        return feedback;
    }

    /**
     * @return count
     * <p>
     * retrieves number of feedback from db
     */
    static Long getFeedbackCount() {
        Long count = new Long(0);
        entityManager = Jpa.getEntityManager();
        try {
            tx = entityManager.getTransaction();
            tx.begin();
            count = (Long) entityManager.createQuery("select count(f) from Feedback f").getSingleResult();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        }

        return count;
    }

    /**
     * retrieves id of the first feedback it the table
     *
     * @return startId
     */
    static Long getStartFeedbackId() {
        Long startId = new Long(0);
        try {
            tx.begin();
            startId = (Long) entityManager.createQuery("select min(f.feedbackId) from Feedback f").getSingleResult();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        }

        return startId;
    }

    /**
     * retrieves id of the last feedback it the table
     *
     * @return endId
     */
    static Long getLastFeedbackId() {
        Long endId = new Long(0);
        try {
            tx.begin();
            endId = (Long) entityManager.createQuery("select max(f.feedbackId) from Feedback f").getSingleResult();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        }

        return endId;
    }

    /**
     * @param feedbackDTO
     * @return feedbackDTO
     * <p>
     * changes data of an object of FeedbackDTO.class and sends it back
     */
    static FeedbackDTO changeData(FeedbackDTO feedbackDTO) {

        feedbackDTO.setApproved(false);
        feedbackDTO.setRate(1000);
        feedbackDTO.setText("changed text");

        return feedbackDTO;
    }

    /**
     * generates random id of a feedback, checking if it exists
     *
     * @return feedback id
     */
    static long getRandomFeedbackId() {

        long feedbackId = 0;
        boolean found = false;
        do {
            feedbackId = (long) (Math.random() * getLastFeedbackId());
            try {
                fsi.getFeedbackById(feedbackId);
                found = true;
            } catch (NoSuchElementException e) {
            }
        } while (!found);

        return feedbackId;

    }

    private static User getUser(String email) {
        UserDao udao = new UserDaoImpl();
        return udao.findOne(email).get();
    }

    private static Order getOrder(Long id) {

        OrderDao odao = new OrderDaoImpl();
        return odao.findOne(id).get();
    }

}
