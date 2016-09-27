package com.softserve.edu.delivery.service.impl;

import com.softserve.edu.delivery.dao.*;
import com.softserve.edu.delivery.domain.*;
import com.softserve.edu.delivery.dto.FeedbackDTO;
import com.softserve.edu.delivery.dto.OrderForAddDto;
import com.softserve.edu.delivery.dto.OrderForListDto;
import com.softserve.edu.delivery.service.OrderService;
import com.softserve.edu.delivery.utils.TransactionManager;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;
    private final UserDao userDao;
    private final CityDao cityDao;
    private final FeedbackDao feedbackDao;
    private final OfferDao offerDao;

    public OrderServiceImpl(OrderDao orderDao, UserDao userDao, CityDao cityDao, FeedbackDao feedbackDao, OfferDao offerDao) {
        this.orderDao = orderDao;
        this.userDao = userDao;
        this.cityDao = cityDao;
        this.offerDao = offerDao;
        this.feedbackDao=feedbackDao;
    }

    @Override
    public List<OrderForListDto> findAllActiveOrders(String email, int page, int size) {
        return TransactionManager.withTransaction(() ->
                orderDao
                        .findAllOrdersByStatus(email, page, size, OrderStatus.ACTIVE)
                        .stream()
                        .map(OrderForListDto::of)
                        .map(dto -> {
                            String name = orderDao
                                    .findDriverByOrderId(dto.getId())
                                    .map(driver -> driver.getFirstName() + " " + driver.getLastName())
                                    .orElse(null);

                            return dto.setDriverName(name);
                        })
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
    public void addFeedback(FeedbackDTO dto, String email) {
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
    }

    @Override
    public void changeStatus(Long offerId, String offerStatus) {
        Boolean newOfferStatus=!(Boolean.parseBoolean(offerStatus));
        TransactionManager.withTransaction(() -> {
            Offer offer = offerDao.findOne(offerId)
                    .orElseThrow(() -> new IllegalArgumentException("No such user with email: " + offerId));
            offer.setApproved(newOfferStatus);
            offerDao.save(offer);
                }



        );
    }

    public Order findOne(Long orderId){
        return TransactionManager.withTransaction(() ->
                orderDao
                        .findOneOrder(orderId)
        );
    }

    public List<Order> findAll(){
        return TransactionManager.withTransaction(() ->
                orderDao
                        .findAllOrder()
        );
    }

    public List<Offer> getAllOffersByOrder(Order order){
        return TransactionManager.withTransaction(() ->
                offerDao
                        .getAllOffersByOrder(order)
        );
    }


/*--------------------IvanSynyshyn----------------------------*/
    @Override
    public List<OrderForListDto> getOrdersByCityFrom(String name) {
        List<OrderForListDto> result = new ArrayList<>();
        Long cityId = Long.valueOf(0);
        if (name == null) {
            throw new IllegalArgumentException("Write name of city");
        }
        for (City city : cityDao.getCityByName(name)) {
            cityId = city.getCityId();
            if (cityId == 0) {
                throw new IllegalArgumentException("Incorrect name of city");
            }
        }
        for (Order ord : orderDao.getOrderByCityFrom(cityId)) {
            result.add(OrderForListDto.of(ord));
        }
        return result;
    }
    @Override
    public List<OrderForListDto> getOrdersByCityTo(String name) {
        List<OrderForListDto> result = new ArrayList<>();
        Long cityId = Long.valueOf(0);
        if (name == null) {
            throw new IllegalArgumentException("Write name of city");
        }

        for (City city : cityDao.getCityByName(name)) {
            cityId = city.getCityId();
            if (cityId == 0) {
                throw new IllegalArgumentException("Incorrect name of city");
            }
        }
        for (Order ord : orderDao.getOrderByCityTo(cityId)) {
            result.add(OrderForListDto.of(ord));
        }
        return result;
    }

    @Override
    public List<OrderForListDto> getOrdersByWeight(BigDecimal weight) {
        List<OrderForListDto> result = new ArrayList<>();
        if (weight.doubleValue() <= 0.0) {
            throw new IllegalArgumentException("Incorect weight");
        }
        for (Order ord : orderDao.getOrderByWeight(weight)) {
            result.add(OrderForListDto.of(ord));
        }
        return result;
    }

    @Override
    public List<OrderForListDto> getOrdersByArriwalDate(Timestamp arrivalDate) {
        List<OrderForListDto> result = new ArrayList<>();
        Date date = new Date();
        if (arrivalDate.getTime() < date.getTime()) {
            throw new IllegalArgumentException("Wrong date format");
        }
        for (Order ord : orderDao.getOrderByArrivalDate(arrivalDate)) {
            result.add(OrderForListDto.of(ord));
        }
        return result;
    }

    @Override
    public List<Offer> addOffer(Long orderId, Offer offer) {
        Order order = orderDao.findOne(orderId).get();
        order.getOffers().add(offer);
        Order updated = orderDao.update(order);
        return updated.getOffers();
    }

}
