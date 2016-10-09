package com.softserve.edu.delivery.controller;

import com.softserve.edu.delivery.dto.OrderIdDto;
import com.softserve.edu.delivery.dto.OrderRouteDto;
import com.softserve.edu.delivery.service.OrderRouteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class HomeController {

    private OrderRouteService service;

    Logger logger = LoggerFactory.getLogger(HomeController.class.getName());

    @Autowired
    public HomeController setService(OrderRouteService service) {
        this.service = service;
        return this;
    }

    @RequestMapping(value = {"/welcome"})
    public ModelAndView welcome() {
        logger.info("In method HomeController.welcome()");
        ModelAndView mv = new ModelAndView();
        mv.addObject("orderIdDto", new OrderIdDto());
        mv.setViewName("welcome");
        logger.info("Out of method HomeController.welcome()");
        return mv;
    }

    @RequestMapping(value = "/tracking", method = RequestMethod.POST)
    public ModelAndView processOrder(@Valid OrderIdDto orderIdDto, BindingResult result) {
        logger.info("In method HomeController.processOrder()");
        if (result.hasErrors()) {
            logger.error("Binding error in method HomeController.processOrder();... /tracking");
            return new ModelAndView("welcome");
        }
        OrderRouteDto dto = service.getOrderRouteById(orderIdDto);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("tracking");
        mv.addObject("order", dto);
        logger.info("Out of method HomeController.processOrder()");
        return mv;
    }
}
