package com.softserve.edu.delivery.service;

import com.softserve.edu.delivery.domain.Feedback;
import com.softserve.edu.delivery.domain.Order;
import com.softserve.edu.delivery.domain.User;
import com.softserve.edu.delivery.dto.FeedbackDto;

import java.util.List;

public interface FeedbackService {

    FeedbackDto copyFeedbackToDTO(Feedback feedback);

    Feedback copyDTOToFeedback(FeedbackDto feedbackDTO);

    List<FeedbackDto> findAll();

    void changeFeedbackStatus(Long id, boolean status);

    void save(FeedbackDto feedbackDTO);

    void update(FeedbackDto feedbackDTO);

    void delete(Long id);

    FeedbackDto findOne(Long id);

    User getUser(String email);

    Order getOrder(Long id);

    /*------------- Find all feedbacks -----------------------*/

    List<FeedbackDto> findFiltered(String text, String rateString, String userName, String transporterName,
                                   String createdOnString, String approvedString, String sortBy, String sort,
                                   int currentPage, int itemsPerPage);

    long getTotalItemsNumber();

    /*------------- Find feedbacks by id -----------------------*/

    FeedbackDto findByFeedbackId(Long id);

    FeedbackDto getCustomerFeedback(Long id);
}
