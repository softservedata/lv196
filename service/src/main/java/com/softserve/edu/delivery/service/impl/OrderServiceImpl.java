package com.softserve.edu.delivery.service.impl;

import com.softserve.edu.delivery.domain.*;
import com.softserve.edu.delivery.dto.*;
import com.softserve.edu.delivery.repository.*;
import com.softserve.edu.delivery.service.FeedbackService;
import com.softserve.edu.delivery.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private long totalItems;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    FeedbackService feedbackService;

    @Override
    @Transactional(readOnly = true)
    public List<OrderDto> findInProgressOrders(String email) {
        return orderRepository
                .findOrderInProgressByCustomerEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDto> findOpenOrders(String email) {
        return Stream.of(
                orderRepository
                        .findOrderOpenWithOffersByCustomerEmail(email),
                orderRepository
                        .findOrderOpenWithoutOffersByCustomerEmail(email))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    @Override
    public void addOrder(OrderDto dto, String email) {
        requireLocationsNonNull(dto.getLocationFrom(), dto.getLocationTo());

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
        requireLocationsNonNull(dto.getLocationFrom(), dto.getLocationTo());

        Order order = orderRepository.findOrderByIdAndCustomerEmail(dto.getId(), email)
                .orElseThrow(() -> new IllegalArgumentException(
                        "No such order with id: " + dto.getId() + " for user with email: " + email));

        saveOrder(order, dto);
    }

    private void saveOrder(Order order, OrderDto dto) {
        City from = cityRepository.findOneOpt(dto.getLocationFrom().getCityId())
                .orElseThrow(() -> new IllegalArgumentException("No such city with id: " + dto.getLocationFrom().getCityId()));

        City to = cityRepository.findOneOpt(dto.getLocationTo().getCityId())
                .orElseThrow(() -> new IllegalArgumentException("No such city with id: " + dto.getLocationTo().getCityId()));

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

    private void requireLocationsNonNull(LocationDto from, LocationDto to) {
        Objects.requireNonNull(from, "Departure city cannot be null");
        Objects.requireNonNull(to, "Arrival city cannot be null");
    }

    @Override
    public void removeOrder(Long id) {
        orderRepository.removeById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDto> findAllClosedOrders(String email) {
        return orderRepository
                .getAllClosedOrderByCustomerEmail(email);
    }

    @Override
    public List<OrderDto> getOrdersFiltered(OrderFilterDto orderFilterDto) {
        Pageable pageable = new PageRequest(orderFilterDto.getCurrentPage() - 1, orderFilterDto.getItemsPerPage());
        Page<Order> result = orderRepository
                .getOrdersFiltered(
                        orderFilterDto.getCityFromId(),
                        orderFilterDto.getCityToId(),
                        orderFilterDto.getWeight(),
                        orderFilterDto.getArrivalDate(),
                        pageable);
        totalItems = result.getTotalElements();
        return result
                .getContent()
                .stream()
                .map(OrderDto::of)
                .collect(Collectors.toList());
    }

    @Override
    public long getCountOfFilteredOrders() {
        return totalItems;
    }

    @Override
    public List<OrderDto> getAllOpenOrder(Pageable pageable) {
        return orderRepository
                .getAllOpenOrder(pageable)
                .stream()
                .map(OrderDto::of)
                .collect(Collectors.toList());
    }

    @Override
    public long getCountOfOrders() {
        return orderRepository.getCountOfOrders();
    }

    @Override
    public List<OrderDto> getOpenOrdersWithMyOffers(String email) {
        return orderRepository
                .getOpenOrdersWithMyOffers(email)
                .stream()
                .map(OrderDto::of)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getMyOrdersInProgress(String email) {
        return orderRepository
                .getMyOrdersInProgress(email)
                .stream()
                .map(OrderDto::of)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getMyOrdersClosed(String email) {
        List<Order> listOrders = orderRepository.getMyOrdersClosed(email);
        List<OrderDto> result = new ArrayList<>();
        for (Order order : listOrders) {
            Feedback feedBack = feedbackRepository.getCustomerFeedback(order.getId());
            if (feedBack != null) {
                FeedbackDto feedbackDTO = feedbackService.copyFeedbackToDTO(feedBack);
                result.add(OrderDto.orderAndFeedbackOf(order, feedbackDTO));
            } else result.add(OrderDto.of(order));
        }
        return result;
    }

    @Override
    public void approveDelivery(Long orderId) {
        Order order = orderRepository.findOne(orderId);
        orderRepository.save(order.setOrderStatus(OrderStatus.CLOSED));
    }

    @Override
    public OrderDto getOrderById(Long orderId) {
        Order order = orderRepository.findOne(orderId);
        if (order == null) {
            return null;
        }
        return OrderDto.of(order);
    }

    @Override
    public List<Long> countOrdersByTime() {
        List<Long> ordersQuantity = new ArrayList<>();
        LocalDateTime end = LocalDateTime.now();
        LocalDateTime start = LocalDate.now().atStartOfDay();
        for (LocalDateTime date = start; date.isBefore(end); date = date.plusHours(1)) {
            ordersQuantity.add(orderRepository.countByArrivalDate(Timestamp.valueOf(date), Timestamp.valueOf(date.plusHours(1))));
        }
        return ordersQuantity;
    }

    @Override
    public List<String> getHoursToThisMoment() {
        LocalTime.now();
        LocalTime end = LocalTime.now();
        LocalTime start = LocalTime.MIDNIGHT.plusHours(1);
        return Stream.iterate(start, date -> date.plusHours(1))
                .limit(ChronoUnit.HOURS.between(start, end.plusMinutes(59).plusSeconds(59)))
                .map(Object::toString)
                .collect(Collectors.toList());
    }

    @Override
    public List<Long> countOrdersByDay() {
        List<Long> ordersQuantity = new ArrayList<>();
        LocalDateTime end = LocalDate.now().atStartOfDay();
        LocalDateTime start = LocalDate.now().atStartOfDay().with(TemporalAdjusters.firstDayOfMonth());
        for (LocalDateTime date = start; date.isBefore(end); date = date.plusDays(1)) {
            ordersQuantity.add(orderRepository.countByArrivalDate(Timestamp.valueOf(date),
                    Timestamp.valueOf(date.plusHours(23).plusMinutes(59).plusSeconds(59))));
        }
        return ordersQuantity;
    }

    @Override
    public List<String> getDaysToThisMoment() {
        LocalDate end = LocalDate.now();
        LocalDate start = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
        return Stream.iterate(start, date -> date.plusDays(1))
                .limit(ChronoUnit.DAYS.between(start, end))
                .map(Object::toString)
                .collect(Collectors.toList());
    }

    @Override
    public List<Long> countOrdersByMonth() {
        List<Long> ordersQuantity = new ArrayList<>();
        LocalDateTime end = LocalDate.now().atStartOfDay().with(TemporalAdjusters.firstDayOfMonth());
        LocalDateTime start = LocalDate.now().atStartOfDay().with(TemporalAdjusters.firstDayOfYear());
        for (LocalDateTime date = start; date.isBefore(end); date = date.plusMonths(1)) {
            ordersQuantity.add(orderRepository.countByArrivalDate(Timestamp.valueOf(date),
                    Timestamp.valueOf(date.with(TemporalAdjusters.lastDayOfMonth()))));
        }
        return ordersQuantity;
    }

    @Override
    public List<String> getMonthsToThisMoment() {
        LocalDateTime end = LocalDateTime.now();
        LocalDateTime start = LocalDate.now().atStartOfDay().with(TemporalAdjusters.firstDayOfYear());
        return Stream.iterate(start, date -> date.plusMonths(1))
                .limit(ChronoUnit.MONTHS.between(start, end))
                .map(LocalDateTime::getMonth)
                .map(Object::toString)
                .collect(Collectors.toList());
    }
}