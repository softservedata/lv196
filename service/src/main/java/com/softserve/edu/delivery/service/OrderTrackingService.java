package com.softserve.edu.delivery.service;

import com.softserve.edu.delivery.dto.OrderTrackingDTO;
import com.softserve.edu.delivery.dto.PlaceDto;

public interface OrderTrackingService {

    OrderTrackingDTO getOrderTracking(Long orderId);
    PlaceDto getCurentLocation(Long id);
}

