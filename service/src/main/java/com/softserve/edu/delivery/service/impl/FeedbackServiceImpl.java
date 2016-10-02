package com.softserve.edu.delivery.service.impl;

import com.softserve.edu.delivery.dao.CarDao;
import com.softserve.edu.delivery.dao.FeedbackDao;
import com.softserve.edu.delivery.dao.OfferDao;
import com.softserve.edu.delivery.dao.OrderDao;
import com.softserve.edu.delivery.dao.UserDao;
import com.softserve.edu.delivery.domain.Car;
import com.softserve.edu.delivery.domain.Feedback;
import com.softserve.edu.delivery.domain.Offer;
import com.softserve.edu.delivery.domain.Order;
import com.softserve.edu.delivery.domain.User;
import com.softserve.edu.delivery.dto.FeedbackDTO;
import com.softserve.edu.delivery.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Created by Ivan Rudnytskyi on 15.09.2016.
 * implementation for business logic of the feedback part of the application
 */
@Service
@Transactional
public class FeedbackServiceImpl implements FeedbackService {

    private FeedbackDao feedbackDao;
    private UserDao userDao;
    private CarDao carDao;
    private OrderDao orderDao;
    private OfferDao offerDao;

    public FeedbackServiceImpl() {
    }

    @Autowired
    public FeedbackServiceImpl(FeedbackDao feedbackDao, UserDao userDao, CarDao carDao, OrderDao orderDao, OfferDao offerDao) {
        this.feedbackDao = feedbackDao;
        this.userDao = userDao;
        this.carDao = carDao;
        this.orderDao = orderDao;
        this.offerDao = offerDao;
    }

    @Override
    public void setFeedbackDao(FeedbackDao feedbackDao) {
        this.feedbackDao = feedbackDao;
    }

    @Override
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void setOrderDao(OrderDao orderDao) {
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
        feedbackDTO.setOrder(feedback.getOrder());
        feedbackDTO.setText(feedback.getText());
        feedbackDTO.setUser(feedback.getUser());
        feedbackDTO.setRate(feedback.getRate());
        feedbackDTO.setApproved(feedback.getApproved());
        feedbackDTO.setTransporterName(feedbackDao.getApprovedDriverName(feedback.getOrder().getId()));
        feedbackDTO.setCreatedOn(feedback.getCreatedOn());
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
        feedback.setCreatedOn(feedbackDTO.getCreatedOn());
        return feedback;
    }


    /**
     * @return List of FeedbackDTO.class
     *
     * looks for all feedbacks and returns list of found feedbacks
     *
     */
    @Override
    @Transactional
    public List<FeedbackDTO> getAllFeedbacks() {
        List<FeedbackDTO> listDTO = new ArrayList<>();
        List<Feedback> list = feedbackDao.findAll();

        list.forEach(f -> listDTO.add(copyFeedbackToDTO(f)));

        return listDTO;
    }


    /**
     * @param int idFrom, int number
     * @return List<FeedbackDTO>
     *
     * accepts start id of a feedback in the db and number of feedbacks. Forms list of FeedbackDTO object,
     * whose ids are within the range.
     */
    @Override
    @Transactional
    public List<FeedbackDTO> getAllFeedbacksInRange(long idFrom, long number) {

        List<FeedbackDTO> feedbackDTOs = new ArrayList<>();

        FeedbackDTO feedbackDTO;

        //increment of i is performed only in case, when a feedback with given id is found - since
        //ids in feedback table will not be in perfect sequence - gaps will be present, caused by
        //deleting feedbacks

        long id = idFrom;
        int count = 0;
        long entriesCount = feedbackDao.getId("select count(f) from Feedback f where f.id >=" + id);
        long startId = feedbackDao.getId("select min(f.id) from Feedback f");

        if (id < 0 || id < startId){
            id = startId;
        }

        if (number < 0 ){
            number = 1;
        }

        if (number > entriesCount){
            number = entriesCount;
        }

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
    @Transactional
    /**
     * @param long id - id of a feedback in db
     * @return object of FeedbackDTO.class
     * looks for a feedback with a given id
     */
    public FeedbackDTO getFeedbackById(long id) {

        FeedbackDTO feedbackDTO;

        Optional<Feedback> oFeedback = feedbackDao.findOne(id);

        if (oFeedback.isPresent()) {
            feedbackDTO = copyFeedbackToDTO(oFeedback.get());
        } else {
            throw new NoSuchElementException();
        }

        return feedbackDTO;

    }

    @Override
    @Transactional
    /**
     * @param long id, boolean status
     *
     * sets a status of a feedback with the id equal to variable status
     */
    public void changeFeedbackStatus(long id, boolean status) {

        Optional<Feedback> oFeedback = feedbackDao.findOne(id);

        if (oFeedback.isPresent()) {
            Feedback feedback = oFeedback.get();
            feedback.setApproved(status);
            feedbackDao.update(feedback);
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
        feedbackDao.save(feedback);
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

        feedbackDao.update(feedback);

    }

    @Override
    @Transactional
    /**
     * @param feedbackDTO
     *
     * transforms the object of FeedbackDTO.class to Feedback.class, which is deleted from the db.
     */
    public void delete(Long id) {

        Optional<Feedback> oFeedback = feedbackDao.findOne(id);
        if (oFeedback.isPresent()) {
            feedbackDao.delete(oFeedback.get());
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

        Optional<Feedback> oFeedback = feedbackDao.findOne(id);

        if (oFeedback.isPresent()) {
            feedback = oFeedback.get();
        } else {
            throw new NoSuchElementException();
        }

        return copyFeedbackToDTO(feedback);
    }

    @Override
    @Transactional
    /**
     * @param Long orderId - id of the order
     * @return String approvedDriverName
     *
     * the method searches in the DB the first and last name of the approved driver for the order with the id
     */
    public String getApprovedDriverName(Long orderId) {
        return feedbackDao.getApprovedDriverName(orderId);
    }

    @Override
    @Transactional
    public Long getId(String query) {
        return feedbackDao.getId(query);
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

    @Override
    @Transactional
    public Car getCar(Long id) {
        Optional<Car> oCar = carDao.findOne(id);
        if (oCar.isPresent()) {
            return oCar.get();
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    @Transactional
    public void saveUser(User user){
        userDao.save(user);
    }

    @Override
    @Transactional
    public List<User> getUsersByRole(String role) {
        return feedbackDao.getUsersByRole(role);
    }

    @Override
    @Transactional
    public void saveCar(Car car) {
        carDao.save(car);
    }

    @Override
    @Transactional
    public void saveOrder(Order order) {
        orderDao.save(order);
    }

    @Override
    @Transactional
    public void saveOffer(Offer offer) {
        offerDao.save(offer);
    }
}
