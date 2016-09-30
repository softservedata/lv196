package com.softserve.edu.delivery.service.feedback;

import com.softserve.edu.delivery.domain.Feedback;
import com.softserve.edu.delivery.domain.Order;
import com.softserve.edu.delivery.domain.User;
import com.softserve.edu.delivery.dto.FeedbackDTO;
import com.softserve.edu.delivery.service.FeedbackService;
import com.softserve.edu.delivery.service.impl.FeedbackServiceImpl;

import java.util.NoSuchElementException;

/**
 * Created by Ivan Rudnytskyi on 17.09.2016.
 *
 * The class provides service methods for different test classes
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

    private static FeedbackService fsi = FeedbackServiceImpl.getInstance();

    private  FeedbackServiceImplTest() {
    }

    private static String getRandomText() {
        return "feedback text #" +  (int) (Math.random() * MAX_RANDOM_TEXT);
    }

    private static String getRandomUserEmail(){
        return "email" + (int)(Math.random() * LAST_USER_ID) + "@gmail.com";
    }

    /**
     *
     * @param email - id of a User
     * @return user with the id
     */
    private static User getUser(String email) {
        return fsi.getUser(email);
    }

    /**
     *
     * @param id of the Order
     * @return Order with the id
     */
    private static Order getOrder(Long id) {
        return fsi.getOrder(id);
    }

    /**
     *
     * @return random rate
     */
    private static int getRandomRate(){
        return (int)(Math.random() * MAX_FEEDBACK_RATE);
    }

    /**
     *
     * @return random approved status
     */
    private static boolean getRandomApproved(){
        return (int) (Math.random() * 2) > 0;
    }

    /**
     * creates random order ID
     * @return order ID
     */

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
     * @return object of of User.class
     *
     * creates an object of User.class - for testing
     */
    static User createMockUser() {
        User user = new User();
        user.setEmail(MOCK_USER_EMAIL);
        return user;
    }


    /**
     * @return object of of Order.class
     *
     * creates an object of Order.class - for testing
     */
    static Order createMockOrder() {
        Order order = new Order();
        order.setId(MOCK_ORDER_ID);
        return order;
    }

    /**
     * @return count
     *
     * retrieves number of feedback from db
     */
    static Long getFeedbackCount() {
        return fsi.getId("select count(f) from Feedback f");
    }

    /**
     * retrieves id of the first feedback it the table
     *
     * @return startId
     */
    static Long getStartFeedbackId() {
        return fsi.getId("select min(f.feedbackId) from Feedback f");
    }

    /**
     * retrieves id of the last feedback it the table
     *
     * @return endId
     */
    static Long getLastFeedbackId() {
        return fsi.getId("select max(f.feedbackId) from Feedback f");
    }

    /**
     * @param feedbackDTO - object of FeedbackDTO.class
     *
     *
     * changes data of an object of FeedbackDTO.class and sends it back
     */
    static void  changeData(FeedbackDTO feedbackDTO) {
        feedbackDTO.setApproved(getRandomApproved());
        feedbackDTO.setRate(getRandomRate());
        feedbackDTO.setText(getRandomText());
        feedbackDTO.setUser(getUser(getRandomUserEmail()));
        feedbackDTO.setOrder(getOrder(getRandomOrderId()));
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
