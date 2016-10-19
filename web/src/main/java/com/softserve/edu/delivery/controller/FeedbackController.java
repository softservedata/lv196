package com.softserve.edu.delivery.controller;

import com.softserve.edu.delivery.dto.FeedbackDTO;
import com.softserve.edu.delivery.service.FeedbackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "feedbacks")
public class FeedbackController {

    private Logger logger = LoggerFactory.getLogger(FeedbackController.class.getName());
    @Autowired
    private FeedbackService feedbackService;

    @RequestMapping(path = "all", method = RequestMethod.POST)
    List<FeedbackDTO> getAllFeedbacks(@RequestParam("text") String text,
                                      @RequestParam("rate") String rateString,
                                      @RequestParam("userName") String userName,
                                      @RequestParam("transporterName") String transporterName,
                                      @RequestParam("createdOn") String createdOnString,
                                      @RequestParam("approved") String approvedString,
                                      @RequestParam("sortBy") String sortBy,
                                      @RequestParam("sortDesc") String sortDesc,
                                      @RequestParam("currentPage") int currentPage,
                                      @RequestParam("itemsPerPage") int itemsPerPage) {

        logger.info("Before feedbackService.findFiltered()");
        return feedbackService.findFiltered(text, rateString, userName, transporterName, createdOnString,
                approvedString, sortBy, sortDesc, currentPage, itemsPerPage);
    }

    @RequestMapping(path = "totalItems", method = RequestMethod.GET)
    long getTotalItemsNumber() {
        logger.info("Before feedbackService.getTotalItemsNumber()");
        return feedbackService.getTotalItemsNumber();

    }

    @RequestMapping(path = {"updateFeedback"}, method = RequestMethod.PUT)
    void updateFeedback(@RequestBody FeedbackDTO feedbackDTO) {
        logger.info("Before feedbackService.update(feedbackDTO)");
        feedbackService.update(feedbackDTO);
        logger.info("After feedbackService.update(feedbackDTO)");
    }

    @RequestMapping(path = {"deleteFeedback/{feedbackId}"}, method = RequestMethod.DELETE)
    void deleteFeedback(@PathVariable Long feedbackId) {
        logger.info("Before feedbackService.delete(feedbackId)");
        feedbackService.delete(feedbackId);
        logger.info("After feedbackService.delete(feedbackDTO)");
    }
}