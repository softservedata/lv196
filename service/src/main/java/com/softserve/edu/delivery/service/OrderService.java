package com.softserve.edu.delivery.service;

import com.softserve.edu.delivery.dto.FeedbackDto;
import com.softserve.edu.delivery.dto.OfferDto;
import com.softserve.edu.delivery.dto.OrderDto;

import java.sql.Timestamp;
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

    List<OfferDto> getOffersByOrderId(Long id);

/**
* Author - Taras Kurdiukov
*/
    //Method for user story - "As customer I want to write transporter feedback."
    void addFeedback (FeedbackDto dto, String email);
    //Method for user story - "As customer I want to change offer status."
    void changeStatus(Long offerId, Boolean offerStatus, Long orderId);

    FeedbackDto getFeedback (Long orderId, String email);

    void updateFeedback(FeedbackDto dto, String email);

    List<OrderDto> findAllClosedOrders(String email);
/**
* Author - Ivan Synyshyn
*/
    //As transporter I want to choose orders by filter
    List<OrderDto> getOrdersFiltered (Long cityFrom, Long cityTo, Double weight, Timestamp arrivalDate);
    List<OrderDto> getAllOpenOrder();
    List<OrderDto> getOpenOrdersWithMyOffers(String email);
    List<OrderDto> getMyOrdersInProgress(String email);
    List<OrderDto> getMyOrdersClosed(String email);

    OrderDto getOrderById(Long orderId);
    
    List<Long> countOrdersByTime();
    
    List<String> getHoursToThisMoment();
    
    List<Long> countOrdersByDay();
    
    List<String> getDaysToThisMoment();
    
    List<Long> countOrdersByMonth();
    
    List<String> getMonthsToThisMoment();
}
