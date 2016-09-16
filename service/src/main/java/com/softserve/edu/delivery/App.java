package com.softserve.edu.delivery;

import com.softserve.edu.delivery.dao.impl.OrderDaoImpl;
import com.softserve.edu.delivery.service.OrderService;
import com.softserve.edu.delivery.service.impl.OrderServiceImpl;
import com.softserve.edu.delivery.utils.Jpa;

// Just on purpose for quick testing ...
public class App {
    public static void main(String[] args) {
        try {
            OrderService orderService = new OrderServiceImpl(new OrderDaoImpl());
            orderService.findAllActiveOrders("blabla@exampl.com", 1, 2);
        } finally {
            Jpa.close();
        }
    }
}
