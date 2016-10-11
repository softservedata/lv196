package com.softserve.edu.delivery.service;

import com.softserve.edu.delivery.dto.OfferDtoForList;
import com.softserve.edu.delivery.dto.FeedbackDTO;
import com.softserve.edu.delivery.dto.OfferDto;
import com.softserve.edu.delivery.dto.OrderForAddDto;
import com.softserve.edu.delivery.dto.OrderForListDto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public interface OrderService {

    /**
     * Finds the list of in progress orders by customer with given email
     *
     * @param email - email of assigned customer
     * @return list of open orders for the relevant parameters
     */
    List<OrderForListDto> findInProgressOrders(String email);

    /**
     * Finds the list of open orders by customer with given email
     *
     * @param email - email of assigned customer
     * @return list of open orders for the relevant parameters
     */
    List<OrderForListDto> findOpenOrders(String email);

    /**
     * Creates new order based of given dto and assigns it to user with given email
     *
     * @param dto - order dto
     * @param email - of user to assign
     * @throws IllegalArgumentException if dto is null or no such user with given email
     * or city from/to could not be found
     */
    void addOrder(OrderForAddDto dto, String email);

    void removeOrder(Long id);

    List<OfferDtoForList> getOffersByOrderId(Long id);

/**
* Author - Taras Kurdiukov
*/
    //Method for user story - "As customer I want to write transporter feedback."
    void addFeedback (FeedbackDTO dto, String email);
    //Method for user story - "As customer I want to change offer status."
    void changeStatus(Long offerId, Boolean offerStatus);

    List<OrderForListDto> findAllClosedOrders(String email);
/**
* Author - Ivan Synyshyn
*/
    //As transporter I want to choose orders by filter
    List<OrderForListDto> getOrdersByCityFrom(String name);
    List<OrderForListDto> getOrdersByCityTo(String name);
    List<OrderForListDto> getOrdersByWeight(BigDecimal weight);
    List<OrderForListDto> getOrdersByArriwalDate(Timestamp arrivalDate);
    List<OrderForListDto> getAllOpenOrder();
}
