package com.softserve.edu.delivery.service.feedback;

import com.softserve.edu.delivery.domain.Feedback;
import com.softserve.edu.delivery.dto.FeedbackDTO;
import com.softserve.edu.delivery.service.FeedbackService;
import com.softserve.edu.delivery.service.impl.FeedbackServiceImpl;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static com.softserve.edu.delivery.service.feedback.FeedbackServiceImplTest.*;

/**
 * Created by Ivan Rudnytskyi on 15.09.2016.
 *
 * test the methods of FeedbackServiceImpl.class
 */
public class FeedbackServiceImplTestDBTest {

    private static final long REQUIRED_NUMBERS_OF_FEEDBACKS = 100;
    private static final int COUNT = 12;

    private final FeedbackService fsi = new FeedbackServiceImpl();

    @BeforeTest
    /**
     * checks, if table Feedback has enough entries, if not - populates it with the required number of feedbacks
     */
    private void checkIfTableHasEntries() {

        Long feedbackCount = getFeedbackCount();

        if (feedbackCount < REQUIRED_NUMBERS_OF_FEEDBACKS)
            for (int i = 0; i < (REQUIRED_NUMBERS_OF_FEEDBACKS - feedbackCount); i++)
                fsi.save(createFeedbackDTOWithoutId());
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

        Feedback feedback = createFeedback();

        FeedbackDTO feedbackDTO = fsi.copyFeedbackToDTO(feedback);

        Assert.assertTrue(feedback.getFeedbackId().equals(feedbackDTO.getFeedbackId()) &&
                feedback.getOrder().equals(feedbackDTO.getOrder()) &&
                feedback.getText().equals(feedbackDTO.getText()) &&
                feedback.getUser().equals(feedbackDTO.getUser()) &&
                feedback.getRate().equals(feedbackDTO.getRate()) &&
                feedback.isApproved().equals(feedbackDTO.getApproved()));

    }

    @Test(enabled = false, groups = {"testDB"})
    /**
     * tests method from FeedbackServiceImpl.class, which copies fields from an object of FeedbackDTO.class
     * to an object of Feedbac.class
     */
    public void testCopyDTOToFeedbackTestDB() {

        FeedbackDTO feedbackDTO = createFeedbackDTO();

        Feedback feedback = fsi.copyDTOToFeedback(feedbackDTO);

        Assert.assertTrue(feedback.getFeedbackId().equals(feedbackDTO.getFeedbackId()) &&
                feedback.getOrder().equals(feedbackDTO.getOrder()) &&
                feedback.getText().equals(feedbackDTO.getText()) &&
                feedback.getUser().equals(feedbackDTO.getUser()) &&
                feedback.getRate().equals(feedbackDTO.getRate()) &&
                feedback.isApproved().equals(feedbackDTO.getApproved()));
    }

    @Test(enabled = false, groups = {"testDB"})
    /**
     *  test#1 for the method from FeedbackServiceImpl.class, which get a list of objects of FeedbackDTO.class
     *  starting from startId, number of objects - startId + count
     *  the method check the size of the list
     */
    public void testgetAllFeedbacks0TestDB() {

        long count = getFeedbackCount();

        List<FeedbackDTO> feedbackList = fsi.getAllFeedbacks();

        Assert.assertTrue(count == feedbackList.size());

    }

    @Test(enabled = false, groups = {"testDB"})
    /**
     *  test#1 for the method from FeedbackServiceImpl.class, which get a list of objects of FeedbackDTO.class
     *  starting from startId, number of objects - startId + count
     *  the method check the contents of the list - objects must not be null and their type must be FeedbackDTO
     */
    public void testgetAllFeedbacks1TestDB() {

        List<FeedbackDTO> feedbackList = fsi.getAllFeedbacks();

        boolean passed = true;

        for (FeedbackDTO f : feedbackList) {
            if (f == null)
                passed = false;
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

        Long startId = getStartFeedbackId();

        List<FeedbackDTO> feedbackList = fsi.getAllFeedbacksInRange(startId, COUNT);

        Assert.assertTrue(COUNT == feedbackList.size());
    }

    @Test(enabled = false, groups = {"testDB"})
    /**
     *  test#1 for the method from FeedbackServiceImpl.class, which get a list of objects of FeedbackDTO.class
     *  starting from startId, number of objects - startId + count
     *  the method check the contents of the list - objects must not be null and their type must be FeedbackDTO
     */
    public void testgetAllFeedbacksInRange1TestDB() {

        Long startId = getStartFeedbackId();

        List<FeedbackDTO> feedbackList = fsi.getAllFeedbacksInRange(startId, COUNT);

        boolean passed = true;

        for (FeedbackDTO f : feedbackList) {
            if (f == null)
                passed = false;
        }

        Assert.assertTrue(passed);
    }


    @Test(enabled = false, groups = {"testDB"})
    /**
     * tests method from FeedbackServiceImpl.class, which gets an object of FeedbackDTO.class with a given id
     *
     */
    public void testGetFeedbackByIdTestDB() {

        long feedbackId = getRandomFeedbackId();

        FeedbackDTO feedbackDTO = fsi.getFeedbackById(feedbackId);

        Assert.assertEquals(feedbackId, (long) feedbackDTO.getFeedbackId());
    }

    @Test(enabled = false, groups = {"testDB"})
    /**
     * tests method from FeedbackServiceImpl.class, which changes status of an feedback in the db
     */
    public void testChangeFeedbackStatusTestDB() {

        long feedbackId = getRandomFeedbackId();

        FeedbackDTO feedbackDTO = fsi.getFeedbackById(feedbackId);

        //retrieving actual status
        boolean previousStatus = feedbackDTO.getApproved();
        //changing the status to an opposite
        feedbackDTO.setApproved(!previousStatus);
        //updating the status
        fsi.changeFeedbackStatus(feedbackDTO.getFeedbackId(), !previousStatus);
        //retrieving the updated object
        feedbackDTO = fsi.getFeedbackById(feedbackId);

        Assert.assertFalse(previousStatus == feedbackDTO.getApproved());
    }

    @Test(enabled = false, groups = {"testDB"})
    /**
     * tests method from FeedbackServiceImpl.class, which saves an object of FeedbackDTO.class with a given id
     * to the db
     */
    public void testSaveTestDB() {

        FeedbackDTO feedbackDTO0 = createFeedbackDTOWithoutId();

        fsi.save(feedbackDTO0);

        //retrieving number of entries in the db after adding an entry
        FeedbackDTO feedbackDTO1 = fsi.getFeedbackById(getLastFeedbackId());

        //comparation of id is omitted - since in non-persistent object it is missing
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

        long feedbackId = 1L;//getRandomFeedbackId();

        //retrieving an object of FeedbackDTO.class from the db
        FeedbackDTO feedbackDTO0 = fsi.getFeedbackById(feedbackId);

        feedbackDTO0 = changeData(feedbackDTO0);

        //updating it
        fsi.update(feedbackDTO0);

        //retrieving the same object of FeedbackDTO.class from the db
        FeedbackDTO feedbackDTO1 = fsi.getFeedbackById(feedbackId);

        //comparing fields of the objects
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

        long feedbackId = FeedbackServiceImplTest.getRandomFeedbackId();

        FeedbackDTO feedbackDTO = fsi.getFeedbackById(feedbackId);

        fsi.delete(feedbackDTO.getFeedbackId());

        fsi.getFeedbackById(feedbackId);
    }

    @Test(enabled = false, groups = {"testDB"})
    /**
     * tests method from FeedbackServiceImpl.class, which looks in the db for an object of FeedbackDTO.class
     * with a given id
     */
    public void testFindOneTestDB() {

        long feedbackId = getRandomFeedbackId();

        Assert.assertNotNull(fsi.findOne(feedbackId));
    }
}

