package com.softserve.edu.delivery.service;

import com.softserve.edu.delivery.domain.Order;

import java.util.List;

/**
 * Created by Ivan Synyshyn on 16.09.2016.
 */
public interface OrdersForTranspService {

    List<Order> getOrdersToOffer();

}
