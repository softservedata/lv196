package com.softserve.edu.delivery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.softserve.edu.delivery.dto.OrderTrackingDTO;
import com.softserve.edu.delivery.service.OrderTrackingService;


@RestController
@RequestMapping(path = "order/tracking")

public class OrderTrackingController {
    
    @Autowired
    private OrderTrackingService orderTrackingService;
    
    @RequestMapping(path = "{orderId}", method = RequestMethod.GET)
    public OrderTrackingDTO getOrderTracking(@PathVariable("orderId") Long orderId) {
        
        return orderTrackingService.getOrderTracking(orderId);
    }
    
}
