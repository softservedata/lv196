package com.softserve.edu.delivery.service.impl;

import com.softserve.edu.delivery.dao.OrderDao;
import com.softserve.edu.delivery.dao.UserDao;
import com.softserve.edu.delivery.domain.Feedback;
import com.softserve.edu.delivery.domain.Order;
import com.softserve.edu.delivery.domain.User;
import com.softserve.edu.delivery.dto.FeedbackDTO;
import com.softserve.edu.delivery.repository.FeedbackRepository;
import com.softserve.edu.delivery.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Created by Ivan Rudnytskyi on 15.09.2016.
 * implementation for business logic of the feedback part of the application
 */
@Service("feedbackService")
@Transactional
public class FeedbackServiceImpl implements FeedbackService {



    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private UserDao userDao;
    @Autowired
    private OrderDao orderDao;

    public FeedbackServiceImpl() {
    }

    public FeedbackServiceImpl(FeedbackRepository feedbackRepository, UserDao userDao, OrderDao orderDao) {
        this.feedbackRepository = feedbackRepository;
        this.userDao = userDao;
        this.orderDao = orderDao;
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
        feedbackDTO.setOrderId(feedback.getOrder().getId());
        feedbackDTO.setText(feedback.getText());
        feedbackDTO.setUserName(feedback.getUser().getFirstName() + " " + feedback.getUser().getLastName());
        feedbackDTO.setUserEmail(feedback.getUser().getEmail());
        feedbackDTO.setRate(feedback.getRate());
        feedbackDTO.setApproved(feedback.getApproved());
        Optional<String> oApprovedDriverName = feedbackRepository.getApprovedDriverName(feedback.getOrder().getId());
        if (oApprovedDriverName.isPresent()) {
            feedbackDTO.setTransporterName(oApprovedDriverName.get());
        } else {
            throw new NoSuchElementException("Driver name for order with id " + feedbackDTO.getOrderId() + " not found");
        }
        Optional<String> oApprovedDriverEmail = feedbackRepository.getApprovedDriverEmail(feedback.getOrder().getId());
        if (oApprovedDriverEmail.isPresent()) {
            feedbackDTO.setTransporterEmail(oApprovedDriverEmail.get());
        } else {
            throw new NoSuchElementException("Driver email for order with id " + feedbackDTO.getOrderId() + " not found");
        }
        feedbackDTO.setCreatedOn(feedback.getCreatedOn());
        return feedbackDTO;
    }

    private List<FeedbackDTO> copyFeedbackListToDTOList(List<Feedback> feedbackList) {
        List<FeedbackDTO> listFeedbackDTO = new ArrayList<>();
        feedbackList.forEach(f ->
                listFeedbackDTO.add(copyFeedbackToDTO(f))
        );
        return listFeedbackDTO;
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
        Optional<User> oUser = userDao.findOne(feedbackDTO.getUserEmail());
        if (oUser.isPresent()) {
            feedback.setUser(oUser.get());
        } else {
            throw new NoSuchElementException("User with id " + feedbackDTO.getUserEmail() + " not found");
        }
        Optional<Order> oOrder = orderDao.findOne(feedbackDTO.getOrderId());
        if (oOrder.isPresent()) {
            feedback.setOrder(oOrder.get());
        } else {
            throw new NoSuchElementException("Order with id " + feedbackDTO.getOrderId() + " not found");
        }
        feedback.setText(feedbackDTO.getText());
        feedback.setRate(feedbackDTO.getRate());
        feedback.setApproved(feedbackDTO.getApproved());
        feedback.setCreatedOn(feedbackDTO.getCreatedOn());
        return feedback;
    }

    /**
     * @param idFrom, number
     * @return List<FeedbackDTO>
     * <p>
     * accepts start id of a feedback in the db and number of feedbacks. Forms list of FeedbackDTO object,
     * whose ids are within the range.
     */
    @Override
    @Transactional
    public List<FeedbackDTO> getAllFeedbacksInRange(Long idFrom, int number) {

        List<FeedbackDTO> feedbackDTOs = new ArrayList<>();

        return feedbackDTOs;

    }

    @Override
    @Transactional
    /**
     * @param long id, boolean status
     *
     * sets a status of a feedback with the id equal to variable status
     */
    public void changeFeedbackStatus(Long id, boolean status) {

        Optional<Feedback> oFeedback = feedbackRepository.findOneOpt(id);

        if (oFeedback.isPresent()) {
            Feedback feedback = oFeedback.get();
            feedback.setApproved(status);
            feedbackRepository.save(feedback);
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    @Transactional
    /**
     * @param feedbackDTO
     *
     * transforms the object of FeedbackDTO.class to Feedback.class, which is saved to the db.
     */
    public void save(FeedbackDTO feedbackDTO) {
        Feedback feedback = copyDTOToFeedback(feedbackDTO);
        feedbackRepository.save(feedback);
    }

    @Override
    @Transactional
    /**
     * @param feedbackDTO
     *
     * transforms the object of FeedbackDTO.class to Feedback.class, which is updated in the db.
     */
    public void update(FeedbackDTO feedbackDTO) {
        Feedback feedback = copyDTOToFeedback(feedbackDTO);
        feedbackRepository.save(feedback);
    }

    @Override
    @Transactional
    /**
     * @param feedbackDTO
     *
     * transforms the object of FeedbackDTO.class to Feedback.class, which is deleted from the db.
     */
    public void delete(Long id) {

        Optional<Feedback> oFeedback = feedbackRepository.findOneOpt(id);
        if (oFeedback.isPresent()) {
            feedbackRepository.delete(oFeedback.get());
        } else {
            throw new NoSuchElementException();
        }

    }

    @Override
    @Transactional
    /**
     * @param feedbackDTO
     * @return object of FeedbackDTO.class
     *
     * looks for a feedback with the id of feedbackDTO object and return object of Feedback.class with the id.
     * If it is not found - throws NoSuchElementException
     */
    public FeedbackDTO findOne(Long id) {

        Feedback feedback;

        Optional<Feedback> oFeedback = feedbackRepository.findOneOpt(id);

        if (oFeedback.isPresent()) {
            feedback = oFeedback.get();
        } else {
            throw new NoSuchElementException();
        }

        return copyFeedbackToDTO(feedback);
    }

    @Override
    @Transactional
    public User getUser(String email) {
        Optional<User> oUser = userDao.findOne(email);
        if (oUser.isPresent()) {
            return oUser.get();
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    @Transactional
    public Order getOrder(Long id) {
        Optional<Order> oOrder = orderDao.findOne(id);
        if (oOrder.isPresent()) {
            return oOrder.get();
        } else {
            throw new NoSuchElementException();
        }
    }

    /**
     * @return List of FeedbackDTO.class
     * <p>
     * looks for all feedbacks and returns list of found feedbacks
     */
    @Override
    @Transactional
    public List<FeedbackDTO> findAll() {
        return copyFeedbackListToDTOList(feedbackRepository.findAll());
    }

    @Override
    @Transactional
    public List<FeedbackDTO> findByOrderByFeedbackIdDesc() {
        return copyFeedbackListToDTOList(feedbackRepository.findByOrderByFeedbackIdDesc());
    }

    @Override
    @Transactional
    /**
     * @param long id - id of a feedback in db
     * @return object of FeedbackDTO.class
     * looks for a feedback with a given id
     */
    public FeedbackDTO findByFeedbackId(Long id) {
        return copyFeedbackToDTO(feedbackRepository.findByFeedbackId(id));
    }

    @Override
    @Transactional
    public List<FeedbackDTO> findByFeedbackIdGreaterThan(Long id) {
        return copyFeedbackListToDTOList(feedbackRepository.findByFeedbackIdGreaterThan(id));
    }


    @Override
    @Transactional
    public List<FeedbackDTO> findByFeedbackIdGreaterThanOrderByFeedbackIdDesc(Long id){
        return copyFeedbackListToDTOList(feedbackRepository.findByFeedbackIdGreaterThanOrderByFeedbackIdDesc(id));
    }

    @Override
    @Transactional
    public List<FeedbackDTO> findByFeedbackIdLessThan(Long id) {
        return copyFeedbackListToDTOList(feedbackRepository.findByFeedbackIdLessThan(id));
    }

    @Override
    @Transactional
    public List<FeedbackDTO> findByFeedbackIdLessThanOrderByFeedbackIdDesc(Long id){
        return copyFeedbackListToDTOList(feedbackRepository.findByFeedbackIdLessThanOrderByFeedbackIdDesc(id));
    }

    @Override
    @Transactional
    public List<FeedbackDTO> findByRate(Integer rate) {
        return copyFeedbackListToDTOList(feedbackRepository.findByRate(rate));
    }

    @Override
    @Transactional
    public List<FeedbackDTO> findByRateGreaterThan(Integer rate) {
        return copyFeedbackListToDTOList(feedbackRepository.findByRateGreaterThan(rate));
    }

    @Override
    @Transactional
    public List<FeedbackDTO> findByRateLessThan(Integer rate) {
        return copyFeedbackListToDTOList(feedbackRepository.findByRateLessThan(rate));
    }

    @Override
    @Transactional
    public List<FeedbackDTO> findByTextContaining(String text) {
        return copyFeedbackListToDTOList(feedbackRepository.findByTextContaining(text));
    }

    @Override
    @Transactional
    public List <FeedbackDTO> findByUserFirstNameOrLastName(String userName){
        return copyFeedbackListToDTOList(feedbackRepository.findByUserFirstNameOrLastName(userName));
    }

    @Override
    @Transactional
    public List <FeedbackDTO> findByTransporterFirstNameOrLastName(String transporterName){
        return copyFeedbackListToDTOList(feedbackRepository.findByTransporterFirstNameOrLastName(transporterName));
    }

    @Override
    @Transactional
    public List<FeedbackDTO> findByApproved(Boolean approved){
        return copyFeedbackListToDTOList(feedbackRepository.findByApproved(approved));
    }

    @Override
    @Transactional
    public List<FeedbackDTO> findByCreatedOn(Timestamp createdOn){
        return copyFeedbackListToDTOList(feedbackRepository.findByCreatedOnAfter(createdOn));
    }

}