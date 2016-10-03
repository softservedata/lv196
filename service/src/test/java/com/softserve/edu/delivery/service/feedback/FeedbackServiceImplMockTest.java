package com.softserve.edu.delivery.service.feedback;

import com.softserve.edu.delivery.dao.*;
import com.softserve.edu.delivery.domain.Feedback;
import com.softserve.edu.delivery.domain.Order;
import com.softserve.edu.delivery.domain.User;
import com.softserve.edu.delivery.dto.FeedbackDTO;
import com.softserve.edu.delivery.service.FeedbackService;

import com.softserve.edu.delivery.service.impl.FeedbackServiceImpl;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.Mockito.*;


/**
 * Created by Ivan Rudnytskyi on 17.09.2016.
 * <p>
 * the class tests service layer methods. Dao layer is mocked using Mockito.
 */
@Test
public class FeedbackServiceImplMockTest extends AbstractTestNGSpringContextTests {

    private final Long FEEDBACK_ID = 1L;
    private final Long START_ID = 10L;
    private final Long COUNT = 1L;
    private final String APPROVED_DRIVER_NAME = "Approved Driver";

    private final FeedbackDao mockFeedbackDao = Mockito.mock(com.softserve.edu.delivery.dao.FeedbackDao.class);
    private final UserDao mockUserDao = Mockito.mock(com.softserve.edu.delivery.dao.UserDao.class);
    private final CarDao mockCarDao = Mockito.mock(com.softserve.edu.delivery.dao.CarDao.class);
    private final OrderDao mockOrderDao = Mockito.mock(com.softserve.edu.delivery.dao.OrderDao.class);
    private final OfferDao mockOfferDao = Mockito.mock(com.softserve.edu.delivery.dao.OfferDao.class);


    private FeedbackServiceImplTest feedbackServiceImplTest;
    private FeedbackService feedbackService;

    /*
    second group of the tests - using mocks for DAO layer
     */

    @Override
    @BeforeSuite
    protected void springTestContextPrepareTestInstance() throws Exception {
        super.springTestContextPrepareTestInstance();
    }

    @BeforeClass
    public void injectMockDAO() {
        feedbackService = new FeedbackServiceImpl(mockFeedbackDao, mockUserDao, mockCarDao,
                mockOrderDao, mockOfferDao);
        feedbackServiceImplTest = new FeedbackServiceImplTest(feedbackService);
    }

    @Test(enabled = true, groups = {"mock"})
    /**
     *  test#0 for the method from FeedbackServiceImpl.class, which get a list of objects of FeedbackDTO.class
     *  the method checks if the list is not null
     *
     */
    public void testgetAllFeedbacks0Mock() {

        List<Feedback> feedbackList = new ArrayList<>();

        when(mockFeedbackDao.findAll()).thenReturn(feedbackList);

        Assert.assertNotNull(feedbackService.getAllFeedbacks());
    }

    @Test(enabled = true, groups = {"mock"})
    /**
     *  test#1 for the method from FeedbackServiceImpl.class, which get a list of objects of FeedbackDTO.class
     *
     *  the method check the contents of the list - objects must not be null
     */
    public void testgetAllFeedbacks1Mock() {

        List<Feedback> feedbackList = new ArrayList<>();

        feedbackList.add(feedbackServiceImplTest.createMockFeedback());
        feedbackList.add(feedbackServiceImplTest.createMockFeedback());

        when(mockFeedbackDao.findAll()).thenReturn(feedbackList);

        List<FeedbackDTO> feedbackDTOList = feedbackService.getAllFeedbacks();

        boolean passed = true;

        for (FeedbackDTO f : feedbackDTOList) {
            if (f == null) {
                passed = false;
            }
        }

        Assert.assertTrue(passed);
    }


    @Test(enabled = true, groups = {"mock"})
    /**
     *  test#0 for the method from FeedbackServiceImpl.class, which get a list of objects of FeedbackDTO.class
     *  starting from startId, number of objects - startId + count
     *  the method check the size of the list
     */
    public void testGetAllFeedbacksInRange0Mock() {

        Optional<Feedback> oFeedback = Optional.of(feedbackServiceImplTest.createMockFeedback());

        when(mockFeedbackDao.findOne(anyLong())).thenReturn(oFeedback);
        when(mockFeedbackDao.getId(anyString())).thenReturn(COUNT);

        List<FeedbackDTO> feedbackList = feedbackService.getAllFeedbacksInRange(START_ID.intValue(), COUNT.intValue());

        Assert.assertTrue(COUNT == feedbackList.size());

    }

    @Test(enabled = true, groups = {"mock"})
    /**
     *  test#1 for the method from FeedbackServiceImpl.class, which get a list of objects of FeedbackDTO.class
     *  starting from startId, number of objects - startId + count
     *  the method check the contents of the list - objects must not be null
     */
    public void testGetAllFeedbacksInRange1Mock() {
        //creating stub object
        Optional<Feedback> oFeedback = Optional.of(feedbackServiceImplTest.createMockFeedback());

        when(mockFeedbackDao.findOne(anyLong())).thenReturn(oFeedback);

        List<FeedbackDTO> feedbackList = feedbackService.getAllFeedbacksInRange(START_ID.intValue(), COUNT.intValue());

        boolean passed = true;

        for (FeedbackDTO f : feedbackList) {
            if (f == null)
                passed = false;
        }

        Assert.assertTrue(passed);
    }

    @Test(enabled = true, groups = {"mock"})
    /**
     * tests method from FeedbackServiceImpl.class, which gets an object of FeedbackDTO.class with a given id
     *
     */
    public void testGetFeedbackById() {

        FeedbackDTO feedbackDTO0 = feedbackServiceImplTest.createMockFeedbackDTO();
        User mockUser = feedbackServiceImplTest.createMockUser();
        Order mockOrder = feedbackServiceImplTest.createMockOrder();

        Optional<User> oUser = Optional.of(mockUser);
        Optional<Order> oOrder = Optional.of(mockOrder);

        when(mockUserDao.findOne(anyString())).thenReturn(oUser);
        when(mockOrderDao.findOne(anyLong())).thenReturn(oOrder);

        Feedback feedback = feedbackService.copyDTOToFeedback(feedbackDTO0);

        Optional<Feedback> oFeedback = Optional.of(feedback);

        when(mockFeedbackDao.findOne(anyLong())).thenReturn(oFeedback);

        FeedbackDTO feedbackDTO1 = feedbackService.getFeedbackById(FEEDBACK_ID);

        Assert.assertTrue(feedbackDTO0.getFeedbackId().equals(feedbackDTO1.getFeedbackId()) &&
                feedbackDTO0.getText().equals(feedbackDTO1.getText()) &&
                feedbackDTO0.getRate().equals(feedbackDTO1.getRate()) &&
                feedbackDTO0.getApproved().equals(feedbackDTO1.getApproved()));
    }

    @Test(enabled = true, groups = {"mock"})
    /**
     * tests method from FeedbackServiceImpl.class, which changes status of a feedback in the db
     */
    public void testChangeFeedbackStatus() {

        FeedbackDTO feedbackDTO0 = feedbackServiceImplTest.createMockFeedbackDTO();

        long feedbackId = feedbackDTO0.getFeedbackId();

        boolean previousStatus = feedbackDTO0.getApproved();

        feedbackDTO0.setApproved(!previousStatus);

        User mockUser = feedbackServiceImplTest.createMockUser();
        Order mockOrder = feedbackServiceImplTest.createMockOrder();

        Optional<User> oUser = Optional.of(mockUser);
        Optional<Order> oOrder = Optional.of(mockOrder);

        when(mockUserDao.findOne(anyString())).thenReturn(oUser);
        when(mockOrderDao.findOne(anyLong())).thenReturn(oOrder);

        Optional<Feedback> oFeedback = Optional.of(feedbackService.copyDTOToFeedback(feedbackDTO0));

        when(mockFeedbackDao.findOne(anyLong())).thenReturn(oFeedback);

        feedbackService.changeFeedbackStatus(feedbackDTO0.getFeedbackId(), feedbackDTO0.getApproved());

        FeedbackDTO feedbackDTO1 = feedbackService.getFeedbackById(feedbackId);

        Assert.assertFalse(previousStatus == feedbackDTO1.getApproved());
    }

    @Test(enabled = true, groups = {"mock"})
    /**
     * tests method from FeedbackServiceImpl.class, which saves an object of FeedbackDTO.class with a given id
     * to the db
     */
    public void testSave() {

        FeedbackDTO feedbackDTO0 = feedbackServiceImplTest.createMockFeedbackDTO();

        Optional<Feedback> oFeedback = Optional.of(feedbackServiceImplTest.createMockFeedback());

        doNothing().when(mockFeedbackDao).save(any(Feedback.class));
        when(mockFeedbackDao.findOne(anyLong())).thenReturn(oFeedback);

        feedbackService.save(feedbackDTO0);

        long id = feedbackDTO0.getFeedbackId();

        FeedbackDTO feedbackDTO1 = feedbackService.getFeedbackById(id);

        Assert.assertTrue(feedbackDTO0.getFeedbackId().equals(feedbackDTO1.getFeedbackId()) &&
                feedbackDTO0.getText().equals(feedbackDTO1.getText()) &&
                feedbackDTO0.getRate().equals(feedbackDTO1.getRate()) &&
                feedbackDTO0.getApproved().equals(feedbackDTO1.getApproved()));
    }

    @Test(enabled = true, groups = {"mock"})
    /**
     * tests method from FeedbackServiceImpl.class, which updates an object of FeedbackDTO.class with a given id
     * to the db
     */
    public void testUpdate() {

        Optional<Feedback> oFeedback = Optional.of(feedbackServiceImplTest.createMockFeedback());
        Optional<User> oUser = Optional.of(feedbackServiceImplTest.createMockUser());
        Optional<Order> oOrder = Optional.of(feedbackServiceImplTest.createMockOrder());

        when(mockFeedbackDao.findOne(anyLong())).thenReturn(oFeedback);
        when(mockUserDao.findOne(anyString())).thenReturn(oUser);
        when(mockOrderDao.findOne(anyLong())).thenReturn(oOrder);

        FeedbackDTO feedbackDTO0 = feedbackService.getFeedbackById(FEEDBACK_ID);

        when(mockFeedbackDao.update(any(Feedback.class))).thenReturn(feedbackServiceImplTest.createMockFeedback());

        when(mockFeedbackDao.getApprovedDriverName(anyLong())).thenReturn(APPROVED_DRIVER_NAME);

        feedbackServiceImplTest.changeData(feedbackDTO0);

        feedbackService.update(feedbackDTO0);

        FeedbackDTO feedbackDTO1 = feedbackService.getFeedbackById(FEEDBACK_ID);

        Assert.assertFalse(feedbackDTO0.getFeedbackId().equals(feedbackDTO1.getFeedbackId()) &&
                feedbackDTO0.getText().equals(feedbackDTO1.getText()) &&
                feedbackDTO0.getRate().equals(feedbackDTO1.getRate()) &&
                feedbackDTO0.getApproved().equals(feedbackDTO1.getApproved()));
    }

    //priority is set lower, than others, to run the test last - otherwise it throws the exceptions, which
    //causes other tests to fail
    @Test(enabled = true, groups = {"mock"}, expectedExceptions = NoSuchElementException.class, priority = 1)
    /**
     * tests method from FeedbackServiceImpl.class, which deletes an object of FeedbackDTO.class with a given id
     * from the db
     */
    public void testDelete() {

        Feedback feedback = feedbackServiceImplTest.createMockFeedback();

        doNothing().when(mockFeedbackDao).delete(feedback);
        when(mockFeedbackDao.findOne(anyLong())).thenThrow(new NoSuchElementException());

        feedbackService.delete(FEEDBACK_ID);

        feedbackService.getFeedbackById(FEEDBACK_ID);
    }

    @Test(enabled = true, groups = {"mock"})
    /**
     * tests method from FeedbackServiceImpl.class, which looks in the db for an object of FeedbackDTO.class
     * with a given id
     */
    public void testFindOne() {
        Optional<Feedback> oFeedback = Optional.of(feedbackServiceImplTest.createMockFeedback());

        when(mockFeedbackDao.findOne(anyLong())).thenReturn(oFeedback);

        Assert.assertNotNull(feedbackService.findOne(FEEDBACK_ID));
    }
}
