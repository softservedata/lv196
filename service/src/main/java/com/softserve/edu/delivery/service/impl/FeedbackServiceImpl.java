package com.softserve.edu.delivery.service.impl;

import com.softserve.edu.delivery.dao.FeedbackDao;
import com.softserve.edu.delivery.dao.impl.FeedbackDaoImpl;
import com.softserve.edu.delivery.domain.Feedback;
import com.softserve.edu.delivery.dto.FeedbackDTO;
import com.softserve.edu.delivery.service.FeedbackService;
import com.softserve.edu.delivery.utils.Jpa;
import com.softserve.edu.delivery.utils.TransactionManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Ivan Rudnytskyi on 15.09.2016.
 */
public class FeedbackServiceImpl implements FeedbackService {

    private EntityManager entityManager = Jpa.getEntityManager();
    private FeedbackDao feedbackDao = new FeedbackDaoImpl();
    EntityTransaction tx = entityManager.getTransaction();

    public FeedbackServiceImpl() {
    }

    public FeedbackDTO copyFeedbackToDTO(Feedback feedback) {
        FeedbackDTO feedbackDTO = new FeedbackDTO();
        feedbackDTO.setFeedbackId(feedback.getFeedbackId());
        //feedbackDTO.setOrder(feedback.getOrder());
        feedbackDTO.setText(feedback.getText());
        //feedbackDTO.setUserName(feedback.getUser().getFirstName() + " " + feedback.getUser().getLastName());
        feedbackDTO.setRate(feedback.getRate());
        feedbackDTO.setApproved(feedback.isApproved());

        return feedbackDTO;
    }

    public Feedback copyDTOToFeedback(FeedbackDTO feedbackDTO) {
        Feedback feedback = new Feedback();
        feedback.setFeedbackId(feedbackDTO.getFeedbackId());
        //feedback.setOrder(feedbackDTO.getOrder());
        feedback.setText(feedbackDTO.getText());
        feedback.setRate(feedbackDTO.getRate());
        feedback.setApproved(feedbackDTO.isApproved());

        return feedback;
    }

    @Override
    public List<FeedbackDTO> getAllFeedbacks(int idFrom, int count) {

        List<FeedbackDTO> feedbackDTOs = new ArrayList<>();

        Optional<Feedback> oFeedback;

        entityManager.getTransaction().begin();

        for (long i = idFrom; i < idFrom + count;) {
            oFeedback = feedbackDao.findOne(i);
            if (oFeedback.isPresent()) {
                feedbackDTOs.add(copyFeedbackToDTO(oFeedback.get()));
                i++;
            }
        }

        entityManager.getTransaction().commit();

        return feedbackDTOs;

    }

    @Override
    public FeedbackDTO getFeedbackById(long id) {

        FeedbackDTO feedbackDTO = null;

        entityManager.getTransaction().begin();

        Optional<Feedback> oFeedback = feedbackDao.findOne(id);

        if (oFeedback.isPresent())
            feedbackDTO = copyFeedbackToDTO(oFeedback.get());

        entityManager.getTransaction().commit();

        return feedbackDTO;

    }

    @Override
    public void changeFeedbackStatus(long id, boolean status) {

        entityManager.getTransaction().begin();

        Optional<Feedback> oFeedback = feedbackDao.findOne(id);

        if (oFeedback.isPresent()) {
            Feedback feedback = oFeedback.get();
            feedback.setApproved(status);
            feedbackDao.update(feedback);
        }

        entityManager.getTransaction().commit();
    }

    @Override
    public void save(FeedbackDTO feedbackDTO) {
        TransactionManager.withTransaction(() ->
                feedbackDao.save(copyDTOToFeedback(feedbackDTO))
        );
    }

    @Override
    public void update(FeedbackDTO feedbackDTO) {

        TransactionManager.withTransaction(() ->
                feedbackDao.update(copyDTOToFeedback(feedbackDTO))
        );
    }

    @Override
    public void delete(Long id) {

        TransactionManager.withTransaction(() ->
                feedbackDao.delete(feedbackDao.findOne(id).get())
        );
    }

    @Override
    public FeedbackDTO findOne(FeedbackDTO feedbackDTO) {

        Feedback feedback = new Feedback();

        try {
            tx.begin();
            feedback = feedbackDao.findOne(feedbackDTO.getFeedbackId()).get();
            tx.commit();

        } catch (Exception e) {
            if (tx != null) tx.rollback();
        }

        return copyFeedbackToDTO(feedback);

    }

    @Override
    public List<FeedbackDTO> findAll() {
        List<FeedbackDTO> listDTO = new ArrayList<>();
        List<Feedback> list = feedbackDao.findAll();

        list.forEach(f -> {
            listDTO.add(copyFeedbackToDTO(f));
        });

        return listDTO;
    }


}
