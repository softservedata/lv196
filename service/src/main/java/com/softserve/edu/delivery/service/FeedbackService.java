package com.softserve.edu.delivery.service;

import com.softserve.edu.delivery.domain.Feedback;
import com.softserve.edu.delivery.domain.Order;
import com.softserve.edu.delivery.domain.User;
import com.softserve.edu.delivery.dto.FeedbackDTO;
import org.springframework.data.domain.Sort;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Ivan Rudnytskyi on 15.09.2016.
 */
public interface FeedbackService {

    FeedbackDTO copyFeedbackToDTO(Feedback feedback);

    Feedback copyDTOToFeedback(FeedbackDTO feedbackDTO);

    List<FeedbackDTO> findAll();

    List<FeedbackDTO> getAllFeedbacksInRange(Long from, int number);

    void changeFeedbackStatus(Long id, boolean status);

    void save(FeedbackDTO feedbackDTO);

    void update(FeedbackDTO feedbackDTO);

    void delete(Long id);

    FeedbackDTO findOne(Long id);

    User getUser(String email);

    Order getOrder(Long id);

    /*------------- Find all feedbacks -----------------------*/

    List<FeedbackDTO> findFiltered(String text, String rateString, String userName, String transporterName,
                                   String createdOnString, String approvedString, String sortBy, String sort);

    /*------------- Find feedbacks by id -----------------------*/

    FeedbackDTO findByFeedbackId(Long id);

}
