package com.softserve.edu.delivery.service.feedback;

import com.softserve.edu.delivery.domain.Car;
import com.softserve.edu.delivery.domain.Feedback;
import com.softserve.edu.delivery.domain.Offer;
import com.softserve.edu.delivery.domain.Order;
import com.softserve.edu.delivery.domain.Role;
import com.softserve.edu.delivery.domain.User;
import com.softserve.edu.delivery.dto.FeedbackDTO;
import com.softserve.edu.delivery.service.FeedbackService;
import com.softserve.edu.delivery.service.impl.FeedbackServiceImpl;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by Ivan Rudnytskyi on 17.09.2016.
 *
 * The class provides service methods for different test classes
 */
@Component
public class FeedbackServiceImplTest{

    private  final Long MOCK_FEEDBACK_ID = 1L;
    private  final int MOCK_RATE = 27;
    private  final boolean MOCK_APPROVED = false;
    private  final String MOCK_TEXT = "mock text";
    private  final String MOCK_USER_EMAIL= "email0@gmail.com";
    private  final String MOCK_USER_FIRST_NAME= "email0@gmail.com";
    private  final String MOCK_USER_LAST__NAME= "email0@gmail.com";
    private  final String MOCK_CREATED_ON= "2016-10-02 04:15:06";
    private  final Long MOCK_ORDER_ID = 2L;
    private  final Long START_ORDER_ID = 2L;
    private  final Long LAST_ORDER_ID = 9L;
    private  final Long LAST_USER_ID = 10L;
    private  final int MAX_FEEDBACK_RATE = 50;
    private  final int MAX_RANDOM_TEXT = 50;

    private FeedbackService feedbackService;

    private List<User> driverList;
    private List<User> customerList;

    public FeedbackServiceImplTest(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    public void setDriverList(List<User> driverList) {
        this.driverList = driverList;
    }

    public void setCustomerList(List<User> customerList) {
        this.customerList = customerList;
    }

    public List<User> getDriverList() {
        return driverList;
    }

    public List<User> getCustomerList() {
        return customerList;
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
    FeedbackDTO createFeedbackDTO() {
        FeedbackDTO feedbackDTO = new FeedbackDTO();
        feedbackDTO.setFeedbackId(getRandomFeedbackId());
        feedbackDTO.setRate(getRandomRate());
        feedbackDTO.setApproved(getRandomApproved());
        feedbackDTO.setText(getRandomText());
        feedbackDTO.setUserName(feedbackService.getUser(getRandomUserEmail()).getFirstName() + " " +
                feedbackService.getUser(getRandomUserEmail()).getFirstName());
        feedbackDTO.setOrderId(feedbackService.getOrder(getRandomOrderId()).getId());
        return feedbackDTO;
    }

    User createUserForDB() {
        User user = new User();
        user.setEmail(createUserEmail())
                .setFirstName(createUserFirstName())
                .setLastName(createUserLastName())
                .setUserRole(createUserRole());
        return user;
    }

    private  String createUserEmail(){
        long lastUser = getEntriesCount(User.class.getSimpleName());
        return "email" + lastUser + "@gmail.com";
    }

    private  String createUserFirstName(){
        switch ((int)(Math.random() * 5)){
            case 0:
                return "John";
            case 1:
                return "Nancy";
            case 2:
                return "Theodory";
            case 3:
                return "Molly";
            default:
                return "Chris";
        }
    }

    private  String createUserLastName(){
        switch ((int)(Math.random() * 5)){
            case 0:
                return "Jenkins";
            case 1:
                return "Jhonson";
            case 2:
                return "Lincoln";
            case 3:
                return "Petersen";
            default:
                return "Brown";
        }
    }

    private  Role createUserRole(){
        switch ((int)(Math.random() * 5)){
            case 0:
                return Role.DRIVER;
            default:
                return Role.CUSTOMER;
        }
    }

    Car createCarForDB() {
        Car car = new Car();
        car.setDriver(getDriver());
        return car;
    }

    private  User getDriver(){
        long count = driverList.size();
        return driverList.get((int)(Math.random() * count));
}

    Order createOrderForDB(){
        Order order = new Order();
        order.setCustomer(getCustomer());
        return order;

    }

    private  User getCustomer(){
        long count = customerList.size();
        return customerList.get((int)(Math.random() * count));
    }

    Offer createOfferForDB(){
        Offer offer = new Offer();
        offer.setApproved(getApproved())
                .setCar(getCar())
                .setOrder(getOrder());
        return offer;

    }

    private  boolean getApproved(){
        switch ((int)(Math.random() * 2)){
            case 0:
                return true;
            default:
                return false;
        }
    }

    private Car getCar(){
        long startId = feedbackService.getId("select min(c) from Car c");
        return feedbackService.getCar(startId);
    }

    private  Order getOrder(){
        long startId = feedbackService.getId("select min(o) from Order o");
        return feedbackService.getOrder(startId);
    }

    /**
     * @return object of of FeedbackDTO.class
     *
     * creates a mock object of FeedbackDTO.class - for testing
     */
    FeedbackDTO createMockFeedbackDTO() {
        FeedbackDTO feedbackDTO = new FeedbackDTO();
        feedbackDTO.setFeedbackId(MOCK_FEEDBACK_ID);
        feedbackDTO.setRate(MOCK_RATE);
        feedbackDTO.setApproved(MOCK_APPROVED);
        feedbackDTO.setText(MOCK_TEXT);
        feedbackDTO.setUserName(MOCK_USER_FIRST_NAME + " " + MOCK_USER_LAST__NAME);
        feedbackDTO.setOrderId(MOCK_ORDER_ID);
        feedbackDTO.setCreatedOn(MOCK_CREATED_ON);
        return feedbackDTO;
    }

    /**
     * @return object of of FeedbackDTO.class
     *
     * creates an object of FeedbackDTO.class - for testing. Id is not created, since in some tests
     * it must be missing (save to bd)
     */
    FeedbackDTO createFeedbackDTOWithoutId() {
        String randomUserEmail = getRandomUserEmail();
        FeedbackDTO feedbackDTO = new FeedbackDTO();
        feedbackDTO.setRate(getRandomRate());
        feedbackDTO.setApproved(getRandomApproved());
        feedbackDTO.setText(getRandomText());
        feedbackDTO.setOrderId(feedbackService.getOrder(getRandomOrderId()).getId());
        feedbackDTO.setUserName(feedbackService.getUser(randomUserEmail).getFirstName() + " " +
                feedbackService.getUser(randomUserEmail).getLastName());

        return feedbackDTO;
    }

    /**
     * @return object of of Feedback.class
     *
     * creates an object of Feedback.class - for testing
     */
    Feedback createFeedback() {
        Feedback feedback = new Feedback();
        feedback.setFeedbackId(getRandomFeedbackId());
        feedback.setRate(getRandomRate());
        feedback.setApproved(getRandomApproved());
        feedback.setText(getRandomText());
        feedback.setUser(feedbackService.getUser(getRandomUserEmail()));
        feedback.setOrder(feedbackService.getOrder(getRandomOrderId()));
        return feedback;
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
        feedback.setUser(new User().setEmail(MOCK_USER_EMAIL));
        feedback.setOrder(new Order().setId(MOCK_ORDER_ID));
        feedback.setCreatedOn(Timestamp.valueOf(MOCK_CREATED_ON));
        return feedback;
    }

    /**
     * @return object of of User.class
     *
     * creates an object of User.class - for testing
     */
    User createMockUser() {
        User user = new User();
        user.setEmail(MOCK_USER_EMAIL);
        return user;
    }

    /**
     * @return object of of Order.class
     *
     * creates an object of Order.class - for testing
     */
    Order createMockOrder() {
        Order order = new Order();
        order.setId(MOCK_ORDER_ID);
        return order;
    }

    /**
     * @return count
     *
     * retrieves number of feedback from db
     */
    Long getEntriesCount(String entity) {
        return feedbackService.getId("select count(x) from " +  entity + " x");
    }

    /**
     * retrieves id of the first feedback it the table
     *
     * @return startId
     */
    Long getStartFeedbackId() {
        return feedbackService.getId("select min(f.feedbackId) from Feedback f");
    }

    /**
     * retrieves id of the last feedback it the table
     *
     * @return endId
     */
    Long getLastFeedbackId() {
        return feedbackService.getId("select max(f.feedbackId) from Feedback f");
    }

    /**
     * @param feedbackDTO - object of FeedbackDTO.class
     *
     *
     * changes data of an object of FeedbackDTO.class and sends it back
     */
    void  changeData(FeedbackDTO feedbackDTO) {
        String randomUserEmail = getRandomUserEmail();
        feedbackDTO.setApproved(getRandomApproved());
        feedbackDTO.setRate(getRandomRate());
        feedbackDTO.setText(getRandomText());
        feedbackDTO.setUserName(feedbackService.getUser(randomUserEmail).getFirstName() + " " +
                feedbackService.getUser(randomUserEmail).getLastName());
        feedbackDTO.setOrderId(feedbackService.getOrder(getRandomOrderId()).getId());
    }

    /**
     * generates random id of a feedback, checking if it exists
     *
     * @return feedback id
     */
    long getRandomFeedbackId() {

        long feedbackId;
        boolean found = false;
        do {
            feedbackId = (long) (Math.random() * getLastFeedbackId());
            try {
                feedbackService.getFeedbackById(feedbackId);
                found = true;
            } catch (NoSuchElementException e) {
            }
        } while (!found);

        return feedbackId;
    }

}
