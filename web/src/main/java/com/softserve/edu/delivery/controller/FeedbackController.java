package com.softserve.edu.delivery.controller;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import com.softserve.edu.delivery.dto.FeedbackDTO;
import com.softserve.edu.delivery.View;
import com.softserve.edu.delivery.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "feedbacks")
public class FeedbackController {

    @Autowired
    FeedbackService feedbackService;

    @RequestMapping(params = {"all"}, method = RequestMethod.GET)
    @JsonView(View.Feedback.class)
    List<FeedbackDTO> getAllFeedbacks() {
        List<FeedbackDTO> list = feedbackService.getAllFeedbacks();
        return list;
    }

    @RequestMapping(params = {"idFrom", "number"}, method = RequestMethod.GET)
    @JsonView(View.Feedback.class)
    List<FeedbackDTO> getAllFeedbacksInRange(@RequestParam("idFrom") long from,
                                             @RequestParam("number") long number) {
        List<FeedbackDTO> list = feedbackService.getAllFeedbacksInRange(from, number);
        return list;
    }

    @RequestMapping(params = {"id"}, method = RequestMethod.GET)
    @JsonView(View.Feedback.class)
    FeedbackDTO getFeedbackById(@RequestParam("id") long feedbackId) {
        FeedbackDTO feedbackDTO = feedbackService.findOne(feedbackId);
        return feedbackDTO;
    }
}