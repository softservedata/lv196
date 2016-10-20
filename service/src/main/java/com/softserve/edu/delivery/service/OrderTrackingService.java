package com.softserve.edu.delivery.service;

import com.softserve.edu.delivery.dto.OrderTrackingDTO;

public interface OrderTrackingService {

    OrderTrackingDTO getOrderTracking(Long orderId);
}

