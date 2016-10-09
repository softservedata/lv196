package com.softserve.edu.delivery.service;

import com.softserve.edu.delivery.dao.FeedbackDao;
import com.softserve.edu.delivery.dao.OrderDao;
import com.softserve.edu.delivery.dao.UserDao;
import com.softserve.edu.delivery.domain.Car;
import com.softserve.edu.delivery.domain.Feedback;
import com.softserve.edu.delivery.domain.Offer;
import com.softserve.edu.delivery.domain.Order;
import com.softserve.edu.delivery.domain.User;
import com.softserve.edu.delivery.dto.FeedbackDTO;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Ivan Rudnytskyi on 15.09.2016.
 */
public interface FeedbackService {

    FeedbackDTO copyFeedbackToDTO(Feedback feedback);

    Feedback copyDTOToFeedback(FeedbackDTO feedbackDTO);

    List<FeedbackDTO> getAllFeedbacks();

    List<FeedbackDTO> getAllFeedbacksInRange(Long from, int number);

    void changeFeedbackStatus(Long id, boolean status);

    void save(FeedbackDTO feedbackDTO);

    void update(FeedbackDTO feedbackDTO);

    void delete(Long id);

    FeedbackDTO findOne(Long id);

    User getUser(String email);

    Order getOrder(Long id);

    FeedbackDTO getFeedbackById(Long id);

    List <FeedbackDTO> findByTextContaining (String text);

    List <FeedbackDTO> findByFeedbackIdGreaterThan(Long id);

    List <FeedbackDTO> findByFeedbackIdLessThan(Long id);

    List<FeedbackDTO> findByRate(Integer rate);

    List <FeedbackDTO> findByRateGreaterThan (Integer rate);

    List <FeedbackDTO> findByRateLessThan (Integer rate);

    List <FeedbackDTO> findByUserFirstNameOrLastName(String userName);

    List <FeedbackDTO> findByTransporterFirstNameOrLastName(String transporterName);

    List<FeedbackDTO> findByApproved(Boolean approved);

    List<FeedbackDTO> findByCreatedOn(Timestamp createdOn);
}
