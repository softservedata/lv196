package com.softserve.edu.delivery.service.feedback;

import com.softserve.edu.delivery.domain.*;
import com.softserve.edu.delivery.dto.FeedbackDTO;
import com.softserve.edu.delivery.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by Ivan Rudnytskyi on 15.09.2016.
 * <p>
 * test the methods of FeedbackServiceImpl.class
 */

public class FeedbackServiceImplDBTest extends AbstractTestNGSpringContextTests {

    private static final long REQUIRED_NUMBERS_OF_FEEDBACKS = 20;
    private static final long REQUIRED_NUMBERS_OF_USERS = 10;
    private static final long REQUIRED_NUMBERS_OF_CARS = 10;
    private static final long REQUIRED_NUMBERS_OF_ORDERS = REQUIRED_NUMBERS_OF_FEEDBACKS;
    private static final long REQUIRED_NUMBERS_OF_OFFERS = REQUIRED_NUMBERS_OF_ORDERS * 2;
    private static final int COUNT = 12;


    private FeedbackService feedbackService;
    private FeedbackServiceImplTest feedbackServiceImplTest;

    public FeedbackServiceImplDBTest() {
    }

    @Autowired
    public FeedbackServiceImplDBTest(FeedbackService feedbackService, FeedbackServiceImplTest feedbackServiceImplTest) {
        this.feedbackService = feedbackService;
        this.feedbackServiceImplTest = feedbackServiceImplTest;
    }

    @Override
    @BeforeSuite
    protected void springTestContextPrepareTestInstance() throws Exception {
        super.springTestContextPrepareTestInstance();
    }

    @BeforeTest
    /**
     * checks, if table Feedback has enough entries, if not - populates it with the required number of feedbacks
     */
    private void checkIfTableHasEntries() {
        addFeedbacksToDB();
        addUsersToDB();
        addCarsToDB();
        addOrdersToDB();
        addOffersToDB();
    }

    private void addFeedbacksToDB() {
        Long count = feedbackServiceImplTest.getEntriesCount(Feedback.class.getSimpleName());

        if (count < REQUIRED_NUMBERS_OF_FEEDBACKS) {
            for (int i = 0; i < (REQUIRED_NUMBERS_OF_FEEDBACKS - count); i++) {
                feedbackService.save(feedbackServiceImplTest.createFeedbackDTOWithoutId());
            }
        }
    }

    private void addUsersToDB() {
        Long count = feedbackServiceImplTest.getEntriesCount(User.class.getSimpleName());

        if (count < REQUIRED_NUMBERS_OF_USERS) {
            for (int i = 0; i < (REQUIRED_NUMBERS_OF_USERS - count); i++) {
                feedbackService.saveUser(feedbackServiceImplTest.createUserForDB());
            }
        }

        feedbackServiceImplTest.setDriverList(feedbackService.getUsersByRole(Role.DRIVER.getName()));
        feedbackServiceImplTest.setCustomerList(feedbackService.getUsersByRole(Role.CUSTOMER.getName()));
    }

    private void addCarsToDB() {
        Long count = feedbackServiceImplTest.getEntriesCount(Car.class.getSimpleName());

        if (count < REQUIRED_NUMBERS_OF_CARS) {
            for (int i = 0; i < (REQUIRED_NUMBERS_OF_CARS - count); i++) {
                feedbackService.saveCar(feedbackServiceImplTest.createCarForDB());
            }
        }
    }

    private void addOrdersToDB() {
        Long count = feedbackServiceImplTest.getEntriesCount(Order.class.getSimpleName());

        if (count < REQUIRED_NUMBERS_OF_ORDERS) {
            for (int i = 0; i < (REQUIRED_NUMBERS_OF_ORDERS - count); i++) {
                feedbackService.saveOrder(feedbackServiceImplTest.createOrderForDB());
            }
        }
    }

    private void addOffersToDB() {
        Long count = feedbackServiceImplTest.getEntriesCount(Offer.class.getSimpleName());

        if (count < REQUIRED_NUMBERS_OF_OFFERS) {
            for (int i = 0; i < (REQUIRED_NUMBERS_OF_OFFERS - count); i++) {
                feedbackService.saveOffer(feedbackServiceImplTest.createOfferForDB());
            }
        }
    }


    /*
        first group of tests - on a real test db, included in the group testDB
     */

    @Test(enabled = false, groups = {"testDB"})
    /**
     * tests method from FeedbackServiceImpl.class, which copies fields from an object of Feedback.class
     * to an object of feedbackDTO.class
     */
    public void testCopyFeedbackToDTOTestDB() {

        Feedback feedback = feedbackServiceImplTest.createFeedback();

        FeedbackDTO feedbackDTO = feedbackService.copyFeedbackToDTO(feedback);

        Assert.assertTrue(feedback.getFeedbackId().equals(feedbackDTO.getFeedbackId()) &&
                feedback.getOrder().equals(feedbackDTO.getOrder()) &&
                feedback.getText().equals(feedbackDTO.getText()) &&
                feedback.getUser().equals(feedbackDTO.getUser()) &&
                feedback.getRate().equals(feedbackDTO.getRate()) &&
                feedback.getApproved().equals(feedbackDTO.getApproved()));

    }

    @Test(enabled = false, groups = {"testDB"})
    /**
     * tests method from FeedbackServiceImpl.class, which copies fields from an object of FeedbackDTO.class
     * to an object of Feedback.class
     */
    public void testCopyDTOToFeedbackTestDB() {

        FeedbackDTO feedbackDTO = feedbackServiceImplTest.createFeedbackDTO();

        Feedback feedback = feedbackService.copyDTOToFeedback(feedbackDTO);

        Assert.assertTrue(feedback.getFeedbackId().equals(feedbackDTO.getFeedbackId()) &&
                feedback.getOrder().equals(feedbackDTO.getOrder()) &&
                feedback.getText().equals(feedbackDTO.getText()) &&
                feedback.getUser().equals(feedbackDTO.getUser()) &&
                feedback.getRate().equals(feedbackDTO.getRate()) &&
                feedback.getApproved().equals(feedbackDTO.getApproved()));
    }

    @Test(enabled = false, groups = {"testDB"})
    /**
     *  test#0 for the method from FeedbackServiceImpl.class, which get a list of objects of FeedbackDTO.class
     *
     *  the method check the size of the list
     */
    public void testGetAllFeedbacks0TestDB() {

        long count = feedbackServiceImplTest.getEntriesCount(Feedback.class.getSimpleName());

        List<FeedbackDTO> feedbackList = feedbackService.getAllFeedbacks();

        Assert.assertTrue(count == feedbackList.size());

    }

    @Test(enabled = false, groups = {"testDB"})
    /**
     *  test#1 for the method from FeedbackServiceImpl.class, which get a list of objects of FeedbackDTO.class
     *
     *  the method check the contents of the list - objects must not be null
     */
    public void testGetAllFeedbacks1TestDB() {

        List<FeedbackDTO> feedbackList = feedbackService.getAllFeedbacks();

        boolean passed = true;

        for (FeedbackDTO f : feedbackList) {
            if (f == null) {
                passed = false;
            }
        }

        Assert.assertTrue(passed);
    }

    @Test(enabled = false, groups = {"testDB"})
    /**
     *  test#1 for the method from FeedbackServiceImpl.class, which get a list of objects of FeedbackDTO.class
     *  starting from startId, number of objects - startId + count
     *  the method check the size of the list
     */
    public void testgetAllFeedbacksInRange0TestDB() {

        Long startId = feedbackServiceImplTest.getStartFeedbackId();

        List<FeedbackDTO> feedbackList = feedbackService.getAllFeedbacksInRange(startId, COUNT);

        Assert.assertTrue(COUNT == feedbackList.size());
    }

    @Test(enabled = false, groups = {"testDB"})
    /**
     *  test#1 for the method from FeedbackServiceImpl.class, which get a list of objects of FeedbackDTO.class
     *  starting from startId, number of objects - startId + count
     *  the method check the contents of the list - objects must not be null and their type must be FeedbackDTO
     */
    public void testgetAllFeedbacksInRange1TestDB() {

        Long startId = feedbackServiceImplTest.getStartFeedbackId();

        List<FeedbackDTO> feedbackList = feedbackService.getAllFeedbacksInRange(startId, COUNT);

        boolean passed = true;

        for (FeedbackDTO f : feedbackList) {
            if (f == null) {
                passed = false;
            }
        }

        Assert.assertTrue(passed);
    }


    @Test(enabled = false, groups = {"testDB"})
    /**
     * tests method from FeedbackServiceImpl.class, which gets an object of FeedbackDTO.class with a given id
     *
     */
    public void testGetFeedbackByIdTestDB() {

        long feedbackId = feedbackServiceImplTest.getRandomFeedbackId();

        FeedbackDTO feedbackDTO = feedbackService.getFeedbackById(feedbackId);

        Assert.assertEquals(feedbackId, (long) feedbackDTO.getFeedbackId());
    }

    @Test(enabled = false, groups = {"testDB"})
    /**
     * tests method from FeedbackServiceImpl.class, which changes status of an feedback in the db
     */
    public void testChangeFeedbackStatusTestDB() {

        long feedbackId = feedbackServiceImplTest.getRandomFeedbackId();

        FeedbackDTO feedbackDTO = feedbackService.getFeedbackById(feedbackId);

        boolean previousStatus = feedbackDTO.getApproved();

        feedbackDTO.setApproved(!previousStatus);

        feedbackService.changeFeedbackStatus(feedbackDTO.getFeedbackId(), !previousStatus);

        feedbackDTO = feedbackService.getFeedbackById(feedbackId);

        Assert.assertFalse(previousStatus == feedbackDTO.getApproved());
    }

    @Test(enabled = false, groups = {"testDB"})
    /**
     * tests method from FeedbackServiceImpl.class, which saves an object of FeedbackDTO.class with a given id
     * to the db
     */
    public void testSaveTestDB() {

        FeedbackDTO feedbackDTO0 = feedbackServiceImplTest.createFeedbackDTOWithoutId();

        feedbackService.save(feedbackDTO0);

        FeedbackDTO feedbackDTO1 = feedbackService.getFeedbackById(feedbackServiceImplTest.getLastFeedbackId());

        //comparison of id is omitted - since in non-persistent object it is missing
        Assert.assertTrue(feedbackDTO0.getOrder().equals(feedbackDTO1.getOrder()) &&
                feedbackDTO0.getText().equals(feedbackDTO1.getText()) &&
                feedbackDTO0.getUser().equals(feedbackDTO1.getUser()) &&
                feedbackDTO0.getRate().equals(feedbackDTO1.getRate()) &&
                feedbackDTO0.getApproved().equals(feedbackDTO1.getApproved()));
    }

    @Test(enabled = false, groups = {"testDB"})
    /**
     * tests method from FeedbackServiceImpl.class, which updates an object of FeedbackDTO.class with a given id
     * to the db
     */
    public void testUpdateTestDB() {

        long feedbackId = feedbackServiceImplTest.getRandomFeedbackId();

        FeedbackDTO feedbackDTO0 = feedbackService.getFeedbackById(feedbackId);

        feedbackServiceImplTest.changeData(feedbackDTO0);

        feedbackService.update(feedbackDTO0);

        FeedbackDTO feedbackDTO1 = feedbackService.getFeedbackById(feedbackId);

        Assert.assertTrue(feedbackDTO0.getFeedbackId().equals(feedbackDTO1.getFeedbackId()) &&
                feedbackDTO0.getOrder().equals(feedbackDTO1.getOrder()) &&
                feedbackDTO0.getText().equals(feedbackDTO1.getText()) &&
                feedbackDTO0.getUser().equals(feedbackDTO1.getUser()) &&
                feedbackDTO0.getRate().equals(feedbackDTO1.getRate()) &&
                feedbackDTO0.getApproved().equals(feedbackDTO1.getApproved()));
    }

    @Test(enabled = false, groups = {"testDB"}, expectedExceptions = NoSuchElementException.class)
    /**
     * tests method from FeedbackServiceImpl.class, which deletes an object of FeedbackDTO.class with a given id
     * from the db
     */
    public void testDeleteTestDB() {

        long feedbackId = feedbackServiceImplTest.getRandomFeedbackId();

        FeedbackDTO feedbackDTO = feedbackService.getFeedbackById(feedbackId);

        feedbackService.delete(feedbackDTO.getFeedbackId());

        feedbackService.getFeedbackById(feedbackId);
    }

    @Test(enabled = false, groups = {"testDB"})
    /**
     * tests method from FeedbackServiceImpl.class, which looks in the db for an object of FeedbackDTO.class
     * with a given id
     */
    public void testFindOneTestDB() {

        long feedbackId = feedbackServiceImplTest.getRandomFeedbackId();

        Assert.assertNotNull(feedbackService.findOne(feedbackId));
    }
}

