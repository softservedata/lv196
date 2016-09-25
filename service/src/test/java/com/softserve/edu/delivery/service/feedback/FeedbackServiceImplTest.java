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
import com.softserve.edu.delivery.service.impl.FeedbackServiceImpl;
import com.softserve.edu.delivery.utils.Jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.NoSuchElementException;

/**
 * Created by Ivan Rudnytskyi on 17.09.2016.
 */
final class FeedbackServiceImplTest {

    private static final Long MOCK_FEEDBACK_ID = 1L;
    private static final int MOCK_RATE = 27;
    private static final boolean MOCK_APPROVED = false;
    private static final String MOCK_TEXT = "mock text";
    private static final String MOCK_USER_EMAIL= "email0@gmail.com";
    private static final Long MOCK_ORDER_ID = 2L;
    private static final Long START_ORDER_ID = 2L;
    private static final Long LAST_ORDER_ID = 9L;
    private static final Long LAST_USER_ID = 10L;
    private static final int MAX_FEEDBACK_RATE = 50;
    private static final int MAX_RANDOM_TEXT = 50;

    private static EntityManager entityManager;
    private static EntityTransaction tx;
    private static FeedbackService fsi = new FeedbackServiceImpl();

    public FeedbackServiceImplTest() {
    }

    public FeedbackServiceImplTest(EntityManager entityManager, FeedbackService fsi, EntityTransaction tx) {
        FeedbackServiceImplTest.entityManager = entityManager;
        FeedbackServiceImplTest.fsi = fsi;
        FeedbackServiceImplTest.tx = tx;
    }

    private static String getRandomText() {
        return "feedback text #" +  (int) (Math.random() * MAX_RANDOM_TEXT);
    }

    private static String getRandomUserEmail(){
        return "email" + (int)(Math.random() * LAST_USER_ID) + "@gmail.com";
    }

    private static User getUser(String email) {
        UserDao udao = new UserDaoImpl();
        return udao.findOne(email).get();
    }

    private static Order getOrder(Long id) {
        OrderDao odao = new OrderDaoImpl();
        return odao.findOne(id).get();
    }

    private static int getRandomRate(){
        return (int)(Math.random() * MAX_FEEDBACK_RATE);
    }

    private static boolean getRandomApproved(){
        return (int) (Math.random() * 2) > 0;
    }

    private static long getRandomOrderId(){
        long orderId =(long)(Math.random() * LAST_ORDER_ID) + START_ORDER_ID;
        if (orderId == 5) {
            orderId++;
        }
        return orderId;
    }

    /**
     * @return object of of FeedbackDTO.class
     * 
     * creates an object of FeedbackDTO.class - for testing
     */
    static FeedbackDTO createFeedbackDTO() {
        FeedbackDTO feedbackDTO = new FeedbackDTO();
        feedbackDTO.setFeedbackId(getRandomFeedbackId());
        feedbackDTO.setRate(getRandomRate());
        feedbackDTO.setApproved(getRandomApproved());
        feedbackDTO.setText(getRandomText());
        feedbackDTO.setUser(getUser(getRandomUserEmail()));
        feedbackDTO.setOrder(getOrder(getRandomOrderId()));
        return feedbackDTO;
    }

    /**
     * @return object of of FeedbackDTO.class
     * 
     * creates a mock object of FeedbackDTO.class - for testing
     */
    static FeedbackDTO createMockFeedbackDTO() {
        FeedbackDTO feedbackDTO = new FeedbackDTO();
        feedbackDTO.setFeedbackId(MOCK_FEEDBACK_ID);
        feedbackDTO.setRate(MOCK_RATE);
        feedbackDTO.setApproved(MOCK_APPROVED);
        feedbackDTO.setText(MOCK_TEXT);
        feedbackDTO.setUser(new User().setEmail(MOCK_USER_EMAIL));
        feedbackDTO.setOrder(new Order().setId(MOCK_ORDER_ID));
        return feedbackDTO;
    }

    /**
     * @return object of of FeedbackDTO.class
     * 
     * creates an object of FeedbackDTO.class - for testing. Id is not created, since in some tests
     * it must be missing (save to bd)
     */
    static FeedbackDTO createFeedbackDTOWithoutId() {
        FeedbackDTO feedbackDTO = new FeedbackDTO();
        feedbackDTO.setRate(getRandomRate());
        feedbackDTO.setApproved(getRandomApproved());
        feedbackDTO.setText(getRandomText());
        feedbackDTO.setOrder(getOrder(getRandomOrderId()));
        feedbackDTO.setUser(getUser(getRandomUserEmail()));

        return feedbackDTO;
    }

    /**
     * @return object of of Feedback.class
     * 
     * creates an object of Feedback.class - for testing
     */
    static Feedback createFeedback() {
        Feedback feedback = new Feedback();
        feedback.setFeedbackId(getRandomFeedbackId());
        feedback.setRate(getRandomRate());
        feedback.setApproved(getRandomApproved());
        feedback.setText(getRandomText());
        feedback.setUser(getUser(getRandomUserEmail()));
        feedback.setOrder(getOrder(getRandomOrderId()));
        return feedback;
    }


    /**
     * @return object of of Feedback.class
     * 
     * creates an object of Feedback.class - for testing
     */
    static Feedback createMockFeedback() {
        Feedback feedback = new Feedback();
        feedback.setFeedbackId(MOCK_FEEDBACK_ID);
        feedback.setRate(MOCK_RATE);
        feedback.setApproved(MOCK_APPROVED);
        feedback.setText(MOCK_TEXT);
        feedback.setUser(new User().setEmail(MOCK_USER_EMAIL));
        feedback.setOrder(new Order().setId(MOCK_ORDER_ID));
        return feedback;
    }

    /**
     * @return count
     * 
     * retrieves number of feedback from db
     */
    static Long getFeedbackCount() {
        Long count = 0L;
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
        Long startId = 0L;
        entityManager = Jpa.getEntityManager();
        try {
            tx = entityManager.getTransaction();
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
        Long endId = 0L;
        entityManager = Jpa.getEntityManager();
        try {
            tx = entityManager.getTransaction();
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
     * 
     * changes data of an object of FeedbackDTO.class and sends it back
     */
    static FeedbackDTO changeData(FeedbackDTO feedbackDTO) {

        feedbackDTO.setApproved(getRandomApproved());
        feedbackDTO.setRate(getRandomRate());
        feedbackDTO.setText(getRandomText());
        feedbackDTO.setUser(getUser(getRandomUserEmail()));
        feedbackDTO.setOrder(getOrder(getRandomOrderId()));
        return feedbackDTO;
    }

    /**
     * generates random id of a feedback, checking if it exists
     *
     * @return feedback id
     */
    static long getRandomFeedbackId() {

        long feedbackId;
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

}
