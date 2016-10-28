package com.softserve.edu.delivery.controller;

import com.softserve.edu.delivery.dto.OrderDto;
import com.softserve.edu.delivery.service.OfferService;
import com.softserve.edu.delivery.service.OrderService;
import com.softserve.edu.delivery.service.UserAuthenticationDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    private UserAuthenticationDetails authenticationDetails;

    private final Logger logger = LoggerFactory.getLogger(FindOrderController.class.getName());

    private final String email = "email2@gmail.com";                            //will delete it later!!!

    @RequestMapping(path = "my-offers", method = RequestMethod.GET)
    List<OrderDto> myOffers() {
        logger.info("Method DriverController.myOffers()");
//        String email = authenticationDetails.getAuthenticatedUserEmail();       //will use it later.
        return orderService.getOpenOrdersWithMyOffers(email);
    }

    @RequestMapping(path = "my-orders-in-progress", method = RequestMethod.GET)
    List<OrderDto> myOrdersInProgress() {
        logger.info("Method DriverController.myOrdersInProgress()");
//        String email = authenticationDetails.getAuthenticatedUserEmail();       //will use it later.
        return orderService.getMyOrdersInProgress(email);
    }

    @RequestMapping(path = "my-orders-closed", method = RequestMethod.GET)
    List<OrderDto> myOrdersClosed() {
        logger.info("Method DriverController.myOrdersClosed()");
//        String email = authenticationDetails.getAuthenticatedUserEmail();       //will use it later.
        return orderService.getMyOrdersClosed(email);
    }

    @RequestMapping(path = "cancel-offer/{id}", method = RequestMethod.DELETE)
    void cancelOffer(@PathVariable Long id) {
        logger.info("Method DriverController.cancelOffer()");
//        String email = authenticationDetails.getAuthenticatedUserEmail();       //will use it later.
        offerService.cancelOffer(id, email);
    }
}