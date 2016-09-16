package com.softserve.edu.delivery.service.impl;

import com.softserve.edu.delivery.dao.CityDao;
import com.softserve.edu.delivery.dao.OrderDao;
import com.softserve.edu.delivery.dao.UserDao;
import com.softserve.edu.delivery.domain.City;
import com.softserve.edu.delivery.domain.Order;
import com.softserve.edu.delivery.domain.OrderStatus;
import com.softserve.edu.delivery.domain.User;
import com.softserve.edu.delivery.dto.OrderForAddDto;
import com.softserve.edu.delivery.dto.OrderForListDto;
import com.softserve.edu.delivery.service.OrderService;
import com.softserve.edu.delivery.utils.TransactionManager;
import java.util.List;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;
    private final UserDao userDao;
    private final CityDao cityDao;

    public OrderServiceImpl(OrderDao orderDao, UserDao userDao, CityDao cityDao) {
        this.orderDao = orderDao;
        this.userDao = userDao;
        this.cityDao = cityDao;
    }

    @Override
    public List<OrderForListDto> findAllActiveOrders(String email, int page, int size) {
        return TransactionManager.withTransaction(() ->
                orderDao
                        .findAllOrdersByStatus(email, page, size, OrderStatus.ACTIVE)
                        .stream()
                        .map(OrderForListDto::of)
                        .collect(Collectors.toList())
        );
    }

    @Override
    public void addOrder(OrderForAddDto dto, String email) {
        TransactionManager.withTransaction(() -> {
            User user = userDao.findOne(email)
                    .orElseThrow(() -> new IllegalArgumentException("No such user with email: " + email));

            City from = cityDao.findOne(dto.getCityIdFrom())
                    .orElseThrow(() -> new IllegalArgumentException("No such city with id: " + dto.getCityIdFrom()));

            City to = cityDao.findOne(dto.getCityIdTo())
                    .orElseThrow(() -> new IllegalArgumentException("No such city with id: " + dto.getCityIdTo()));

            orderDao.save(new Order()
                    .setCustomer(user)
                    .setCityFrom(from)
                    .setCityTo(to)
                    .setArrivalDate(dto.getArrivalDate())
                    .setHeight(dto.getHeight())
                    .setWidth(dto.getWidth())
                    .setLength(dto.getLength())
                    .setWeight(dto.getWeight())
                    .setPrice(dto.getPrice())
                    .setDescription(dto.getDescription())
            );
        });
    }
}
