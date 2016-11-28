package com.softserve.edu.delivery.service.impl;

import com.softserve.edu.delivery.domain.*;
import com.softserve.edu.delivery.dto.DataDto;
import com.softserve.edu.delivery.dto.FeedbackDto;
import com.softserve.edu.delivery.dto.OrderDto;
import com.softserve.edu.delivery.dto.OrderFilterDto;
import com.softserve.edu.delivery.repository.FeedbackRepository;
import com.softserve.edu.delivery.repository.OrderRepository;
import com.softserve.edu.delivery.repository.UserRepository;
import com.softserve.edu.delivery.repository.impl.DataRepositoryCustomImpl;
import com.softserve.edu.delivery.service.FeedbackService;
import com.softserve.edu.delivery.service.LocationService;
import com.softserve.edu.delivery.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private long totalItems;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private LocationService locationService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private DataRepositoryCustomImpl dataRepositoryCustom;

    @Override
    @Transactional(readOnly = true)
    public List<OrderDto> findInProgressOrders(String email) {
        return orderRepository
                .findOrderInProgressByCustomerEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDto> findOpenOrders(String email) {
        return orderRepository
                .findOrderOpenByCustomerEmail(email);
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
        Location from = locationService.persistLocation(dto.getLocationFrom());
        Location to = locationService.persistLocation(dto.getLocationTo());

        orderRepository.save(order
                .setLocationFrom(from)
                .setLocationTo(to)
                .setArrivalDate(dto.getArrivalDate())
                .setHeight(dto.getHeight())
                .setWidth(dto.getWidth())
                .setLength(dto.getLength())
                .setWeight(dto.getWeight())
                .setDescription(dto.getDescription()));
    }

    private void requireLocationsNonNull(Location from, Location to) {
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
    public List<OrderDto> getMyOrdersByStatus(String email, OrderStatus status) {
        return orderRepository
                .getMyOrdersByStatus(email, status)
                .stream()
                .map(OrderDto::of)
                .collect(Collectors.toList());
    }

    @Override
    public void approveDelivery(Long orderId) {
        Order order = orderRepository.findOne(orderId);
        orderRepository.save(order
                .setOrderStatus(OrderStatus.CLOSED)
                .setArrivalDate(new Timestamp(new Date().getTime())));
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
    public List<DataDto> countClosedOrdersPerHour(String date) {
    	String dateOfAnalyse = getDate(date);
    	return dataRepositoryCustom.countClosedOrdersPerHour(dateOfAnalyse);
    }
    
    @Override
    public List<DataDto> countClosedOrdersPerDay(Integer month) {
    	String monthOfAnalyse = getMonth(month);
    	return dataRepositoryCustom.countClosedOrdersPerDay(monthOfAnalyse);
    }
    
    @Override
    public List<DataDto> countClosedOrdersMonth() {
    	return dataRepositoryCustom.countClosedOrdersMonth();
    }
    
    private String getDate(String value) {
    	java.sql.Date date = new  java.sql.Date(Long.parseLong(value));
            return date.toString();  
    }
    
    private String getMonth(Integer month) {
		return LocalDate.of(LocalDate.now().getYear(), month, 1).toString();
    }


}
