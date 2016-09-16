package com.softserve.edu.delivery.dao;

import com.softserve.edu.delivery.domain.Feedback;
import com.softserve.edu.delivery.domain.Offer;
import com.softserve.edu.delivery.domain.Order;
import com.softserve.edu.delivery.domain.OrderStatus;

import java.util.List;
/**
 * @author Marina Chepurna
 * @author Taras Kurdiukov
 *
 * */

public interface OrderDao extends BaseDao<Order, Long> {
    List<Order> findAllOrdersByStatus(String email, int page, int size, OrderStatus orderStatus);


//    As customer I want to choose transporter.
    List<Order> changeStatus(String id, Offer offer);

//    As customer I want to write transporter feedback.
    String feedback(String order_id, Feedback feedback);
}
