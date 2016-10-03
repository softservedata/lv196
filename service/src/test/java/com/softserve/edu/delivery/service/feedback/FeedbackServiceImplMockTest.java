package com.softserve.edu.delivery.service.feedback;

import com.softserve.edu.delivery.dao.*;
import com.softserve.edu.delivery.domain.Feedback;
import com.softserve.edu.delivery.domain.Order;
import com.softserve.edu.delivery.domain.User;
import com.softserve.edu.delivery.dto.FeedbackDTO;
import com.softserve.edu.delivery.service.FeedbackService;

import com.softserve.edu.delivery.service.impl.FeedbackServiceImpl;
import org.mockito.Mockito;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
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
    private final int COUNT = 2;
    private final String APPROVED_DRIVER_NAME = "Approved Driver";

    private final FeedbackDao mockFeedbackDao = Mockito.mock(com.softserve.edu.delivery.dao.FeedbackDao.class);
    private final UserDao mockUserDao = Mockito.mock(com.softserve.edu.delivery.dao.UserDao.class);
    private final OrderDao mockOrderDao = Mockito.mock(com.softserve.edu.delivery.dao.OrderDao.class);

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
    void injectMockDAO() {
        feedbackService = new FeedbackServiceImpl(mockFeedbackDao, mockUserDao, mockOrderDao);
        feedbackServiceImplTest = new FeedbackServiceImplTest(feedbackService);
    }

    @BeforeMethod
    void setUp() {
        User mockUser = feedbackServiceImplTest.createMockUser();
        Order mockOrder = feedbackServiceImplTest.createMockOrder();

        when(mockFeedbackDao.getApprovedDriverName(anyLong())).thenReturn(Optional.of(APPROVED_DRIVER_NAME));
        when(mockUserDao.findOne(anyString())).thenReturn(Optional.of(mockUser));
        when(mockOrderDao.findOne(anyLong())).thenReturn(Optional.of(mockOrder));

        doNothing().when(mockFeedbackDao).save(any(Feedback.class));

    }

    @Test(enabled = true, groups = {"mock"})
    /**
     * tests method from FeedbackServiceImpl.class, which copies fields from an object of Feedback.class
     * to an object of feedbackDTO.class
     */
    public void testCopyFeedbackToDTOTestDB() {
        Feedback feedback = feedbackServiceImplTest.createMockFeedback();

        FeedbackDTO feedbackDTO = feedbackService.copyFeedbackToDTO(feedback);

        Assert.assertTrue(feedback.getFeedbackId().equals(feedbackDTO.getFeedbackId()) &&
                feedback.getOrder().getId().equals(feedbackDTO.getOrderId()) &&
                feedback.getText().equals(feedbackDTO.getText()) &&
                feedback.getUser().getEmail().equals(feedbackDTO.getUserEmail()) &&
                feedbackDTO.getUserName() != null &&
                feedbackDTO.getTransporterName() != null &&
                feedback.getRate().equals(feedbackDTO.getRate()) &&
                feedback.getApproved().equals(feedbackDTO.getApproved()) &&
                feedback.getCreatedOn().toString().equals(feedbackDTO.getCreatedOn()));
    }


    @Test(enabled = true, groups = {"mock"})
    /**
     * tests method from FeedbackServiceImpl.class, which copies fields from an object of FeedbackDTO.class
     * to an object of Feedback.class
     */
    public void testCopyDTOToFeedbackTestDB() {

        FeedbackDTO feedbackDTO = feedbackServiceImplTest.createMockFeedbackDTO();
        Feedback feedback = feedbackService.copyDTOToFeedback(feedbackDTO);

        Assert.assertTrue(feedback.getFeedbackId().equals(feedbackDTO.getFeedbackId()) &&
                feedback.getOrder().getId().equals(feedbackDTO.getOrderId()) &&
                feedback.getText().equals(feedbackDTO.getText()) &&
                feedback.getUser().getEmail().equals(feedbackDTO.getUserEmail()) &&
                feedback.getUser().getFirstName() != null &&
                feedback.getUser().getLastName() != null &&
                feedback.getRate().equals(feedbackDTO.getRate()) &&
                feedback.getApproved().equals(feedbackDTO.getApproved()) &&
                feedback.getCreatedOn().toString().equals(feedbackDTO.getCreatedOn()));
    }

    @Test(enabled = true, groups = {"mock"})
    /**
     *  test for the method from FeedbackServiceImpl.class, which get a list of objects of FeedbackDTO.class
     *
     *  the method check the contents and the size of the list - objects must not be null
     */
    public void testGetAllFeedbacksMock() {

        List<Feedback> feedbackList = new ArrayList<>();

        for (int i = 0; i < COUNT; i++) {
            feedbackList.add(feedbackServiceImplTest.createMockFeedback());
        }

        when(mockFeedbackDao.findAll()).thenReturn(feedbackList);

        List<FeedbackDTO> feedbackDTOList = feedbackService.getAllFeedbacks();

        boolean passed = true;

        for (FeedbackDTO f : feedbackDTOList) {
            if (f == null) {
                passed = false;
            }
        }

        Assert.assertTrue(passed);
        Assert.assertTrue(COUNT == feedbackDTOList.size());

    }


    @Test(enabled = true, groups = {"mock"})
    /**
     *  test for the method from FeedbackServiceImpl.class, which get a list of objects of FeedbackDTO.class
     *  starting from startId, number of objects - startId + count
     *  the method check the contents and the size of the list
     */
    public void testGetAllFeedbacksInRangeMock() {

        Feedback feedback = feedbackServiceImplTest.createMockFeedback();
        List<Feedback> mockFeedbackList = new ArrayList<>();

        for (int i = 0; i < COUNT; i++) {
            mockFeedbackList.add(feedback);
        }

        when(mockFeedbackDao.findAllFeedbacksInRange(anyLong(), anyInt())).thenReturn(mockFeedbackList);

        List<FeedbackDTO> feedbackList = feedbackService.getAllFeedbacksInRange(START_ID, COUNT);

        boolean passed = true;

        for (FeedbackDTO f : feedbackList) {
            if (f == null)
                passed = false;
        }

        Assert.assertTrue(passed);
        Assert.assertTrue(COUNT == feedbackList.size());

    }

    @Test(enabled = true, groups = {"mock"})
    /**
     * tests method from FeedbackServiceImpl.class, which gets an object of FeedbackDTO.class with a given id
     *
     */
    public void testGetFeedbackById() {

        FeedbackDTO feedbackDTO0 = feedbackServiceImplTest.createMockFeedbackDTO();

        Feedback feedback = feedbackService.copyDTOToFeedback(feedbackDTO0);

        when(mockFeedbackDao.findOne(anyLong())).thenReturn(Optional.of(feedback));

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

        Boolean previousStatus = feedbackDTO0.getApproved();

        feedbackDTO0.setApproved(!previousStatus);

        Feedback feedback = feedbackService.copyDTOToFeedback(feedbackDTO0);

        when(mockFeedbackDao.findOne(anyLong())).thenReturn(Optional.of(feedback));

        feedbackService.changeFeedbackStatus(feedbackDTO0.getFeedbackId(), feedbackDTO0.getApproved());

        FeedbackDTO feedbackDTO1 = feedbackService.getFeedbackById(feedbackId);

        Assert.assertFalse(previousStatus.equals(feedbackDTO1.getApproved()));
    }

    @Test(enabled = true, groups = {"mock"})
    /**
     * tests method from FeedbackServiceImpl.class, which saves an object of FeedbackDTO.class with a given id
     * to the db
     */
    public void testSave() {

        FeedbackDTO feedbackDTO0 = feedbackServiceImplTest.createMockFeedbackDTO();

        Optional<Feedback> oFeedback = Optional.of(feedbackServiceImplTest.createMockFeedback());

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

        when(mockFeedbackDao.findOne(anyLong())).thenReturn(oFeedback);

        FeedbackDTO feedbackDTO0 = feedbackService.getFeedbackById(FEEDBACK_ID);

        when(mockFeedbackDao.update(any(Feedback.class))).thenReturn(feedbackServiceImplTest.createMockFeedback());

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

    @Test(enabled = true, groups = {"mock"})
    /**
     * tests method from FeedbackServiceImpl.class, which looks in the db for an object of User.class
     * with a given id
     */
    public void testGetUser() {
        User mockUser0 = feedbackServiceImplTest.createMockUser();
        User mockUser1 = feedbackService.getUser("");
        Assert.assertTrue(mockUser0.getEmail().equals(mockUser1.getEmail()) &&
                mockUser0.getFirstName().equals(mockUser1.getFirstName()) &&
                mockUser0.getLastName().equals(mockUser1.getLastName()));
    }

    @Test(enabled = true, groups = {"mock"})
    /**
     * tests method from FeedbackServiceImpl.class, which looks in the db for an object of Order.class
     * with a given id
     */
    public void testGetOrder() {
        Order mockOrder0 = feedbackServiceImplTest.createMockOrder();
        Order mockOrder1 = feedbackService.getOrder(1L);
        Assert.assertTrue(mockOrder0.getId().equals(mockOrder1.getId()));
    }
}
