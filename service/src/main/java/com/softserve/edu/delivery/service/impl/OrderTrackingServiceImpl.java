package com.softserve.edu.delivery.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.edu.delivery.dto.OrderTrackingDTO;
import com.softserve.edu.delivery.service.OrderService;
import com.softserve.edu.delivery.service.OrderTrackingService;
import com.softserve.edu.delivery.service.RouteCityService;

@Service
@Transactional
public class OrderTrackingServiceImpl implements OrderTrackingService{

    @Autowired
    private OrderService orderService;
    
    @Autowired
    private RouteCityService routeCityService;
    
    @Override
    public OrderTrackingDTO getOrderTracking(Long orderId) {

        OrderTrackingDTO orderTrackingDTO = new OrderTrackingDTO();
        orderTrackingDTO.setOrderDto(orderService.getOrderById(orderId));
        orderTrackingDTO.setRouteCityDTOs(routeCityService.getRouteCitiesByOrderId(orderId));
        return orderTrackingDTO;
    }
    
}
