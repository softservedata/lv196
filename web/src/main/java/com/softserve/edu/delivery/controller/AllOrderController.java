package com.softserve.edu.delivery.controller;

import com.softserve.edu.delivery.dto.OrderDto;
import com.softserve.edu.delivery.service.OfferService;
import com.softserve.edu.delivery.service.OrderService;
import com.softserve.edu.delivery.service.UserAuthenticationDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Ivan Synyshyn on 05.10.2016.
 */

@RestController
@RequestMapping(path = "all-orders")
public class AllOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OfferService offerService;

    @Autowired
    private UserAuthenticationDetails authenticationDetails;

    private final Logger logger = LoggerFactory.getLogger(AllOrderController.class.getName());

    @RequestMapping(path = "open", method = RequestMethod.GET)
    List<OrderDto> open() {
        logger.info("Method AllOrderController.open()");
        return orderService.getAllOpenOrder();
    }

    @RequestMapping (path = "filtered-orders", method = RequestMethod.GET)
    List<OrderDto> filter (@RequestParam (required = false) Long cityFromId,
                           @RequestParam (required = false) Long cityToId,
                           @RequestParam (required = false) BigDecimal weight,
                           @RequestParam (required = false) String date) {

        Timestamp arrivalDate = null;
        if (date != null && !date.isEmpty()) {
            try {
                arrivalDate = new Timestamp(Long.parseLong(date));
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Incorrect date");
            }
        }
        logger.info("Method AllOrderController.filter()");
        logger.info("cityFromId = " + cityFromId);                          //will delete it later!!!
        logger.info("cityToId = " + cityToId);                              //will delete it later!!!
        logger.info("weight = " + weight);                                  //will delete it later!!!
        logger.info("arrivalDate = " + arrivalDate);                        //will delete it later!!!
        return orderService.getOrdersFiltered(cityFromId, cityToId, weight, arrivalDate);
    }

    @RequestMapping(path = "offer/{id}", method = RequestMethod.POST)
    void addOffer(@PathVariable Long id) {
        logger.info("Method AllOrderController.addOffer()");
        String email = "email2@gmail.com";                                  //will delete it later!!!
//        String email = authenticationDetails.getAuthenticatedUserEmail(); //will use it later.
        offerService.addOffer(id, email);
    }
}