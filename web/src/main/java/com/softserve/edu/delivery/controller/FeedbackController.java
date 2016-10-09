package com.softserve.edu.delivery.controller;

import java.util.List;

import com.softserve.edu.delivery.dto.FeedbackDTO;
import com.softserve.edu.delivery.service.FeedbackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    Logger logger = LoggerFactory.getLogger(FeedbackController.class.getName());

    @RequestMapping(params = {"all"}, method = RequestMethod.GET)
    List<FeedbackDTO> getAllFeedbacks() {
        logger.info("Method FeedbackController.getAllFeedbacks()");
        return feedbackService.getAllFeedbacks();
    }

    @RequestMapping(params = {"idFrom", "number"}, method = RequestMethod.GET)
    List<FeedbackDTO> getAllFeedbacksInRange(@RequestParam("idFrom") long from,
                                             @RequestParam("number") int number) {
        logger.info("Method FeedbackController.getAllFeedbacks()");
        return feedbackService.getAllFeedbacksInRange(from, number);
    }

    @RequestMapping(params = {"id"}, method = RequestMethod.GET)
    FeedbackDTO getFeedbackById(@RequestParam("id") long feedbackId) {
        logger.info("Method FeedbackController.getFeedbackById()");
        return feedbackService.findOne(feedbackId);
    }

    @RequestMapping(path = {"changeFeedbackStatus"}, method = RequestMethod.PUT)
    void changeFeedbackStatus(@RequestBody FeedbackDTO feedbackDTO){
        logger.info("In method FeedbackController.changeFeedbackStatus()");
        feedbackService.update(feedbackDTO);
    }
}