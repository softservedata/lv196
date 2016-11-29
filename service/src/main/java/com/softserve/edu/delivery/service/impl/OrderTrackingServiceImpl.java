package com.softserve.edu.delivery.service.impl;

import javax.transaction.Transactional;

import com.softserve.edu.delivery.domain.RouteCities;
import com.softserve.edu.delivery.dto.PlaceDto;
import com.softserve.edu.delivery.repository.OrderRepository;
import com.softserve.edu.delivery.repository.RouteCitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.edu.delivery.dto.OrderTrackingDTO;
import com.softserve.edu.delivery.service.OrderService;
import com.softserve.edu.delivery.service.OrderTrackingService;
import com.softserve.edu.delivery.service.RouteCityService;

import java.util.Optional;

@Service
@Transactional
public class OrderTrackingServiceImpl implements OrderTrackingService{

    @Autowired
    private OrderService orderService;

    @Autowired
    private RouteCityService routeCityService;

    @Autowired
    private RouteCitiesRepository routeCityRepository;

   @Override
    public OrderTrackingDTO getOrderTracking(Long orderId) {
       OrderTrackingDTO orderTrackingDTO = new OrderTrackingDTO();
       orderTrackingDTO.setOrderDto(orderService.getOrderById(orderId));
       orderTrackingDTO.setRouteCityDTOs(routeCityService.getRouteCitiesByOrderId(orderId));
       return orderTrackingDTO;
   }
   @Override
   public PlaceDto getCurentLocation(Long id){
       return PlaceDto.convertEntity(routeCityRepository.findCurrentLocation(id)
               .orElseThrow(() -> new IllegalArgumentException("No such  points with order id: " + id)));
   }
}
