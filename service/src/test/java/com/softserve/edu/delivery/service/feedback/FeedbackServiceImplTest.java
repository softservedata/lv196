package com.softserve.edu.delivery.service.feedback;

import com.softserve.edu.delivery.domain.Feedback;
import com.softserve.edu.delivery.domain.Order;
import com.softserve.edu.delivery.domain.User;
import com.softserve.edu.delivery.dto.FeedbackDto;
import com.softserve.edu.delivery.dto.FeedbackFilterDTO;
import com.softserve.edu.delivery.service.FeedbackService;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * Created by Ivan Rudnytskyi on 17.09.2016.
 *
 * The class provides service methods for different test classes
 */
@Component
public class FeedbackServiceImplTest{

    private final Long MOCK_FEEDBACK_ID = 1L;
    private final int MOCK_RATE = 27;
    private final boolean MOCK_APPROVED = false;
    private final String MOCK_TEXT = "mock text";
    private final String MOCK_USER_EMAIL = "emai0@gmail.com";
    private final String MOCK_USER_FIRST_NAME = "Firstname";
    private final String MOCK_USER_LAST__NAME = "Lastname";
    private final String MOCK_APPROVED_DRIVER_NAME = "Approved Driver";
    private final String MOCK_APPROVED_DRIVER_EMAIL = "email0@gmail.com";
    private final String MOCK_SORT_BY = "createdOn";
    private final String MOCK_SORT_ORDER = "desc";
    private final Timestamp MOCK_CREATED_ON = Timestamp.valueOf("2016-10-02 04:15:06");
    private final Long MOCK_ORDER_ID = 1L;
    private final Long START_ORDER_ID = 1L;
    private final Long LAST_ORDER_ID = 2L;
    private final Long LAST_USER_ID = 2L;
    private final int MAX_FEEDBACK_RATE = 50;
    private final int MAX_RANDOM_TEXT = 50;
    private final int MOCK_CURRENT_PAGE = 1;
    private final int MOCK_ITEMS_PER_PAGE = 5;

    private final FeedbackService feedbackService;

    public FeedbackServiceImplTest(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    private String getRandomText() {
        return "feedback text #" +  (int) (Math.random() * MAX_RANDOM_TEXT);
    }

    private  String getRandomUserEmail(){
        return "email" + (int)(Math.random() * LAST_USER_ID) + "@gmail.com";
    }

    /**
     *
     * @return random rate
     */
    private  int getRandomRate(){
        return (int)(Math.random() * MAX_FEEDBACK_RATE);
    }

    /**
     *
     * @return random approved status
     */
    private  boolean getRandomApproved(){
        return (int) (Math.random() * 2) > 0;
    }

    /**
     * creates random order ID
     * @return order ID
     */

    private  long getRandomOrderId(){
        return (long)(Math.random() * LAST_ORDER_ID) + START_ORDER_ID;
    }

    /**
     * @return object of of FeedbackDTO.class
     *
     * creates a mock object of FeedbackDTO.class - for testing
     */
    FeedbackDto createMockFeedbackDTO() {
        FeedbackDto feedbackDTO = new FeedbackDto();
        feedbackDTO.setFeedbackId(MOCK_FEEDBACK_ID);
        feedbackDTO.setRate(MOCK_RATE);
        feedbackDTO.setApproved(MOCK_APPROVED);
        feedbackDTO.setText(MOCK_TEXT);
        feedbackDTO.setUserEmail(MOCK_USER_EMAIL);
        feedbackDTO.setUserName(MOCK_USER_FIRST_NAME + " " + MOCK_USER_LAST__NAME);
        feedbackDTO.setTransporterName(MOCK_APPROVED_DRIVER_NAME);
        feedbackDTO.setTransporterEmail(MOCK_APPROVED_DRIVER_EMAIL);
        feedbackDTO.setOrderId(MOCK_ORDER_ID);
        feedbackDTO.setCreatedOn(MOCK_CREATED_ON);
        return feedbackDTO;
    }

    /**
     * @return object of of Feedback.class
     *
     * creates an object of Feedback.class - for testing
     */
    Feedback createMockFeedback() {
        Feedback feedback = new Feedback();
        feedback.setFeedbackId(MOCK_FEEDBACK_ID);
        feedback.setRate(MOCK_RATE);
        feedback.setApproved(MOCK_APPROVED);
        feedback.setText(MOCK_TEXT);
        feedback.setUser(createMockUser());
        feedback.setOrder(createMockOrder());
        feedback.setCreatedOn(MOCK_CREATED_ON);
        return feedback;
    }

    /**
     * @return object of of User.class
     *
     * creates an object of User.class - for testing
     */
    User createMockUser() {
        return new User()
                .setEmail(MOCK_USER_EMAIL)
                .setFirstName(MOCK_USER_FIRST_NAME)
                .setLastName(MOCK_USER_LAST__NAME);
    }

    /**
     * @return object of of Order.class
     *
     * creates an object of Order.class - for testing
     */
    Order createMockOrder() {
        return new Order()
                .setId(MOCK_ORDER_ID);
    }

    FeedbackFilterDTO createMockFeedbackFilterDTO (){
        FeedbackFilterDTO feedbackFilterDTO = new FeedbackFilterDTO();
        feedbackFilterDTO.setText(MOCK_TEXT);
        feedbackFilterDTO.setRate(Integer.toString(MOCK_RATE));
        feedbackFilterDTO.setUserName(MOCK_USER_FIRST_NAME + MOCK_USER_LAST__NAME);
        feedbackFilterDTO.setTransporterName(MOCK_APPROVED_DRIVER_NAME);
        feedbackFilterDTO.setCreatedOn(MOCK_CREATED_ON.toString());
        feedbackFilterDTO.setApproved(Boolean.toString(MOCK_APPROVED));
        feedbackFilterDTO.setSortBy(MOCK_SORT_BY);
        feedbackFilterDTO.setSortOrder(MOCK_SORT_ORDER);
        feedbackFilterDTO.setCurrentPage(MOCK_CURRENT_PAGE);
        feedbackFilterDTO.setItemsPerPage(MOCK_ITEMS_PER_PAGE);

        return feedbackFilterDTO;
    }

     /**
     * @param feedbackDTO - object of FeedbackDTO.class
     *
     *
     * changes data of an object of FeedbackDTO.class and sends it back
     */
    void  changeData(FeedbackDto feedbackDTO) {
        String randomUserEmail = getRandomUserEmail();
        feedbackDTO.setApproved(getRandomApproved());
        feedbackDTO.setRate(getRandomRate());
        feedbackDTO.setText(getRandomText());
        feedbackDTO.setUserName(feedbackService.getUser(randomUserEmail).getFirstName() + " " +
                feedbackService.getUser(randomUserEmail).getLastName());
        feedbackDTO.setOrderId(feedbackService.getOrder(getRandomOrderId()).getId());
    }
}
