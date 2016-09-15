package com.softserve.edu.delivery.dao;

import com.softserve.edu.delivery.domain.Order;
import com.softserve.edu.delivery.domain.OrderStatus;

import java.util.List;

public interface OrderDao extends BaseDao<Order, Long> {
    List<Order> findAllOrdersByStatus(String email, int page, int size, OrderStatus orderStatus);
}
