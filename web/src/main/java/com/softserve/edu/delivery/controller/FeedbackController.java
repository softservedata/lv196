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

    @RequestMapping(params = {"id"}, method = RequestMethod.GET)
    FeedbackDTO getFeedbackById(@RequestParam("id") String feedbackIdString) {
        logger.info("Method FeedbackController.getFeedbackById()");
        Long feedbackId = 0L;
        try{
            feedbackId = Long.parseLong(feedbackIdString.replaceAll("\\D+",""));
        }catch (IllegalArgumentException e){e.printStackTrace();}

        return feedbackService.getFeedbackById(feedbackId);
    }

    @RequestMapping(path = "id/greater-than", method = RequestMethod.GET)
    List<FeedbackDTO> findByFeedbackIdGreaterThan(@RequestParam("id") String feedbackIdString) {
        Long feedbackId = 0L;
        try{
            feedbackId = Long.parseLong(feedbackIdString.replaceAll("\\D+",""));
        }catch (IllegalArgumentException e){e.printStackTrace();}

        return feedbackService.findByFeedbackIdGreaterThan(feedbackId);
    }

    @RequestMapping(path = "id/less-than", method = RequestMethod.GET)
    List<FeedbackDTO> findByFeedbackIdLessThan(@RequestParam("id") String feedbackIdString) {
        Long feedbackId = 0L;
        try{
            feedbackId = Long.parseLong(feedbackIdString.replaceAll("\\D+",""));
        }catch (IllegalArgumentException e){e.printStackTrace();}

        return feedbackService.findByFeedbackIdLessThan(feedbackId);
    }

    @RequestMapping(path = "text", method = RequestMethod.GET)
    List<FeedbackDTO> getFeedbackByText(@RequestParam("text") String feedbackText) {
        return feedbackService.findByTextContaining(feedbackText);
    }

    @RequestMapping(path = "rate", method = RequestMethod.GET)
    List<FeedbackDTO> findByRate(@RequestParam("rate") String rateString) {
        Integer rate = 0;
        try{
            rate = Integer.parseInt(rateString.replaceAll("\\D+",""));
        }catch (IllegalArgumentException e){e.printStackTrace();}

        return feedbackService.findByRate(rate);
    }

    @RequestMapping(path = "rate/greater-than", method = RequestMethod.GET)
    List<FeedbackDTO> findByRateGreaterThan(@RequestParam("rate") String rateString) {
        Integer rate = 0;
        try{
            rate = Integer.parseInt(rateString.replaceAll("\\D+",""));
        }catch (IllegalArgumentException e){e.printStackTrace();}

        return feedbackService.findByRateGreaterThan(rate);
    }

    @RequestMapping(path = "rate/less-than", method = RequestMethod.GET)
    List<FeedbackDTO> findByRateLessThan(@RequestParam("rate") String rateString) {
        Integer rate = 0;
        try{
            rate = Integer.parseInt(rateString.replaceAll("\\D+",""));
        }catch (IllegalArgumentException e){e.printStackTrace();}

        return feedbackService.findByRateLessThan(rate);
    }

    @RequestMapping(path = "userName", method = RequestMethod.GET)
    List<FeedbackDTO> getFeedbackByUserName(@RequestParam("userName") String userName) {
        return feedbackService.findByUserFirstNameOrLastName(userName);
    }

    @RequestMapping(path = "transporterName", method = RequestMethod.GET)
    List<FeedbackDTO> getFeedbackByTransporterName(@RequestParam("transporterName") String transporterName) {
        return feedbackService.findByTransporterFirstNameOrLastName(transporterName);
    }


    @RequestMapping(path = {"changeFeedbackStatus"}, method = RequestMethod.PUT)
    void changeFeedbackStatus(@RequestBody FeedbackDTO feedbackDTO){
        logger.info("In method FeedbackController.changeFeedbackStatus()");
        feedbackService.update(feedbackDTO);
    }
}