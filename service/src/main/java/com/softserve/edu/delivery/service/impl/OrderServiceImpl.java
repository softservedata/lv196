package com.softserve.edu.delivery.service.impl;

import com.softserve.edu.delivery.dao.CityDao;
import com.softserve.edu.delivery.dao.OrderDao;
import com.softserve.edu.delivery.domain.*;
import com.softserve.edu.delivery.dto.CityDto;
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
                        .setAmountOfOffers(offerRepository.countByOrderIdAndCarDriverBlocked(order.getId(),false))
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

    @Transactional(readOnly = true)
    public Integer checkFeedback (Long orderId){
        return feedbackRepository.countFeedbacks(orderId);
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
                .map(order -> {
                    OrderDto dto = OrderDto.of(order);
                    String name = orderRepository
                            .findDriverNameByOrderId(dto.getId())
                            .orElse(null);
                    String carPhoto = orderRepository
                            .findCarPhotoByOrderId(dto.getId())
                            .orElse(null);
                    dto.setDriverName(name);
                    dto.setCarPhoto(carPhoto);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<OfferDtoForList> getOffersByOrderId(Long orderId) {
        return offerRepository
                .getAllOffersByOrderId(orderId)
                .stream()
                .map(offer -> {
                    OfferDtoForList dto = OfferDtoForList.offerToOfferDto(offer);
                    String name = orderRepository
                            .findDriverNameByOfferId(dto.getOfferId())
                            .orElse(null);
                    String carPhoto = orderRepository
                            .findCarPhotoByOrderId(dto.getOfferId())
                            .orElse(null);
                    Integer rate = orderRepository
                            .findRateByOfferId(dto.getOfferId())
                            .orElse(null);
                    dto.setDriverName(name);
                    dto.setCarPhoto(carPhoto);
                    dto.setRate(rate);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getOrdersFiltered(String cityFrom, String cityTo, String weight, String arrivalDate) {
        List<OrderDto> result = new ArrayList<>();
        Long cityFromId = getCityId(cityFrom);
        Long cityToId = getCityId(cityTo);
        BigDecimal mass = parseWeight(weight);
        Timestamp date = parseDate(arrivalDate);
        result.addAll(orderRepository.getOrdersFiltered(cityFromId, cityToId, mass, date).stream()
                .map(OrderDto::of).collect(Collectors.toList()));
        return result;
    }

    private Long getCityId (String txtCityId) {
        Long cityId = null;
        if (txtCityId != null && !txtCityId.isEmpty()) {
            cityId = Long.parseLong(txtCityId);
        }
        return cityId;
    }

    private BigDecimal parseWeight (String weight) {
        BigDecimal mass = null;
        if (weight != null && !weight.isEmpty()) {
            try {
                mass = BigDecimal.valueOf(Double.parseDouble(weight));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            if (mass.doubleValue() < 0.0) {
                throw new IllegalArgumentException("Incorrect weight");
            }
        }
        return mass;
    }

    private Timestamp parseDate (String date) {
        Timestamp arrivalDate = null;
        if (date != null && !date.isEmpty()) {
            try {
                arrivalDate = new Timestamp(Long.parseLong(date));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        return arrivalDate;
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
