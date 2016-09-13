package com.softserve.edu.delivery.dao.impl;

import com.softserve.edu.delivery.dao.OrderDao;
import com.softserve.edu.delivery.domain.Order;

public class OrderDaoImpl extends BaseDaoImpl<Order, Long> implements OrderDao {

    public OrderDaoImpl() {
        super(Order.class);
    }

}
