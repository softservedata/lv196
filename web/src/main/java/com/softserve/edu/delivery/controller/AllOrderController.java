package com.softserve.edu.delivery.controller;

import com.softserve.edu.delivery.dto.OfferDto;
import com.softserve.edu.delivery.dto.OrderForListDto;
import com.softserve.edu.delivery.service.OfferService;
import com.softserve.edu.delivery.service.OrderService;
import com.softserve.edu.delivery.service.UserAuthenticationDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    List<OrderForListDto> open() {
        logger.info("Method AllOrderController.open()");
        return orderService.getAllOpenOrder();
    }

    @RequestMapping(path = "filtered-by-city-from", method = RequestMethod.GET)
    List<OrderForListDto> filteredByCityFrom(@RequestParam String city) {
        logger.info("Method AllOrderController.filteredByCityFrom()");
        return orderService.getOrdersByCityFrom(city);
    }

    @RequestMapping(path = "filtered-by-city-to", method = RequestMethod.GET)
    List<OrderForListDto> filteredByCityTo(@RequestParam String city) {
        logger.info("Method AllOrderController.filteredByCityTo()");
        return orderService.getOrdersByCityTo(city);
    }

    @RequestMapping(path = "filtered-by-weight", method = RequestMethod.GET)
    List<OrderForListDto> filteredByWeight(@RequestParam String weight) {
        logger.info("Method AllOrderController.filteredByWeight()");
        BigDecimal converted = BigDecimal.valueOf(Double.parseDouble(weight));
        return orderService.getOrdersByWeight(converted);
    }

    @RequestMapping(path = "filtered-by-arrival-date", method = RequestMethod.GET)
    List<OrderForListDto> filteredByArriwalDate(@RequestParam String date) {
        logger.info("In method AllOrderController.filteredByArriwalDate()");
        SimpleDateFormat formater = new SimpleDateFormat("yyyy/MM/dd");
        Date parsed = null;
        try {
            parsed = formater.parse(date);
        } catch (ParseException e) {
            logger.error("Error parsing date in filteredByArriwalDate()");
            e.printStackTrace();
        }
        Timestamp timestamp = new Timestamp(parsed.getTime());
        logger.info("Out of method AllOrderController.filteredByArriwalDate()");
        return orderService.getOrdersByArriwalDate(timestamp);
    }

    @RequestMapping(path = "offer/{id}", method = RequestMethod.POST)
    void addOffer(@PathVariable Long id) {
        logger.info("Method AllOrderController.addOffer()");
        String email = "email2@gmail.com";                                  //will delete it later!!!
//        String email = authenticationDetails.getAuthenticatedUserEmail(); //will use it later.
        offerService.addOffer(id, email);
    }
}