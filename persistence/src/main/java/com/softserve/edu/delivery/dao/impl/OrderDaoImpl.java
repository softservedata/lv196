package com.softserve.edu.delivery.dao.impl;

import com.softserve.edu.delivery.dao.OrderDao;
import com.softserve.edu.delivery.domain.Order;
import com.softserve.edu.delivery.domain.OrderStatus;

import java.util.List;

public class OrderDaoImpl extends BaseDaoImpl<Order, Long> implements OrderDao {

    public OrderDaoImpl() {
        super(Order.class);
    }

    public List<Order> findAllOrdersByStatus(int page, int size, OrderStatus orderStatus) {
        return getEntityManager()
                .createQuery("select o from Order o where o.orderStatus = :orderStatus " +
                        "order by o.registrationDate", Order.class)
                .setParameter("orderStatus", orderStatus)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
    }

}
