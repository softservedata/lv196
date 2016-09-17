package com.softserve.edu.delivery.service.impl;

import com.softserve.edu.delivery.dao.OrderDao;
import com.softserve.edu.delivery.dao.impl.OrderDaoImpl;
import com.softserve.edu.delivery.domain.Order;
import com.softserve.edu.delivery.service.OrdersForTranspService;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Ivan Synyshyn on 16.09.2016.
 */
public class OrdersForTranspServiceImpl implements OrdersForTranspService {

    private OrderDaoImpl orderDao;

    public OrdersForTranspServiceImpl(OrderDaoImpl orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public List<Order> getOrdersByCityFrom(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Write name of city");
        }

        OrderDaoImpl order = new OrderDaoImpl();
        List<Order> orders = order.getOrderByCityFrom(name);
        return orders;
    }

    @Override
    public List<Order> getOrdersByCityTo(String name) {
        return null;
    }

    @Override
    public List<Order> getOrdersByWeight(BigDecimal weight) {
        return null;
    }

    @Override
    public List<Order> getOrdersByArriwalDate(Timestamp arrivalDate) {
        return null;
    }
}

