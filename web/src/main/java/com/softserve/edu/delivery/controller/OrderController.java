package com.softserve.edu.delivery.controller;

import com.softserve.edu.delivery.dto.FeedbackDTO;
import com.softserve.edu.delivery.dto.OfferDtoForList;
import com.softserve.edu.delivery.dto.OrderDto;
import com.softserve.edu.delivery.service.NotificationService;
import com.softserve.edu.delivery.service.OrderService;
import com.softserve.edu.delivery.service.UserAuthenticationDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import static com.softserve.edu.delivery.config.SecurityConstraints.CUSTOMER_OR_DRIVER;

@RestController
@RequestMapping(path = "order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private NotificationService notification;
    @Autowired
    private UserAuthenticationDetails authenticationDetails;

    Logger logger = LoggerFactory.getLogger(OrderController.class.getName());

    @PreAuthorize(CUSTOMER_OR_DRIVER)
    @RequestMapping(path = "in-progress", method = RequestMethod.GET)
    List<OrderDto> inProgress(Principal principal) {
        return orderService.findInProgressOrders(principal.getName());
    }

    @PreAuthorize(CUSTOMER_OR_DRIVER)
    @RequestMapping(path = "open", method = RequestMethod.GET)
    List<OrderDto> open(Principal principal) {
        return orderService.findOpenOrders(principal.getName());
    }

    @PreAuthorize(CUSTOMER_OR_DRIVER)
    @RequestMapping(path = "closed", method = RequestMethod.GET)
    List<OrderDto> closed(Principal principal) {
        return orderService.findAllClosedOrders(principal.getName());
    }

    @PreAuthorize(CUSTOMER_OR_DRIVER)
    @RequestMapping(method = RequestMethod.POST)
    void addOrder(@RequestBody OrderDto dto, Principal principal) {
        orderService.addOrder(dto, principal.getName());
    }

    @PreAuthorize(CUSTOMER_OR_DRIVER)
    @RequestMapping(method = RequestMethod.PUT)
    void updateOrder(@RequestBody OrderDto dto, Principal principal) {
        orderService.updateOrder(dto, principal.getName());
    }

    @PreAuthorize(CUSTOMER_OR_DRIVER)
    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    void removeOrder(@PathVariable Long id) {
        notification.removeOrder(id);
        orderService.removeOrder(id);
    }

    @PreAuthorize(CUSTOMER_OR_DRIVER)
    @RequestMapping(path = "addfeedback", method = RequestMethod.POST)
    void addFeedback(@RequestBody FeedbackDTO dto, Principal principal) {
        logger.info("Method OrderController.addFeedback()");
        orderService.addFeedback(dto, principal.getName());
    }

    @PreAuthorize(CUSTOMER_OR_DRIVER)
    @RequestMapping(path = "addfeedback", method = RequestMethod.PUT)
    void updateFeedback(@RequestBody FeedbackDTO dto, Principal principal) {
        logger.info("Method OrderController.updateFeedback()");
        orderService.updateFeedback(dto, principal.getName());
    }

    @PreAuthorize(CUSTOMER_OR_DRIVER)
    @RequestMapping(path = "getFeedback/{id}", method = RequestMethod.GET)
    FeedbackDTO getFeedback(@PathVariable Long id) {
        logger.info("Method OrderController.getFeedback()");
        return orderService.getFeedback(id);
    }

    @PreAuthorize(CUSTOMER_OR_DRIVER)
    @RequestMapping(path = "change", method = RequestMethod.PUT)
    void changeOfferStatus(@RequestBody OfferDtoForList offerDto) {
        logger.info("Method OrderController.changeOfferStatus()");
        orderService.changeStatus(offerDto.getOfferId(),offerDto.isApproved(),offerDto.getOrderId());
        notification.changeOfferStatus(offerDto);
    }

    @PreAuthorize(CUSTOMER_OR_DRIVER)
    @RequestMapping(path = "offers/{id}", method = RequestMethod.GET)
    List<OfferDtoForList> getOffersByOrderId(@PathVariable Long id) {
        logger.info("Method OrderController.getOfferByOrderId()");
        return orderService.getOffersByOrderId(id);
    }
}
