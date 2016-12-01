package com.softserve.edu.delivery.service.impl;

import com.softserve.edu.delivery.domain.Order;
import com.softserve.edu.delivery.domain.OrderStatus;
import com.softserve.edu.delivery.domain.Point;
import com.softserve.edu.delivery.domain.RouteCities;
import com.softserve.edu.delivery.dto.OrderClosedDto;
import com.softserve.edu.delivery.dto.OrderDto;
import com.softserve.edu.delivery.dto.PlaceDto;
import com.softserve.edu.delivery.dto.RoutesDto;
import com.softserve.edu.delivery.repository.OrderRepository;
import com.softserve.edu.delivery.repository.RouteCitiesRepository;
import com.softserve.edu.delivery.service.TransporterService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.softserve.edu.delivery.domain.OrderStatus.*;

@Service
@Transactional
public class TransporterServiceImpl implements TransporterService {
    private final RouteCitiesRepository routeCityRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public TransporterServiceImpl(RouteCitiesRepository routeCityRepository, OrderRepository orderRepository) {
        this.routeCityRepository = routeCityRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public List<PlaceDto> getAllPointsByOrderIdWithoutDate(Long id) {
        return routeCityRepository.findByOrderId(id)
                .stream()
                .map(entity -> PlaceDto.convertEntityWithoutDate(entity))
                .collect(Collectors.toList());
    }

    @Override
    public RoutesDto getMyRoute(Long id) {
        OrderDto orderDto = orderRepository.findOneByIdAndOrderStatus(id, IN_PROGRESS)
                .orElseThrow(() -> new IllegalArgumentException("No such order with id: " + id + " and order status" + IN_PROGRESS));
        RouteCities routeCities = routeCityRepository.findCurrentLocation(id)
                .orElseThrow(() -> new IllegalArgumentException("No such  points with order id: " + id));
        return RoutesDto.convertEntity(orderDto, routeCities);
    }

    @Override
    public List<RoutesDto> getAllOrdersInProgress() {
        List<OrderDto> list = orderRepository.findAllByOrderStatus(IN_PROGRESS);
        List<RoutesDto> resultList = new ArrayList<>();
        for (OrderDto o : list) {
            RouteCities routeCities = routeCityRepository.findCurrentLocation(o.getId())
                    .orElseThrow(() -> new IllegalArgumentException("No such point with order id: " + o.getId()));
            resultList.add(RoutesDto.convertEntity(o, routeCities));
        }
        return resultList;
    }

    @Override
    public List<OrderClosedDto> getAllOrdersClosedByDates(Timestamp min, Timestamp max) {
        List<OrderDto> orders = orderRepository.findByOrderStatusAndArrivalDateBetween(min, max);
        List<OrderClosedDto> list = orders
                .stream()
                .map(entity -> OrderClosedDto.convertEntity(entity))
                .collect(Collectors.toList());

        return list;

    }

    @Override
    public OrderClosedDto findOneByIdAndOrderStatus(Long id) {
        OrderDto order = orderRepository.findOneByIdAndOrderStatus(id, APPROVED)
                .orElseThrow(() -> new IllegalArgumentException("No such order with id: " + id + " and order status" + APPROVED));
        return OrderClosedDto.convertEntity(order);
    }

    @Override
    public void setPoints(Long orderId, List<Point> points) {
        points.forEach(point -> routeCityRepository.save(new RouteCities(orderId, point)));
    }

    @Override
    public void changeStatus(Long id, OrderStatus status) {
        orderRepository.changeStatus(id, status);
    }
@Override
   public List<Long> getAllIdInProgress() {
        List<OrderDto> list = orderRepository.findAllByOrderStatus(IN_PROGRESS);
        List<Long> res = new ArrayList<>();
        for(OrderDto o: list){
            res.add(o.getId());
        }
        return res;
    }
}