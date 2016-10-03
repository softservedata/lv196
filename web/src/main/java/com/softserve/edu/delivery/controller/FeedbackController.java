package com.softserve.edu.delivery.controller;

import java.util.List;

import com.softserve.edu.delivery.dto.FeedbackDTO;
import com.softserve.edu.delivery.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "feedbacks")
public class FeedbackController {

    private final FeedbackService feedbackService;

    @Autowired
    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @RequestMapping(params = {"all"}, method = RequestMethod.GET)
    List<FeedbackDTO> getAllFeedbacks() {
        return feedbackService.getAllFeedbacks();
    }

    @RequestMapping(params = {"idFrom", "number"}, method = RequestMethod.GET)
    List<FeedbackDTO> getAllFeedbacksInRange(@RequestParam("idFrom") long from,
                                             @RequestParam("number") int number) {
        return feedbackService.getAllFeedbacksInRange(from, number);
    }

    @RequestMapping(params = {"id"}, method = RequestMethod.GET)
    FeedbackDTO getFeedbackById(@RequestParam("id") long feedbackId) {
        return feedbackService.findOne(feedbackId);
    }
}