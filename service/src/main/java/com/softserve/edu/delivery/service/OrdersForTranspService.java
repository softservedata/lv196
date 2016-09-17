package com.softserve.edu.delivery.service;

import com.softserve.edu.delivery.domain.Order;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Ivan Synyshyn on 16.09.2016.
 */
public interface OrdersForTranspService {

    List<Order> getOrdersByCityFrom(String name);
    List<Order> getOrdersByCityTo(String name);
    List<Order> getOrdersByWeight(BigDecimal weight);
    List<Order> getOrdersByArriwalDate(Timestamp arrivalDate);

}
