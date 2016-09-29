package com.softserve.edu.delivery.service;

import com.softserve.edu.delivery.domain.Feedback;
import com.softserve.edu.delivery.dto.FeedbackDTO;

import java.util.List;

/**
 * Created by Ivan Rudnytskyi on 15.09.2016.
 */
public interface FeedbackService {

    FeedbackDTO copyFeedbackToDTO(Feedback feedback);

    Feedback copyDTOToFeedback(FeedbackDTO feedbackDTO);

    List<FeedbackDTO> getAllFeedbacks();

    List<FeedbackDTO> getAllFeedbacksInRange(long from, long count);

    FeedbackDTO getFeedbackById(long id);

    void changeFeedbackStatus(long id, boolean status);

    void save(FeedbackDTO feedbackDTO);

    void update(FeedbackDTO feedbackDTO);

    void delete(Long id);

    FeedbackDTO findOne(Long id);

    String getApprovedDriverName(Long orderId);
}
