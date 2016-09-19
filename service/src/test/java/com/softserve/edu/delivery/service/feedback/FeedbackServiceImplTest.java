package com.softserve.edu.delivery.service.feedback;

import com.softserve.edu.delivery.domain.Feedback;
import com.softserve.edu.delivery.dto.FeedbackDTO;
import com.softserve.edu.delivery.service.FeedbackService;
import com.softserve.edu.delivery.service.impl.FeedbackServiceImpl;
import com.softserve.edu.delivery.utils.Jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.NoSuchElementException;

/**
 * Created by Ivan Rudnytskyi on 17.09.2016.
 */
public class FeedbackServiceImplTest {

    static EntityManager entityManager = Jpa.getEntityManager();
    static FeedbackService fsi = new FeedbackServiceImpl();
    static EntityTransaction tx = entityManager.getTransaction();
    ;

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
        feedbackDTO.setFeedbackId(2L);
        feedbackDTO.setRate(20);
        feedbackDTO.setApproved(true);
        feedbackDTO.setText("text");
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
        feedbackDTO.setApproved(true);
        feedbackDTO.setText("text");
        return feedbackDTO;
    }

    /**
     * @return object of of Feedback.class
     * <p>
     * creates an object of Feedback.class - for testing
     */
    static Feedback createFeedback() {
        Feedback feedback = new Feedback();
        feedback.setFeedbackId(12L);
        feedback.setRate(35);
        feedback.setApproved(false);
        feedback.setText("some text");
        return feedback;
    }

    /**
     * @return count
     * <p>
     * retrieves number of feedback from db
     */
    static Long getFeedbackCount() {
        Long count = new Long(0);
        try {
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
                fsi.findOne(feedbackId);
                found = true;
            } catch (NoSuchElementException e) {}
        } while (!found);

        return feedbackId;

    }

}
