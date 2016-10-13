package com.softserve.edu.delivery.controller;

import com.softserve.edu.delivery.dto.FeedbackDTO;
import com.softserve.edu.delivery.service.FeedbackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    Logger logger = LoggerFactory.getLogger(FeedbackController.class.getName());

    @RequestMapping(path = "all", method = RequestMethod.GET)
    List<FeedbackDTO> getAllFeedbacks(@RequestParam("text") String text,
                                      @RequestParam("rate") String rateString,
                                      @RequestParam("userName") String userName,
                                      @RequestParam("transporterName") String transporterName,
                                      @RequestParam("createdOn") String createdOnString,
                                      @RequestParam("approved") String approvedString,
                                      @RequestParam("sortBy") String sortBy,
                                      @RequestParam("sortDesc") String sortDesc) {

        logger.info("Method FeedbackController.findAll()");

        return feedbackService.findFiltered(text, rateString, userName, transporterName, createdOnString,
                approvedString, sortBy, sortDesc);
    }

    @RequestMapping(path = {"changeFeedbackStatus"}, method = RequestMethod.PUT)
    void changeFeedbackStatus(@RequestBody FeedbackDTO feedbackDTO) {
        logger.info("In method FeedbackController.changeFeedbackStatus()");
        feedbackService.update(feedbackDTO);
    }
}