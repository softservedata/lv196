package com.softserve.edu.delivery.dao;

import com.softserve.edu.delivery.domain.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface OrderDao extends BaseDao<Order, Long> {

    /**
     * Finds driver full name by given order id
     *
     * @param id - order id
     * @return the Optional of driver full name or
     * empty Optional if couldn't find driver for given order id.
     */
    Optional<String> findDriverNameByOrderId(Long id);

    /**
     * Finds orders by given parameters.
     *
     * @param email - email of assigned customer
     * @param page - number of page
     * @param size - orders amount on the page
     * @param orderStatus - status of order
     * @return list of orders for the relevant parameters
     */
    List<Order> findAllOrdersByStatus(String email, int page, int size, OrderStatus orderStatus);

    //  As customer I want to choose transporter.
    void changeStatus(String order_id, Boolean offerStatus);

    //	As transporter I want to choose orders by filter.
    List<Order> getOrderByCityFrom(Long id);
    List<Order> getOrderByCityTo(Long id);
    List<Order> getOrderByWeight(BigDecimal weight);
    List<Order> getOrderByArrivalDate(Timestamp arrivalDate);




}
