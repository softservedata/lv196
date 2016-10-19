package com.softserve.edu.delivery.controller;

import com.softserve.edu.delivery.dto.FeedbackDTO;
import com.softserve.edu.delivery.dto.OfferDtoForList;
import com.softserve.edu.delivery.dto.OrderDto;
import com.softserve.edu.delivery.service.OrderService;
import com.softserve.edu.delivery.service.UserAuthenticationDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserAuthenticationDetails authenticationDetails;

    Logger logger = LoggerFactory.getLogger(OrderController.class.getName());

    @RequestMapping(path = "in-progress", method = RequestMethod.GET)
    List<OrderDto> inProgress() {
        String email = "martin@gmail.com";
        return orderService.findInProgressOrders(email);
    }

    @RequestMapping(path = "open", method = RequestMethod.GET)
    List<OrderDto> open() {
        String email = "martin@gmail.com";
        return orderService.findOpenOrders(email);
    }

    @RequestMapping(path = "closed", method = RequestMethod.GET)
    List<OrderDto> closed() {
//        String email = authenticationDetails.getAuthenticatedUserEmail();
        String email = "martin@gmail.com";
        return orderService.findAllClosedOrders(email);
    }

    @RequestMapping(method = RequestMethod.POST)
    void addOrder(@RequestBody OrderDto dto) {
        String email = "martin@gmail.com";
        orderService.addOrder(dto, email);
    }

    @RequestMapping(method = RequestMethod.PUT)
    void updateOrder(@RequestBody OrderDto dto) {
        String email = "martin@gmail.com";
        orderService.updateOrder(dto, email);
    }

    @RequestMapping(path = "addfeedback", method = RequestMethod.POST)
    void addFeedback(@RequestBody FeedbackDTO dto) {
        logger.info("Method OrderController.addFeedback()");
//        String email = authenticationDetails.getAuthenticatedUserEmail();
        String email = "martin@gmail.com";
        orderService.addFeedback(dto, email);
    }

    @RequestMapping(path = "addfeedback", method = RequestMethod.PUT)
    void updateFeedback(@RequestBody FeedbackDTO dto) {
        logger.info("Method OrderController.updateFeedback()");
//        String email = authenticationDetails.getAuthenticatedUserEmail();
        String email = "martin@gmail.com";
        orderService.updateFeedback(dto, email);
    }

    @RequestMapping(path = "getFeedback/{id}", method = RequestMethod.GET)
    FeedbackDTO getFeedback(@PathVariable Long id) {
        logger.info("Method OrderController.getFeedback()");
        return orderService.getFeedback(id);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    void removeOrder(@PathVariable Long id) {
        orderService.removeOrder(id);
    }

    @RequestMapping(path = "change", method = RequestMethod.PUT)
    void changeOfferStatus(@RequestBody OfferDtoForList offerDto) {
        logger.info("Method OrderController.changeOfferStatus()");
        orderService.changeStatus(offerDto.getOfferId(),offerDto.isApproved(),offerDto.getOrderId());
    }

    @RequestMapping(path = "offers/{id}", method = RequestMethod.GET)
    List<OfferDtoForList> getOffersByOrderId(@PathVariable Long id) {
        logger.info("Method OrderController.getOfferByOrderId()");
        return orderService.getOffersByOrderId(id);
    }
}
