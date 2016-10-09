package com.softserve.edu.delivery.controller;

import com.softserve.edu.delivery.dto.FeedbackDTO;
import com.softserve.edu.delivery.dto.OfferDtoForList;
import com.softserve.edu.delivery.dto.OrderForAddDto;
import com.softserve.edu.delivery.dto.OrderForListDto;
import com.softserve.edu.delivery.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(path = "in-progress", method = RequestMethod.GET)
    List<OrderForListDto> inProgress() {
        String email = "martin@gmail.com"; // will be retrieved via Spring Security later

        return orderService.findInProgressOrders(email);
    }

    @RequestMapping(path = "open", method = RequestMethod.GET)
    List<OrderForListDto> open() {
        String email = "martin@gmail.com"; // will be retrieved via Spring Security later
        return orderService.findOpenOrders(email);
    }

    @RequestMapping(path = "closed", method = RequestMethod.GET)
    List<OrderForListDto> closed() {
        String email = "martin@gmail.com"; // will be retrieved via Spring Security later
        return orderService.findAllClosedOrders(email);
    }


    @RequestMapping(method = RequestMethod.POST)
    void addOrder(@RequestBody OrderForAddDto dto) {
        String email = "martin@gmail.com"; // will be retrieved via Spring Security later
        orderService.addOrder(dto, email);
    }

    @RequestMapping(path = "addfeedback", method = RequestMethod.POST)
    void addFeedback(@RequestBody FeedbackDTO dto) {
        String email = "martin@gmail.com"; // will be retrieved via Spring Security later
        orderService.addFeedback(dto, email);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    void removeOrder(@PathVariable Long id) {
        orderService.removeOrder(id);
    }

    @RequestMapping(path = "change", method = RequestMethod.PUT)
    void changeOfferStatus(@RequestBody OfferDtoForList offerDto) {
        orderService.changeStatus(offerDto.getOfferId(),offerDto.isApproved());
    }

    @RequestMapping(path = "offers/{id}", method = RequestMethod.GET)
    List<OfferDtoForList> getOffersByOrderId(@PathVariable Long id) {
        return orderService.getOffersByOrderId(id);
    }
}
