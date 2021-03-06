package com.softserve.edu.delivery.controller;

import com.softserve.edu.delivery.dto.FeedbackDto;
import com.softserve.edu.delivery.dto.FeedbackFilterDTO;
import com.softserve.edu.delivery.service.FeedbackService;
import com.softserve.edu.delivery.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

import static com.softserve.edu.delivery.config.SecurityConstraints.MODERATOR_OR_MANAGER;

@RestController
@RequestMapping(path = "feedback")
public class FeedbackController {

    private final Logger logger = LoggerFactory.getLogger(FeedbackController.class.getName());

    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private NotificationService notificationService;

    @PreAuthorize(MODERATOR_OR_MANAGER)
    @RequestMapping(path = "all", method = RequestMethod.POST)
    List<FeedbackDto> getAllFeedbacks(@RequestBody FeedbackFilterDTO feedbackFilterDTO) {
        logger.info("Before feedbackService.findFiltered()");
        return feedbackService.findFiltered(feedbackFilterDTO);
    }

    @PreAuthorize(MODERATOR_OR_MANAGER)
    @RequestMapping(path = "totalItems", method = RequestMethod.POST)
    long getTotalItemsNumber(@RequestBody FeedbackFilterDTO feedbackFilterDTO) {
        logger.info("Before feedbackService.getTotalItemsNumber()");
        return feedbackService.getTotalItemsNumber(feedbackFilterDTO);
    }

    @PreAuthorize(MODERATOR_OR_MANAGER)
    @RequestMapping(path = {"updateFeedback"}, method = RequestMethod.PUT)
    void updateFeedback(@RequestBody FeedbackDto feedbackDTO) {
        logger.info("Before feedbackService.update(feedbackDTO)");

        try {
            feedbackService.update(feedbackDTO);
            notificationService.updateFeedback(feedbackDTO);
        } catch (NoSuchElementException e) {
            logger.error("Exception while trying to update feedback with id " + feedbackDTO.getFeedbackId() +
                    " in feedbackService.update(feedbackDTO) " + e.getMessage());
        }
    }

    @PreAuthorize(MODERATOR_OR_MANAGER)
    @RequestMapping(path = {"deleteFeedback/{feedbackId}"}, method = RequestMethod.DELETE)
    void deleteFeedback(@PathVariable Long feedbackId) {
        logger.info("Before feedbackService.delete(feedbackId)");
        feedbackService.delete(feedbackId);
    }

}