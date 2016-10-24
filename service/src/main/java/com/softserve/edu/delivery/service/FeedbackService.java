package com.softserve.edu.delivery.service;

import com.softserve.edu.delivery.domain.Feedback;
import com.softserve.edu.delivery.domain.Order;
import com.softserve.edu.delivery.domain.User;
import com.softserve.edu.delivery.dto.FeedbackDTO;

import java.util.List;

public interface FeedbackService {

    FeedbackDTO copyFeedbackToDTO(Feedback feedback);

    Feedback copyDTOToFeedback(FeedbackDTO feedbackDTO);

    List<FeedbackDTO> findAll();

    void changeFeedbackStatus(Long id, boolean status);

    void save(FeedbackDTO feedbackDTO);

    void update(FeedbackDTO feedbackDTO);

    void delete(Long id);

    FeedbackDTO findOne(Long id);

    User getUser(String email);

    Order getOrder(Long id);

    /*------------- Find all feedbacks -----------------------*/

    List<FeedbackDTO> findFiltered(String text, String rateString, String userName, String transporterName,
                                   String createdOnString, String approvedString, String sortBy, String sort,
                                   int currentPage, int itemsPerPage);

    long getTotalItemsNumber();

    /*------------- Find feedbacks by id -----------------------*/

    FeedbackDTO findByFeedbackId(Long id);

}
