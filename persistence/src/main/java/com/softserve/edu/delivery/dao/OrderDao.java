package com.softserve.edu.delivery.dao;

import com.softserve.edu.delivery.domain.Feedback;
import com.softserve.edu.delivery.domain.Offer;
import com.softserve.edu.delivery.domain.Order;
import com.softserve.edu.delivery.domain.OrderStatus;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public interface OrderDao extends BaseDao<Order, Long> {

    List<Order> findAllOrdersByStatus(String email, int page, int size, OrderStatus orderStatus);

    //  As customer I want to choose transporter.
    void changeStatus(String order_id, Boolean offerStatus);

    //	As transporter I want to choose orders by filter.
    List<Order> getOrderByCityFrom(Long id);
    List<Order> getOrderByCityTo(Long id);
    List<Order> getOrderByWeight(BigDecimal weight);
    List<Order> getOrderByArrivalDate(Timestamp arrivalDate);




}
