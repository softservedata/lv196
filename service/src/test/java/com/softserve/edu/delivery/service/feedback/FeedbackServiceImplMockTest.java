package com.softserve.edu.delivery.service.feedback;

import com.softserve.edu.delivery.dao.FeedbackDao;
import com.softserve.edu.delivery.domain.Feedback;
import com.softserve.edu.delivery.dto.FeedbackDTO;
import com.softserve.edu.delivery.service.FeedbackService;
import com.softserve.edu.delivery.service.impl.FeedbackServiceImpl;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.*;
import java.util.Optional;

import static org.mockito.Mockito.*;


/**
 * Created by Ivan Rudnytskyi on 17.09.2016.
 */
public class FeedbackServiceImplMockTest {

    private final Long FEEDBACK_ID = 10L;
    private final Long START_ID = 10L;
    private final Long COUNT = 5L;

    private FeedbackDao mockFDao = Mockito.mock(com.softserve.edu.delivery.dao.FeedbackDao.class);
    private FeedbackService fsi;

    /*
    second group of the tests - using mocks for DAO layer
     */

    @BeforeClass
    public void injectMockFeedbackDAO() {
        fsi = new FeedbackServiceImpl(mockFDao);
    }

    @Test(enabled = true, groups = {"mock"})
    /**
     *  test#1 for the method from FeedbackServiceImpl.class, which get a list of objects of FeedbackDTO.class
     *  starting from startId, number of objects - startId + count
     *  the method check the size of the list
     */
    public void testgetAllFeedbacks0TestDB() {

        List<Feedback> feedbackList = new ArrayList<>();

        when(mockFDao.findAll()).thenReturn(feedbackList);

        Assert.assertNotNull(fsi.getAllFeedbacks());
    }

    @Test(enabled = true, groups = {"mock"})
    /**
     *  test#1 for the method from FeedbackServiceImpl.class, which get a list of objects of FeedbackDTO.class
     *  starting from startId, number of objects - startId + count
     *  the method check the contents of the list - objects must not be null and their type must be FeedbackDTO
     */
    public void testgetAllFeedbacks1TestDB() {

        List<Feedback> feedbackList = new ArrayList<>();

        feedbackList.add(FeedbackServiceImplTest.createFeedback());
        feedbackList.add(FeedbackServiceImplTest.createFeedback());

        when(mockFDao.findAll()).thenReturn(feedbackList);

        List<FeedbackDTO> feedbackDTOList = fsi.getAllFeedbacks();

        boolean notEmpty = true;

        for (FeedbackDTO f : feedbackDTOList) {
            if (f == null || !(f instanceof FeedbackDTO))
                notEmpty = false;
        }

        Assert.assertTrue(notEmpty);
    }


    @Test(enabled = true, groups = {"mock"})
    /**
     *  test#1 for the method from FeedbackServiceImpl.class, which get a list of objects of FeedbackDTO.class
     *  starting from startId, number of objects - startId + count
     *  the method check the size of the list
     */
    public void testGetAllFeedbacksInRange0() {
        //creating stub object
        Optional<Feedback> oFeedback = Optional.of(fsi.copyDTOToFeedback(FeedbackServiceImplTest.changeData(FeedbackServiceImplTest.createFeedbackDTO())));

        when(mockFDao.findOne(anyLong())).thenReturn(oFeedback);

        List<FeedbackDTO> feedbackList = fsi.getAllFeedbacksInRange(START_ID.intValue(), COUNT.intValue());

        Assert.assertTrue(COUNT == feedbackList.size());

    }

    @Test(enabled = true, groups = {"mock"})
    /**
     *  test#1 for the method from FeedbackServiceImpl.class, which get a list of objects of FeedbackDTO.class
     *  starting from startId, number of objects - startId + count
     *  the method check the contents of the list - objects must not be null and their type must be FeedbackDTO
     */
    public void testGetAllFeedbacksInRange1() {
        //creating stub object
        Optional<Feedback> oFeedback = Optional.of(fsi.copyDTOToFeedback(FeedbackServiceImplTest.changeData(FeedbackServiceImplTest.createFeedbackDTO())));

        when(mockFDao.findOne(anyLong())).thenReturn(oFeedback);

        List<FeedbackDTO> feedbackList = fsi.getAllFeedbacksInRange(START_ID.intValue(), COUNT.intValue());

        boolean notEmpty = true;

        for (FeedbackDTO f : feedbackList) {
            if (f == null || !(f instanceof FeedbackDTO))
                notEmpty = false;
        }

        Assert.assertTrue(notEmpty);
    }

    @Test(enabled = true, groups = {"mock"})
    /**
     * tests method from FeedbackServiceImpl.class, which gets an object of FeedbackDTO.class with a given id
     *
     */
    public void testGetFeedbackById() {

        FeedbackDTO feedbackDTO0 = FeedbackServiceImplTest.createFeedbackDTO();

        feedbackDTO0.setFeedbackId(FEEDBACK_ID);

        Feedback feedback = fsi.copyDTOToFeedback(feedbackDTO0);

        Optional<Feedback> oFeedback = Optional.of(feedback);

        when(mockFDao.findOne(FEEDBACK_ID)).thenReturn(oFeedback);

        FeedbackDTO feedbackDTO1 = fsi.getFeedbackById(FEEDBACK_ID);

        Assert.assertEquals((long) feedbackDTO0.getFeedbackId(), (long) feedbackDTO1.getFeedbackId());
    }

    @Test(enabled = true, groups = {"mock"})
    /**
     * tests method from FeedbackServiceImpl.class, which changes status of an feedback in the db
     */
    public void testChangeFeedbackStatus() {

        FeedbackDTO feedbackDTO0 = FeedbackServiceImplTest.createFeedbackDTO();

        long feedbackId = feedbackDTO0.getFeedbackId();

        //retrieving actual status
        boolean previousStatus = feedbackDTO0.isApproved();

        //changing the status to an opposite
        feedbackDTO0.setApproved(!previousStatus);

        Optional<Feedback> oFeedback = Optional.of(fsi.copyDTOToFeedback(feedbackDTO0));
        
        when(mockFDao.findOne(anyLong())).thenReturn(oFeedback);

        //updating the status
        fsi.changeFeedbackStatus(feedbackDTO0.getFeedbackId(), feedbackDTO0.isApproved());
        //retrieving the updated object
        FeedbackDTO feedbackDTO1 = fsi.getFeedbackById(feedbackId);

        Assert.assertFalse(previousStatus == feedbackDTO1.isApproved());
    }

    @Test(enabled = true, groups = {"mock"})
    /**
     * tests method from FeedbackServiceImpl.class, which saves an object of FeedbackDTO.class with a given id
     * to the db
     */
    public void testSave() {

        //retrieving previous number of entries in the db
        long former = 10L;

        doNothing().when(mockFDao).save(any(Feedback.class));

        fsi.save(FeedbackServiceImplTest.createFeedbackDTOWithoutId());

        //retrieving number of entries in the db after addind an entry
        long latter = former + 1;

        Assert.assertTrue((latter - former) == 1);
    }

    @Test(enabled = true, groups = {"mock"})
    /**
     * tests method from FeedbackServiceImpl.class, which updates an object of FeedbackDTO.class with a given id
     * to the db
     */
    public void testUpdate() {

        //creating stub object with changed data
        Optional<Feedback> oFeedback = Optional.of(fsi.copyDTOToFeedback(FeedbackServiceImplTest.changeData(FeedbackServiceImplTest.createFeedbackDTO())));

        when(mockFDao.findOne(anyLong())).thenReturn(oFeedback);

        //retrieving an object of FeedbackDTO.class from the db
        FeedbackDTO feedbackDTO0 = fsi.getFeedbackById(FEEDBACK_ID);

        FeedbackServiceImplTest.changeData(feedbackDTO0);

        //updating it
        fsi.update(feedbackDTO0);

        //retrieving the same object of FeedbackDTO.class from the db
        FeedbackDTO feedbackDTO1 = fsi.getFeedbackById(FEEDBACK_ID);

        //comparing fields of the objects
        Assert.assertTrue(feedbackDTO0.getFeedbackId().equals(feedbackDTO1.getFeedbackId()) &&
                feedbackDTO0.getRate().equals(feedbackDTO1.getRate()) &&
                feedbackDTO0.getText().equals(feedbackDTO1.getText()) &&
                feedbackDTO0.isApproved().equals(feedbackDTO1.isApproved()));
    }

    //priority is set lower, than others, to run the test last - otherwise it throws the exceptions, which
    //causes other tests to fail
    @Test(enabled = true, groups = {"mock"}, expectedExceptions = NoSuchElementException.class, priority = 1)
    /**
     * tests method from FeedbackServiceImpl.class, which deletes an object of FeedbackDTO.class with a given id
     * from the db
     */
    public void testDelete() {
        //creating stub object
        Feedback feedback = new Feedback();

        doNothing().when(mockFDao).delete(feedback);
        when(mockFDao.findOne(anyLong())).thenThrow(new NoSuchElementException());

        fsi.delete(FEEDBACK_ID);

        fsi.getFeedbackById(FEEDBACK_ID);
    }

    @Test(enabled = true, groups = {"mock"})
    /**
     * tests method from FeedbackServiceImpl.class, which looks in the db for an object of FeedbackDTO.class
     * with a given id
     */
    public void testFindOne() {
        //creating stub object
        Optional<Feedback> oFeedback = Optional.of(fsi.copyDTOToFeedback(FeedbackServiceImplTest.changeData(FeedbackServiceImplTest.createFeedbackDTO())));

        when(mockFDao.findOne(anyLong())).thenReturn(oFeedback);

        Assert.assertNotNull(fsi.findOne(FEEDBACK_ID));
    }


}
