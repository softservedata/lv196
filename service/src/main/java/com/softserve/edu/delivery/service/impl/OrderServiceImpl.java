package com.softserve.edu.delivery.service.impl;

import com.softserve.edu.delivery.dao.CityDao;
import com.softserve.edu.delivery.dao.FeedbackDao;
import com.softserve.edu.delivery.dao.OrderDao;
import com.softserve.edu.delivery.dao.UserDao;
import com.softserve.edu.delivery.domain.*;
import com.softserve.edu.delivery.dto.FeedbackDTO;
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
    private final FeedbackDao feedbackDao;

    public OrderServiceImpl(OrderDao orderDao, UserDao userDao, CityDao cityDao, FeedbackDao feedbackDao) {
        this.orderDao = orderDao;
        this.userDao = userDao;
        this.cityDao = cityDao;
        this.feedbackDao=feedbackDao;
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

    @Override
    public String addFeedback(FeedbackDTO dto, String email) {
        TransactionManager.withTransaction(() -> {
            User user = userDao.findOne(email)
                    .orElseThrow(() -> new IllegalArgumentException("No such user with email: " + email));
            Order order = orderDao.findOne(dto.getOrder().getId())
                    .orElseThrow(() -> new IllegalArgumentException("No such order with id: " + dto.getOrder().getId()));

            Feedback feedback = new Feedback();
            feedback.setOrder(order);
            feedback.setUser(user);
            feedback.setRate(dto.getRate());
            feedback.setText(dto.getText());
            feedback.setApproved(false);
            feedbackDao.save(feedback);
        });
        return dto.getText();
    }

    @Override
    public List<OrderForListDto> changeStatus(String order_id, Boolean offerStatus) {
        return TransactionManager.withTransaction(() ->
                orderDao
                        .changeStatus(order_id, offerStatus)
                        .stream()
                        .map(OrderForListDto::of)
                        .collect(Collectors.toList())
        );
    }


}
