package com.softserve.edu.delivery.controller;

import com.softserve.edu.delivery.dto.FeedbackDTO;
import com.softserve.edu.delivery.dto.OfferDto;
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
        dto.setOrderId(1l);
        orderService.addFeedback(dto, email);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    void removeOrder(@PathVariable Long id) {
        orderService.removeOrder(id);
    }

    @RequestMapping(path = "change", method = RequestMethod.PUT)
    void changeOfferStatus(@RequestBody OfferDto offerDto) {
        orderService.changeStatus(offerDto.getOfferId(),offerDto.isApproved());
    }

    /*--------------------IvanSynyshyn----------------------------*/
    @RequestMapping(path = "filtered-by-city-from", method = RequestMethod.GET)
    List<OrderForListDto> filteredByCityFrom(@RequestParam String city) {
        return orderService.getOrdersByCityFrom(city);
    }

    @RequestMapping(path = "filtered-by-city-to", method = RequestMethod.GET)
    List<OrderForListDto> filteredByCityTo(@RequestParam String city) {
        return orderService.getOrdersByCityTo(city);
    }

    @RequestMapping(path = "filtered-by-weight", method = RequestMethod.GET)
    List<OrderForListDto> filteredByWeight(@RequestParam String weight) {
        BigDecimal converted = BigDecimal.valueOf(Double.parseDouble(weight));
        return orderService.getOrdersByWeight(converted);
    }

    @RequestMapping(path = "filtered-by-arrival-date", method = RequestMethod.GET)
    List<OrderForListDto> filteredByArriwalDate(@RequestParam String date) {
        SimpleDateFormat formater = new SimpleDateFormat("yyyy/MM/dd");
        Date parsed = null;
        try {
            parsed = formater.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Timestamp timestamp = new Timestamp(parsed.getTime());
        return orderService.getOrdersByArriwalDate(timestamp);
    }

    @RequestMapping(path = "offer", method = RequestMethod.POST)
    List<OfferDto> addOffer(@RequestBody OrderForListDto order) {
        return orderService.addOffer(order.getId());
    }
}
