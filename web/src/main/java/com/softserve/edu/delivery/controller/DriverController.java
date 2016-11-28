package com.softserve.edu.delivery.controller;

import com.softserve.edu.delivery.domain.OrderStatus;
import com.softserve.edu.delivery.dto.FeedbackDto;
import com.softserve.edu.delivery.dto.OrderDto;
import com.softserve.edu.delivery.dto.OrderFilterDto;
import com.softserve.edu.delivery.service.FeedbackService;
import com.softserve.edu.delivery.service.OfferService;
import com.softserve.edu.delivery.service.OrderService;
import com.softserve.edu.delivery.service.NotificationService;
import com.softserve.edu.delivery.service.UserAuthenticationDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import static com.softserve.edu.delivery.config.SecurityConstraints.CUSTOMER_OR_DRIVER;
import static com.softserve.edu.delivery.config.SecurityConstraints.DRIVER;
import static com.softserve.edu.delivery.domain.OrderStatus.APPROVED;
import static com.softserve.edu.delivery.domain.OrderStatus.IN_PROGRESS;
import static com.softserve.edu.delivery.domain.OrderStatus.OPEN;

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

    @Autowired
    private NotificationService notification;

    private final Logger logger = LoggerFactory.getLogger(DriverController.class.getName());
    private String email;
    private OrderStatus status;

    @PreAuthorize(DRIVER)
    @RequestMapping(path = "open", method = RequestMethod.GET)
    List<OrderDto> open( @RequestParam Integer currentPage,
                         @RequestParam Integer itemsPerPage) {
        logger.info("Method DriverController.open()");
        Pageable pageable = new PageRequest(currentPage - 1, itemsPerPage);
        return orderService.getAllOpenOrder(pageable);
    }

    @PreAuthorize(DRIVER)
    @RequestMapping(path = "count-items", method = RequestMethod.GET)
    long countItems() {
        logger.info("Method DriverController.countItems()");
        return orderService.getCountOfOrders();
    }

    @PreAuthorize(DRIVER)
    @RequestMapping (path = "filtered-orders", method = RequestMethod.POST)
    List<OrderDto> filter (@RequestBody OrderFilterDto orderFilterDto){
        logger.info("Method DriverController.filter()");
        logger.info("orderFilterDto = " + orderFilterDto);
        return orderService.getOrdersFiltered(orderFilterDto);
    }

    @PreAuthorize(DRIVER)
    @RequestMapping(path = "count-items-filter", method = RequestMethod.GET)
    long countItemsFilter() {
        logger.info("Method DriverController.countItems()");
        return orderService.getCountOfFilteredOrders();
    }

    @PreAuthorize(DRIVER)
    @RequestMapping(path = "offer/{id}", method = RequestMethod.POST)
    void addOffer(@PathVariable Long id) {
        logger.info("Method DriverController.addOffer()");
        email = authenticationDetails.getAuthenticatedUserEmail();
        offerService.addOffer(id, email);
        notification.addOffer(id, email);
    }

    @PreAuthorize(DRIVER)
    @RequestMapping(path = "my-offers", method = RequestMethod.GET)
    List<OrderDto> myOffers() {
        logger.info("Method DriverController.myOffers()");
        email = authenticationDetails.getAuthenticatedUserEmail();
        status = OPEN;
        return orderService.getMyOrdersByStatus(email, status);
    }

    @PreAuthorize(DRIVER)
    @RequestMapping(path = "my-approved-orders", method = RequestMethod.GET)
    List<OrderDto> myApprovedOrders() {
        logger.info("Method DriverController.myApprovedOrders()");
        email = authenticationDetails.getAuthenticatedUserEmail();
        status = APPROVED;
        return orderService.getMyOrdersByStatus(email, status);
    }

    @PreAuthorize(DRIVER)
    @RequestMapping(path = "my-orders-in-progress", method = RequestMethod.GET)
    List<OrderDto> myOrdersInProgress() {
        logger.info("Method DriverController.myOrdersInProgress()");
        email = authenticationDetails.getAuthenticatedUserEmail();
        status = IN_PROGRESS;
        return orderService.getMyOrdersByStatus(email, status);
    }

    @PreAuthorize(DRIVER)
    @RequestMapping(path = "my-orders-closed", method = RequestMethod.GET)
    List<OrderDto> myOrdersClosed() {
        logger.info("Method DriverController.myOrdersClosed()");
        email = authenticationDetails.getAuthenticatedUserEmail();
        return orderService.getMyOrdersClosed(email);
    }

    @PreAuthorize(DRIVER)
    @RequestMapping(path = "cancel-offer/{id}", method = RequestMethod.DELETE)
    void cancelOffer(@PathVariable Long id) {
        logger.info("Method DriverController.cancelOffer()");
        email = authenticationDetails.getAuthenticatedUserEmail();
        offerService.cancelOffer(id, email);
    }

    @PreAuthorize(DRIVER)
    @RequestMapping(path = "addfeedback", method = RequestMethod.POST)
    void addFeedback(@PathVariable FeedbackDto dto) {
        logger.info("Method DriverController.addFeedback()");
        email = authenticationDetails.getAuthenticatedUserEmail();
        feedbackService.addFeedback(dto, email);
    }

    @RequestMapping(path = "offer-id/{orderId}", method = RequestMethod.GET)
    public Long getOfferId(@PathVariable Long orderId, Principal principal) {
        return offerService.findOfferId(orderId, principal.getName());
    }

    @PreAuthorize(DRIVER)
    @RequestMapping(path = "approve-delivery", method = RequestMethod.PUT)
    void approveDelivery(@RequestBody Long orderId) {
        notification.approveDelivery(orderId);
    }
}