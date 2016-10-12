package com.softserve.edu.delivery.service.impl;

import com.softserve.edu.delivery.dao.CityDao;
import com.softserve.edu.delivery.dao.OrderDao;
import com.softserve.edu.delivery.domain.*;
import com.softserve.edu.delivery.dto.FeedbackDTO;
import com.softserve.edu.delivery.dto.OfferDtoForList;
import com.softserve.edu.delivery.dto.OrderDto;
import com.softserve.edu.delivery.repository.*;
import com.softserve.edu.delivery.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderDao orderDao;
    private final CityDao cityDao;
    private final CityRepository cityRepository;
    private final OfferRepository offerRepository;
    private final UserRepository userRepository;
    private final FeedbackRepository feedbackRepository;


    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderDao orderDao, CityDao cityDao, CityRepository cityRepository, OfferRepository offerRepository, UserRepository userRepository, FeedbackRepository feedbackRepository) {
        this.orderRepository = orderRepository;
        this.orderDao = orderDao;
        this.cityDao = cityDao;
        this.cityRepository = cityRepository;
        this.offerRepository = offerRepository;
        this.userRepository = userRepository;
        this.feedbackRepository = feedbackRepository;

    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDto> findInProgressOrders(String email) {
        return orderRepository
                .findOrderByCustomerEmailAndOrderStatus(email, OrderStatus.IN_PROGRESS)
                .stream()
                .map(order -> {
                    OrderDto dto = OrderDto.of(order);
                    String name = orderRepository
                            .findDriverNameByOrderId(dto.getId())
                            .orElse(null);
                    return dto.setDriverName(name);
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDto> findOpenOrders(String email) {
        return orderRepository
                .findOrderByCustomerEmailAndOrderStatus(email, OrderStatus.OPEN)
                .stream()
                .map(order -> OrderDto.of(order)
                        .setNumberOfOffers(offerRepository.countByOrderId(order.getId()))
                )
                .collect(Collectors.toList());
    }


    @Override
    public void addOrder(OrderDto dto, String email) {
        User customer = userRepository.findOneOpt(email)
                .orElseThrow(() -> new IllegalArgumentException("No such user with email: " + email));

        Order order = new Order()
                .setCustomer(customer)
                .setOrderStatus(OrderStatus.OPEN)
                .setRegistrationDate(new Timestamp(new Date().getTime()));

        saveOrder(order, dto);
    }

    @Override
    public void updateOrder(OrderDto dto, String email) {
        Order order = orderRepository.findOneOpt(dto.getId())
                .orElseThrow(() -> new IllegalArgumentException("No such order with id: " + dto.getId()));

        if (!order.getCustomer().getEmail().equals(email)) {
            throw new IllegalArgumentException("User with email: " + email + " cannot edit order with id: " + dto.getId());
        }

        saveOrder(order, dto);
    }

    private void saveOrder(Order order, OrderDto dto) {
        City from = cityRepository.findOneOpt(dto.getLocationFrom().getCityId())
                .orElseThrow(() -> new IllegalArgumentException("No such city with id: " + dto.getLocationFrom().getCityId()));

        City to = cityRepository.findOneOpt(dto.getLocationFrom().getCityId())
                .orElseThrow(() -> new IllegalArgumentException("No such city with id: " + dto.getLocationFrom().getCityId()));

        orderRepository.save(order
                .setCityFrom(from)
                .setCityTo(to)
                .setArrivalDate(dto.getArrivalDate())
                .setHeight(dto.getHeight())
                .setWidth(dto.getWidth())
                .setLength(dto.getLength())
                .setWeight(dto.getWeight())
                .setDescription(dto.getDescription()));
    }

    @Override
    public void removeOrder(Long id) {
        orderRepository.removeById(id);
    }

    @Override
    public void addFeedback(FeedbackDTO dto, String email) {
        if (dto == null) {
            throw new IllegalArgumentException("Feedback dto must not be null");
        }

        User user = userRepository.findOneOpt(email)
                .orElseThrow(() -> new IllegalArgumentException("No such user with email: " + email));
        /*changed by Ivan Rudnytskyi - the structure of FeedbackDTO was changed - entities Order and User are removed.
        *to get User use feedbackDTO.getUserId(), Order - feedbackDTO.getOrderId()
         */
        Order order = orderRepository.findOneOpt(dto.getOrderId())
                .orElseThrow(() -> new IllegalArgumentException("No such order with id: " + dto.getOrderId()));

        Feedback feedback = new Feedback();
        feedback.setOrder(order);
        feedback.setUser(user);
        feedback.setRate(dto.getRate());
        feedback.setText(dto.getText());
        feedback.setApproved(false);
        feedback.setCreatedOn(new Timestamp(new Date().getTime()));
        feedbackRepository.save(feedback);
    }

    public void changeStatus(Long offerId, Boolean offerStatus, Long orderId) {
        offerRepository.findOfferByOrderIdAndChangeStatus(orderId);
        Boolean newOfferStatus = !offerStatus;
        Offer offer = offerRepository.findOneOpt(offerId)
                .orElseThrow(() -> new IllegalArgumentException("No such user with email: " + offerId));
        offer.setApproved(newOfferStatus);
        offerRepository.save(offer);

    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDto> findAllClosedOrders(String email) {
        return orderDao
                .findClosedOrders(email)
                .stream()
                .map(OrderDto::of)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<OfferDtoForList> getOffersByOrderId(Long orderId) {
        return offerRepository
                .getAllOffersByOrderId(orderId)
                .stream()
                .map(OfferDtoForList::offerToOfferDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getOrdersByCityFrom(String name) {
        List<OrderDto> result = new ArrayList<>();
        Long cityId = 0L;
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
            result.add(OrderDto.of(ord));
        }
        return result;
    }

    @Override
    public List<OrderDto> getOrdersByCityTo(String name) {
        List<OrderDto> result = new ArrayList<>();
        Long cityId = 0L;
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
            result.add(OrderDto.of(ord));
        }
        return result;
    }

    @Override
    public List<OrderDto> getOrdersByWeight(BigDecimal weight) {
        List<OrderDto> result = new ArrayList<>();
        if (weight.doubleValue() <= 0.0) {
            throw new IllegalArgumentException("Incorect weight");
        }
        for (Order ord : orderDao.getOrderByWeight(weight)) {
            result.add(OrderDto.of(ord));
        }
        return result;
    }

    @Override
    public List<OrderDto> getOrdersByArriwalDate(Timestamp arrivalDate) {
        List<OrderDto> result = new ArrayList<>();
        Date date = new Date();
        if (arrivalDate.getTime() < date.getTime()) {
            throw new IllegalArgumentException("Wrong date format");
        }
        for (Order ord : orderDao.getOrderByArrivalDate(arrivalDate)) {
            result.add(OrderDto.of(ord));
        }
        return result;
    }

    @Override
    public List<OrderDto> getAllOpenOrder() {
        return orderRepository
                .getAllOpenOrder()
                .stream()
                .map(OrderDto::of)
                .collect(Collectors.toList());
    }
}
