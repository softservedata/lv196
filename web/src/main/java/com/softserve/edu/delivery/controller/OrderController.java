package com.softserve.edu.delivery.controller;

import com.softserve.edu.delivery.dto.OrderForAddDto;
import com.softserve.edu.delivery.dto.OrderForListDto;
import com.softserve.edu.delivery.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping(path = "in_progress", method = RequestMethod.GET)
    List<OrderForListDto> inProgress() {
        String email = "martin@gmail.com"; // will be retrieved via Spring Security later

        return orderService.findAllInProgressOrders(email);
    }

    @RequestMapping(path = "open", method = RequestMethod.GET)
    List<OrderForListDto> open() {
        String email = "martin@gmail.com"; // will be retrieved via Spring Security later

        return orderService.findAllOpenOrders(email);
    }

    @RequestMapping(method = RequestMethod.POST)
    void addOrder(@RequestBody OrderForAddDto dto) {
        String email = "martin@gmail.com"; // will be retrieved via Spring Security later
        orderService.addOrder(dto, email);
    }
}
