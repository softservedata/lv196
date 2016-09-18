package com.softserve.edu.delivery.service.impl;

import com.softserve.edu.delivery.dao.impl.CityDaoImpl;
import com.softserve.edu.delivery.dao.impl.OrderDaoImpl;
import com.softserve.edu.delivery.domain.City;
import com.softserve.edu.delivery.domain.Order;
import com.softserve.edu.delivery.service.OrdersForTranspService;
import com.softserve.edu.delivery.utils.Jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by Ivan Synyshyn on 16.09.2016.
 */
public class OrdersForTranspServiceImpl implements OrdersForTranspService {

    private OrderDaoImpl orderDao;

    public OrdersForTranspServiceImpl(OrderDaoImpl orderDao) {
        this.orderDao = orderDao;
    }

    public OrdersForTranspServiceImpl() {
    }

    Long cityId = Long.valueOf(0);
    CityDaoImpl cityDao = new CityDaoImpl();
    OrderDaoImpl order = new OrderDaoImpl();

    @Override
    public List<Order> getOrdersByCityFrom(String name) {

        if (name == null) {
            throw new IllegalArgumentException("Write name of city");
        }

        for (City city : cityDao.getCityByName(name)) {
            cityId = city.getCityId();
            if (cityId == 0) {
                throw new IllegalArgumentException("Incorrect name of city");
            }
        }
        return order.getOrderByCityFrom(cityId);
    }

    @Override
    public List<Order> getOrdersByCityTo(String name) {

        if (name == null) {
            throw new IllegalArgumentException("Write name of city");
        }

        for (City city : cityDao.getCityByName(name)) {
            cityId = city.getCityId();
            if (cityId == 0) {
                throw new IllegalArgumentException("Incorrect name of city");
            }
        }
        return order.getOrderByCityTo(cityId);
    }

    @Override
    public List<Order> getOrdersByWeight(BigDecimal weight) {
        if (weight.doubleValue() <= 0.0) {
            throw new IllegalArgumentException("Incorect weight");
        }
        return order.getOrderByWeight(weight);
    }

    @Override
    public List<Order> getOrdersByArriwalDate(Timestamp arrivalDate) {
        Date date = new Date();
        if (arrivalDate.getTime() < date.getTime()) {
            throw new IllegalArgumentException("Wrong date format");
        }
        return order.getOrderByArrivalDate(arrivalDate);
    }
}

