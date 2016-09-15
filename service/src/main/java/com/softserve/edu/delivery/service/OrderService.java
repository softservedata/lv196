package com.softserve.edu.delivery.service;

import com.softserve.edu.delivery.domain.Order;

import java.util.List;

public interface OrderService {
    List<Order> findAllActiveOrders(int page, int size);
}
