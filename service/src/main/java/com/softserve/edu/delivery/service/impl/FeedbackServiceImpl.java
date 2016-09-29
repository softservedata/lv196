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
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Created by Ivan Rudnytskyi on 15.09.2016.
 * implementation for business logic of the feedback part of the application
 */
public class FeedbackServiceImpl implements FeedbackService {

    private FeedbackDao feedbackDao = new FeedbackDaoImpl();

    private static FeedbackService fsi;

    private  FeedbackServiceImpl() {
    }

    public static FeedbackService getInstance(){
        if (fsi == null) {
            fsi = new FeedbackServiceImpl();
        }
        return fsi;
    }

    @Override
    public void setFeedbackDao(FeedbackDao feedbackDao){
        this.feedbackDao = feedbackDao;
    }

    /**
     * @param feedback of Feedback class
     * @return object of FeedbackDTO.class
     * <p>
     * copies all the fields of an object of Feedback.class to an object of FeedbackDTO.class
     */
    public FeedbackDTO copyFeedbackToDTO(Feedback feedback) {
        FeedbackDTO feedbackDTO = new FeedbackDTO();
        feedbackDTO.setFeedbackId(feedback.getFeedbackId());
        feedbackDTO.setOrder(feedback.getOrder());
        feedbackDTO.setText(feedback.getText());
        feedbackDTO.setUser(feedback.getUser());
        feedbackDTO.setRate(feedback.getRate());
        feedbackDTO.setApproved(feedback.getApproved());

        return feedbackDTO;
    }

    /**
     * @param feedbackDTO of Feedback class
     * @return object of Feedback.class
     * <p>
     * copies all the fields of an object of FeedbackDTO.class to an object of Feedback.class
     */
    public Feedback copyDTOToFeedback(FeedbackDTO feedbackDTO) {
        Feedback feedback = new Feedback();
        feedback.setFeedbackId(feedbackDTO.getFeedbackId());
        feedback.setOrder(feedbackDTO.getOrder());
        feedback.setText(feedbackDTO.getText());
        feedback.setUser(feedbackDTO.getUser());
        feedback.setRate(feedbackDTO.getRate());
        feedback.setApproved(feedbackDTO.getApproved());

        return feedback;
    }

    @Override
    /**
     * @return List of FeedbackDTO.class
     *
     * looks for all feedbacks and returns list of found feedbacks
     *
     */
    public List<FeedbackDTO> getAllFeedbacks() {
        List<FeedbackDTO> listDTO = new ArrayList<>();
        List<Feedback> list = TransactionManager.withTransaction(() ->
                feedbackDao.findAll()
        );

        list.forEach(f -> listDTO.add(copyFeedbackToDTO(f)));

        return listDTO;
    }

    @Override
    /**
     * @param int idFrom, int number
     * @return List<FeedbackDTO>
     *
     * accepts start id of a feedback in the db and number of feedbacks. Forms list of FeedbackDTO object,
     * whose ids are within the range.
     */
    public List<FeedbackDTO> getAllFeedbacksInRange(long idFrom, long number) {

        List<FeedbackDTO> feedbackDTOs = new ArrayList<>();

        FeedbackDTO feedbackDTO;

        //increment of i is performed only in case, when a feedback with given id is found - since
        //ids in feedback table will not be in perfect sequence - gaps will be present, caused by
        //deleting feedbacks

        long id = idFrom;
        int count = 0;

        while (count < number) {
            try {
                feedbackDTO = getFeedbackById(id);
                feedbackDTOs.add(feedbackDTO);
                count++;
            } catch (NoSuchElementException e) {
            }
            id++;
        }

        return feedbackDTOs;

    }

    @Override
    /**
     * @param long id - id of a feedback in db
     * @return object of FeedbackDTO.class
     * looks for a feedback with a given id
     */
    public FeedbackDTO getFeedbackById(long id) {

        FeedbackDTO feedbackDTO;

        Optional<Feedback> oFeedback = TransactionManager.withTransaction(() ->
                feedbackDao.findOne(id)
        );

        if (oFeedback.isPresent()) {
            feedbackDTO = copyFeedbackToDTO(oFeedback.get());
        } else {
            throw new NoSuchElementException();
        }

        return feedbackDTO;

    }

    @Override
    /**
     * @param long id, boolean status
     *
     * sets a status of a feedback with the id equal to variable status
     */
    public void changeFeedbackStatus(long id, boolean status) {

        Optional<Feedback> oFeedback = TransactionManager.withTransaction(() ->
                feedbackDao.findOne(id)
        );

        if (oFeedback.isPresent()) {
            Feedback feedback = oFeedback.get();
            feedback.setApproved(status);
            TransactionManager.withTransaction(() ->
                    feedbackDao.update(feedback)
            );
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    /**
     * @param feedbackDTO
     *
     * transforms the object of FeedbackDTO.class to Feedback.class, which is saved to the db.
     */
    public void save(FeedbackDTO feedbackDTO) {

        Feedback feedback = copyDTOToFeedback(feedbackDTO);

        TransactionManager.withTransaction(() ->
                feedbackDao.save(feedback)
        );
    }

    @Override
    /**
     * @param feedbackDTO
     *
     * transforms the object of FeedbackDTO.class to Feedback.class, which is updated in the db.
     */
    public void update(FeedbackDTO feedbackDTO) {

        Feedback feedback = copyDTOToFeedback(feedbackDTO);

        TransactionManager.withTransaction(() ->
                feedbackDao.update(feedback)
        );
    }

    @Override
    /**
     * @param feedbackDTO
     *
     * transforms the object of FeedbackDTO.class to Feedback.class, which is deleted from the db.
     */
    public void delete(Long id) {

        TransactionManager.withTransaction(() -> {
            Optional<Feedback> oFeedback = feedbackDao.findOne(id);
            if (oFeedback.isPresent()) {
                feedbackDao.delete(oFeedback.get());
            } else {
                throw new NoSuchElementException();
            }
        });
    }

    @Override
    /**
     * @param feedbackDTO
     * @return object of FeedbackDTO.class
     *
     * looks for a feedback with the id of feedbackDTO object and return object of Feedback.class with the id.
     * If it is not found - throws NoSuchElementException
     */
    public FeedbackDTO findOne(Long id) {

        Feedback feedback;

        Optional<Feedback> oFeedback = feedbackDao.findOne(id);

        if (oFeedback.isPresent()) {
            feedback = oFeedback.get();
        } else {
            throw new NoSuchElementException();
        }

        return copyFeedbackToDTO(feedback);
    }

    @Override
    /**
     * @param Long orderId - id of the order
     * @return String approvedDriverName
     *
     * the method searches in the DB the first and last name of the approved driver for the order with the id
     */
    public String getApprovedDriverName(Long orderId) {
        EntityManager entityManager = Jpa.getEntityManager();
        EntityTransaction tx = null;
        String query = "select u.first_name, u.last_name from orders ord " +
                "join offer of on " +
                "ord.order_id=of.order_id " +
                "join cars c on " +
                "of.car_id=c.car_id " +
                "join users u on " +
                "c.driver_id=u.email " +
                "where of.isApproved and ord.order_id=?1";
        List<Object[]> approvedDriverName = new ArrayList<>();
        try {
            tx = entityManager.getTransaction();
            tx.begin();
            approvedDriverName  = entityManager.createNativeQuery(query)
                    .setParameter(1, orderId)
                    .getResultList();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        }

        return approvedDriverName.get(0)[0] + " " + approvedDriverName.get(0)[1];
    }
}
