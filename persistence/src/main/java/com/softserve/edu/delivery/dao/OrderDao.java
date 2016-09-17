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

//    As customer I want to choose transporter.
    List<Order> changeStatus(String id, Offer offer);

//    As customer I want to write transporter feedback.
    String feedback(String order_id, Feedback feedback);

    //	As transporter I want to choose orders by filter.
    List<Order> getOrderByCityFrom(String name);
    List<Order> getOrderByCityTo(String name);
    List<Order> getOrderByWeight(BigDecimal weight);
    List<Order> getOrderByArrivalDate(Timestamp arrivalDate);




}
