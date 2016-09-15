package com.softserve.edu.delivery.service.impl;

import com.softserve.edu.delivery.dao.OrderDao;
import com.softserve.edu.delivery.domain.Order;
import com.softserve.edu.delivery.domain.OrderStatus;
import com.softserve.edu.delivery.service.OrderService;
import com.softserve.edu.delivery.utils.TransactionManager;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;

    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public List<Order> findAllActiveOrders(int page, int size) {
        return TransactionManager.withTransaction(() ->
            orderDao.findAllOrdersByStatus(page, size, OrderStatus.ACTIVE)
        );
    }
}
