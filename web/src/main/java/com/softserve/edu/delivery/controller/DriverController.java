package com.softserve.edu.delivery.controller;

import com.softserve.edu.delivery.dto.FeedbackDto;
import com.softserve.edu.delivery.dto.OrderDto;
import com.softserve.edu.delivery.service.FeedbackService;
import com.softserve.edu.delivery.service.OfferService;
import com.softserve.edu.delivery.service.OrderService;
import com.softserve.edu.delivery.service.UserAuthenticationDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.List;

import static com.softserve.edu.delivery.config.SecurityConstraints.DRIVER;

/**
 * Created by Ivan Synyshyn on 25.10.2016.
 */

@RestController
@RequestMapping(path = "driver")
public class DriverController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OfferService offerService;

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private UserAuthenticationDetails authenticationDetails;

    private final Logger logger = LoggerFactory.getLogger(DriverController.class.getName());

    @PreAuthorize(DRIVER)
    @RequestMapping(path = "open", method = RequestMethod.GET)
    List<OrderDto> open() {
        logger.info("Method DriverController.open()");
        return orderService.getAllOpenOrder();
    }

    @PreAuthorize(DRIVER)
    @RequestMapping (path = "filtered-orders", method = RequestMethod.GET)
    List<OrderDto> filter (@RequestParam (required = false) Long cityFromId,
                           @RequestParam (required = false) Long cityToId,
                           @RequestParam (required = false) Double weight,
                           @RequestParam (required = false) String date) {

        Timestamp arrivalDate = null;
        if (date != null && !date.isEmpty()) {
            try {
                arrivalDate = new Timestamp(Long.parseLong(date));
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Incorrect date");
            }
        }
        logger.info("Method DriverController.filter()");
        logger.info("cityFromId = " + cityFromId);                          //will delete it later!!!
        logger.info("cityToId = " + cityToId);                              //will delete it later!!!
        logger.info("weight = " + weight);                                  //will delete it later!!!
        logger.info("arrivalDate = " + arrivalDate);                        //will delete it later!!!
        return orderService.getOrdersFiltered(cityFromId, cityToId, weight, arrivalDate);
    }

    @PreAuthorize(DRIVER)
    @RequestMapping(path = "offer/{id}", method = RequestMethod.POST)
    void addOffer(@PathVariable Long id) {
        logger.info("Method DriverController.addOffer()");
        String email = authenticationDetails.getAuthenticatedUserEmail();
        offerService.addOffer(id, email);
    }

    @PreAuthorize(DRIVER)
    @RequestMapping(path = "my-offers", method = RequestMethod.GET)
    List<OrderDto> myOffers() {
        logger.info("Method DriverController.myOffers()");
        String email = authenticationDetails.getAuthenticatedUserEmail();
        return orderService.getOpenOrdersWithMyOffers(email);
    }

    @PreAuthorize(DRIVER)
    @RequestMapping(path = "my-orders-in-progress", method = RequestMethod.GET)
    List<OrderDto> myOrdersInProgress() {
        logger.info("Method DriverController.myOrdersInProgress()");
        String email = authenticationDetails.getAuthenticatedUserEmail();
        return orderService.getMyOrdersInProgress(email);
    }

    @PreAuthorize(DRIVER)
    @RequestMapping(path = "my-orders-closed", method = RequestMethod.GET)
    List<OrderDto> myOrdersClosed() {
        logger.info("Method DriverController.myOrdersClosed()");
        String email = authenticationDetails.getAuthenticatedUserEmail();
        return orderService.getMyOrdersClosed(email);
    }

    @PreAuthorize(DRIVER)
    @RequestMapping(path = "cancel-offer/{id}", method = RequestMethod.DELETE)
    void cancelOffer(@PathVariable Long id) {
        logger.info("Method DriverController.cancelOffer()");
        String email = authenticationDetails.getAuthenticatedUserEmail();
        offerService.cancelOffer(id, email);
    }

    @PreAuthorize(DRIVER)
    @RequestMapping(path = "customer-feedback/{id}", method = RequestMethod.GET)
    void customerFedback(@PathVariable Long id) {
        logger.info("Method DriverController.customerFedback()");
        logger.info("order id = " + id);
        feedbackService.getCustomerFeedback(id);
    }

    @PreAuthorize(DRIVER)
    @RequestMapping(path = "addfeedback", method = RequestMethod.POST)
    void addFeedback(@PathVariable FeedbackDto dto) {
        logger.info("Method DriverController.addFeedback()");
        String email = authenticationDetails.getAuthenticatedUserEmail();
        orderService.addFeedback(dto, email);
    }

    @RequestMapping(path = "offer-id/{orderId}", method = RequestMethod.GET)
    public Long getOfferId(@PathVariable Long orderId, Principal principal) {
        return offerService.findOfferId(orderId, principal.getName());
    }
}