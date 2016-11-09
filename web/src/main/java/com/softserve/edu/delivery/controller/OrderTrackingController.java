package com.softserve.edu.delivery.controller;

import com.softserve.edu.delivery.config.SecurityConstraints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.softserve.edu.delivery.dto.OrderTrackingDTO;
import com.softserve.edu.delivery.service.OrderTrackingService;

import static com.softserve.edu.delivery.config.SecurityConstraints.*;

@RestController
@RequestMapping(path = "order/tracking")
public class OrderTrackingController {
    
    @Autowired
    private OrderTrackingService orderTrackingService;

    @PreAuthorize(CUSTOMER)
    @RequestMapping(path = "{orderId}", method = RequestMethod.GET)
    public OrderTrackingDTO getOrderTracking(@PathVariable("orderId") Long orderId) {
        
        return orderTrackingService.getOrderTracking(orderId);
    }
    
}
