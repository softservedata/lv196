package com.softserve.edu.delivery.service;

import java.util.List;

public interface OrderService {
    List<Order> findAllActiveOrders(String email, int page, int size);
}
