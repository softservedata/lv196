package com.softserve.edu.delivery.service;

import com.softserve.edu.delivery.domain.Feedback;
import com.softserve.edu.delivery.domain.Order;
import com.softserve.edu.delivery.dto.FeedbackDTO;
import com.softserve.edu.delivery.service.impl.FeedbackServiceImpl;
import com.softserve.edu.delivery.utils.Jpa;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

/**
 * Created by Ivan Rudnytskyi on 15.09.2016.
 */
public class FeedbackServiceImplTest {
    private EntityManager entityManager = Jpa.getEntityManager();
    private FeedbackService fsi = new FeedbackServiceImpl();
    EntityTransaction tx = entityManager.getTransaction();

    private FeedbackDTO createFeedbackDTO() {
        FeedbackDTO feedbackDTO = new FeedbackDTO();
        feedbackDTO.setFeedbackId(2L);
        feedbackDTO.setRate(20);
        feedbackDTO.setApproved(true);
        feedbackDTO.setText("text");
        return feedbackDTO;
    }

    private FeedbackDTO createFeedbackDTOWithoutId() {
        FeedbackDTO feedbackDTO = new FeedbackDTO();
        feedbackDTO.setRate(20);
        feedbackDTO.setApproved(true);
        feedbackDTO.setText("text");
        return feedbackDTO;
    }


    private Feedback createFeedback() {
        Feedback feedback = new Feedback();
        feedback.setFeedbackId(12L);
        feedback.setRate(35);
        feedback.setApproved(false);
        feedback.setText("some text");
        return feedback;
    }

    private long getFeedbackCount() {
        long count = 0L;
        try {
            tx.begin();
            count = (long) entityManager.createQuery("select count(f) from Feedback f").getSingleResult();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        }

        return count;
    }

    private int getStartFeedbackId() {
        Long startId = new Long(0);
        try {
            tx.begin();
            startId = (Long) entityManager.createQuery("select min(f.feedbackId) from Feedback f").getSingleResult();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        }

        return startId.intValue();
    }

    private long getEndFeedbackId() {
        Long endId = new Long(0);
        try {
            tx.begin();
            endId = (Long) entityManager.createQuery("select min(f.feedbackId) from Feedback f").getSingleResult();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        }

        return endId.longValue();
    }

    private FeedbackDTO changeData(FeedbackDTO feedbackDTO){
        feedbackDTO.setApproved(false);
        feedbackDTO.setRate(1000);
        feedbackDTO.setText("changed text");

        return feedbackDTO;
    }

    @Test(enabled = true)
    public void testCopyFeedbackToDTO() {

        Feedback feedback = createFeedback();

        FeedbackDTO feedbackDTO = fsi.copyFeedbackToDTO(feedback);

        Assert.assertTrue(feedback.getFeedbackId() == feedbackDTO.getFeedbackId() && feedback.getOrder() == feedbackDTO.getOrder()
                && feedback.getRate() == feedbackDTO.getRate() && feedback.getText() == feedbackDTO.getText() &&
                feedback.getUser() == feedbackDTO.getUser());

    }

    @Test(enabled = true)
    public void testCopyDTOToFeedback() {

        FeedbackDTO feedbackDTO = createFeedbackDTO();

        Feedback feedback = fsi.copyDTOToFeedback(feedbackDTO);

        Assert.assertTrue(feedback.getFeedbackId() == feedbackDTO.getFeedbackId() && feedback.getOrder() == feedbackDTO.getOrder()
                && feedback.getRate() == feedbackDTO.getRate() && feedback.getText() == feedbackDTO.getText() &&
                feedback.getUser() == feedbackDTO.getUser());
    }

    @Test (enabled = true)
    public void testGetFeedbackById(){

        long feedbackId = getEndFeedbackId();

        FeedbackDTO feedbackDTO = fsi.getFeedbackById(feedbackId);

        Assert.assertEquals(feedbackId, (long) feedbackDTO.getFeedbackId());
    }

    @Test (enabled = true)
    public void testChangeFeedbackStatus(){

        long feedbackId = getEndFeedbackId();

        FeedbackDTO feedbackDTO = fsi.getFeedbackById(feedbackId);

        boolean previousStatus = feedbackDTO.isApproved();

        feedbackDTO.setApproved(!previousStatus);

        fsi.changeFeedbackStatus(feedbackDTO.getFeedbackId(), !previousStatus);

        feedbackDTO = fsi.getFeedbackById(feedbackId);

        Assert.assertFalse(previousStatus == feedbackDTO.isApproved());
    }

    @Test(enabled = true)
    public void testSave() {

        long former = getFeedbackCount();

        fsi.save(createFeedbackDTOWithoutId());

        long latter = getFeedbackCount();

        Assert.assertTrue((latter - former) == 1);
    }

    @Test(enabled = true)
    public void testUpdate() {

        long feedbackId = getEndFeedbackId();

        FeedbackDTO feedbackDTO0 = fsi.getFeedbackById(feedbackId);
        FeedbackDTO feedbackDTO1 = changeData(feedbackDTO0);

        fsi.update(feedbackDTO1);

        FeedbackDTO feedbackDTO2 = fsi.getFeedbackById(feedbackId);

        Assert.assertTrue(feedbackDTO0.getFeedbackId() == feedbackDTO2.getFeedbackId()
                && feedbackDTO0.getRate() == feedbackDTO2.getRate() && feedbackDTO0.getText() == feedbackDTO2.getText());
    }

    @Test(enabled = true)
    public void testDelete() {

        long feedbackId = getEndFeedbackId();

        FeedbackDTO feedbackDTO = fsi.getFeedbackById(feedbackId);

        fsi.delete(feedbackDTO.getFeedbackId());

        FeedbackDTO feedbackDTO1 = fsi.getFeedbackById(feedbackId);

        Assert.assertNull(feedbackDTO1);
    }

    @Test(enabled = true)
    public void testFindOne() {

        FeedbackDTO feedbackDTO = createFeedbackDTO();

        Assert.assertNotNull(fsi.findOne(feedbackDTO));
    }

    @Test(enabled = true)
    public void testFindAll1() {

        int startId = getStartFeedbackId();

        long count = 5L;

        List<FeedbackDTO> feedbackList = fsi.getAllFeedbacks(startId, (int) count);

        Assert.assertTrue(count == feedbackList.size());

    }

    @Test(enabled = true)
    public void testFindAll2() {

        int startId = getStartFeedbackId();

        long count = getFeedbackCount();

        List<FeedbackDTO> feedbackList = fsi.getAllFeedbacks(startId, (int) count);

        boolean notEmpty = true;

        for (FeedbackDTO f : feedbackList){
            if (f == null || !(f instanceof FeedbackDTO))
                notEmpty = false;
        }

        Assert.assertTrue(notEmpty);

    }




}
