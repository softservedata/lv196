package com.softserve.edu.delivery.service;

import com.softserve.edu.delivery.domain.Feedback;
import com.softserve.edu.delivery.dto.FeedbackDTO;

import java.util.List;
import java.util.Optional;

/**
 * Created by Ivan Rudnytskyi on 15.09.2016.
 */
public interface FeedbackService {

    List<FeedbackDTO> getAllFeedbacksInRange(int from, int count);

    FeedbackDTO getFeedbackById(long id);

    void changeFeedbackStatus(long id, boolean status);

    FeedbackDTO copyFeedbackToDTO(Feedback feedback);

    Feedback copyDTOToFeedback(FeedbackDTO feedbackDTO);


    public void save(FeedbackDTO feedbackDTO);

    public void update(FeedbackDTO feedbackDTO);

    public void delete(Long id);

    public FeedbackDTO findOne(Long id);

    public List<FeedbackDTO> getAllFeedbacks();

}
