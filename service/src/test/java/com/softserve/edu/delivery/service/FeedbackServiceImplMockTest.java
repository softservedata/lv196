package com.softserve.edu.delivery.service;

import com.softserve.edu.delivery.dto.FeedbackDTO;
import com.softserve.edu.delivery.service.impl.FeedbackServiceImpl;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

import static com.softserve.edu.delivery.service.FeedbackServiceImplTest.*;
import static org.mockito.Mockito.*;

/**
 * Created by Ivan Rudnytskyi on 17.09.2016.
 */
public class FeedbackServiceImplMockTest {

    private final Long FEEDBACK_ID = 10L;
    private final Long START_ID = 10L;
    private final Long COUNT = 5L;

    private FeedbackServiceImpl mockFsi = Mockito.mock(com.softserve.edu.delivery.service.impl.FeedbackServiceImpl.class);
    private FeedbackDTO mockFeedbackDTO = Mockito.mock(FeedbackDTO.class);
    private List<FeedbackDTO> listDTO;

    /*
    second group of the tests - using mocks for DAO layer
     */

    @Test(enabled = true, groups = {"mock"})
    /**
     * tests method from FeedbackServiceImpl.class, which gets an object of FeedbackDTO.class with a given id
     *
     */
    public void testGetFeedbackById() {

        FeedbackDTO feedbackDTO0 = createFeedbackDTO();

        feedbackDTO0.setFeedbackId(FEEDBACK_ID);

        when(mockFsi.getFeedbackById(FEEDBACK_ID)).thenReturn(feedbackDTO0);

        FeedbackDTO feedbackDTO1 = mockFsi.getFeedbackById(FEEDBACK_ID);

        Assert.assertEquals((long) feedbackDTO0.getFeedbackId(), (long) feedbackDTO1.getFeedbackId());
    }

    @Test(enabled = true, groups = {"mock"})
    /**
     * tests method from FeedbackServiceImpl.class, which changes status of an feedback in the db
     */
    public void testChangeFeedbackStatus() {

        FeedbackDTO feedbackDTO0 = createFeedbackDTO();
        FeedbackDTO feedbackDTO1 = createFeedbackDTO();

        long feedbackId = feedbackDTO0.getFeedbackId();

        //retrieving actual status
        boolean previousStatus = feedbackDTO0.isApproved();

        //changing the status to an opposite
        feedbackDTO0.setApproved(!previousStatus);
        feedbackDTO1.setApproved(!previousStatus);

        boolean afterUpdateStatus = previousStatus;

        doNothing().when(mockFsi).changeFeedbackStatus(feedbackDTO0.getFeedbackId(),
                !feedbackDTO0.isApproved());

        when(mockFsi.getFeedbackById(feedbackId)).thenReturn(feedbackDTO1);

        //updating the status
        mockFsi.changeFeedbackStatus(feedbackDTO0.getFeedbackId(), feedbackDTO0.isApproved());
        //retrieving the updated object
        feedbackDTO1 = mockFsi.getFeedbackById(feedbackId);

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

        doNothing().when(mockFsi).save(createFeedbackDTOWithoutId());

        mockFsi.save(createFeedbackDTOWithoutId());

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

        when(mockFsi.getFeedbackById(FEEDBACK_ID)).thenReturn(createFeedbackDTO());


        //retrieving an object of FeedbackDTO.class from the db
        FeedbackDTO feedbackDTO0 = mockFsi.getFeedbackById(FEEDBACK_ID);

        doNothing().when(mockFsi).update(feedbackDTO0);

        feedbackDTO0 = changeData(feedbackDTO0);

        //updating it
        mockFsi.update(feedbackDTO0);

        //retrieving the same object of FeedbackDTO.class from the db
        FeedbackDTO feedbackDTO1 = mockFsi.getFeedbackById(FEEDBACK_ID);

        //comparing fields of the objects
        Assert.assertTrue(feedbackDTO0.getFeedbackId() == feedbackDTO1.getFeedbackId()
                && feedbackDTO0.getRate() == feedbackDTO1.getRate() && feedbackDTO0.getText() == feedbackDTO1.getText());
    }

    @Test(enabled = true, groups = {"mock"})
    /**
     * tests method from FeedbackServiceImpl.class, which deletes an object of FeedbackDTO.class with a given id
     * from the db
     */
    public void testDelete() {

        when(mockFsi.getFeedbackById(FEEDBACK_ID)).thenReturn(createFeedbackDTO());
        doNothing().when(mockFsi).delete(FEEDBACK_ID);

        FeedbackDTO feedbackDTO = mockFsi.getFeedbackById(FEEDBACK_ID);

        mockFsi.delete(feedbackDTO.getFeedbackId());

        when(mockFsi.getFeedbackById(FEEDBACK_ID)).thenReturn(null);

        FeedbackDTO feedbackDTO1 = mockFsi.getFeedbackById(FEEDBACK_ID);

        Assert.assertNull(feedbackDTO1);
    }

    @Test(enabled = true, groups = {"mock"})
    /**
     * tests method from FeedbackServiceImpl.class, which looks in the db for an object of FeedbackDTO.class
     * with a given id
     */
    public void testFindOne() {

        when(mockFsi.findOne(FEEDBACK_ID)).thenReturn(createFeedbackDTO());

        Assert.assertNotNull(mockFsi.findOne(FEEDBACK_ID));
    }

    @Test(enabled = true, groups = {"mock"})
    /**
     *  test#1 for the method from FeedbackServiceImpl.class, which get a list of objects of FeedbackDTO.class
     *  starting from startId, number of objects - startId + count
     *  the method check the size of the list
     */
    public void testGetAllFeedbacksInRange0() {

        listDTO = new ArrayList<>();

        for (int i = 0; i < COUNT.intValue(); i++)
            listDTO.add(mockFeedbackDTO);

        when(mockFsi.getAllFeedbacksInRange(START_ID.intValue(), COUNT.intValue())).thenReturn(listDTO);

        List<FeedbackDTO> feedbackList = mockFsi.getAllFeedbacksInRange(START_ID.intValue(), COUNT.intValue());

        Assert.assertTrue(COUNT == feedbackList.size());

    }

    @Test(enabled = true, groups = {"mock"})
    /**
     *  test#1 for the method from FeedbackServiceImpl.class, which get a list of objects of FeedbackDTO.class
     *  starting from startId, number of objects - startId + count
     *  the method check the contents of the list - objects must not be null and their type must be FeedbackDTO
     */
    public void testGetAllFeedbacksInRange1() {

        listDTO = new ArrayList<>();

        for (int i = 0; i < COUNT.intValue(); i++)
            listDTO.add(mockFeedbackDTO);

        when(mockFsi.getAllFeedbacksInRange(START_ID.intValue(), COUNT.intValue())).thenReturn(listDTO);

        List<FeedbackDTO> feedbackList = mockFsi.getAllFeedbacksInRange(START_ID.intValue(), COUNT.intValue());

        boolean notEmpty = true;

        for (FeedbackDTO f : feedbackList) {
            if (f == null || !(f instanceof FeedbackDTO))
                notEmpty = false;
        }

        Assert.assertTrue(notEmpty);

    }

}
