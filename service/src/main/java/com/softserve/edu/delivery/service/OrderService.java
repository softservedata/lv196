package com.softserve.edu.delivery.service;

import com.softserve.edu.delivery.domain.OrderStatus;
import com.softserve.edu.delivery.dto.DataDto;
import com.softserve.edu.delivery.dto.OrderDto;
import com.softserve.edu.delivery.dto.OrderFilterDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {

    /**
     * Finds the list of in progress orders by customer with given email
     *
     * @param email - email of assigned customer
     * @return list of open orders for the relevant parameters
     */
    List<OrderDto> findInProgressOrders(String email);

    /**
     * Finds the list of open orders by customer with given email
     *
     * @param email - email of assigned customer
     * @return list of open orders for the relevant parameters
     */
    List<OrderDto> findOpenOrders(String email);

    /**
     * Creates new order based of given dto and assigns it to user with given email
     *
     * @param dto - order dto
     * @param email - of user to assign
     * @throws IllegalArgumentException if dto is null or no such user with given email
     * or city from/to could not be found
     */
    void addOrder(OrderDto dto, String email);

    /**
     * Updates existing order
     *
     * @param dto - order dto
     * @param email - of assigned user
     * @throws IllegalArgumentException if dto is null or no such user with given email
     * or city from/to could not be found
     */
    void updateOrder(OrderDto dto, String email);

    void removeOrder(Long id);

    List<OrderDto> findAllClosedOrders(String email);

    List<OrderDto> getOrdersFiltered (OrderFilterDto orderFilterDto);
    long getCountOfFilteredOrders();
    List<OrderDto> getAllOpenOrder(Pageable pageable);
    long getCountOfOrders();
    List<OrderDto> getMyOrdersClosed(String email);
    List<OrderDto> getMyOrdersByStatus(String email, OrderStatus status);
    void approveDelivery(Long orderId);

    OrderDto getOrderById(Long orderId);
    
    List<DataDto> countClosedOrdersPerHour(String date);
    List<DataDto> countClosedOrdersPerDay();
    List<DataDto> countClosedOrdersMonth();
}
