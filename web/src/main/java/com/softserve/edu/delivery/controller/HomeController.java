package com.softserve.edu.delivery.controller;

import com.softserve.edu.delivery.dto.OrderIdDto;
import com.softserve.edu.delivery.dto.OrderRouteDto;
import com.softserve.edu.delivery.service.OrderRouteService;
import com.softserve.edu.delivery.service.impl.OrderRouteServiceImplFake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class HomeController {

    private OrderRouteService service;

    @Autowired
    public HomeController setService(@Qualifier("fake") OrderRouteService service) {
        this.service = service;
        return this;
    }

    @RequestMapping(value = {"/", "/home", "/welcome", "/index", "/main"})
    public ModelAndView welcome() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("orderIdDto", new OrderIdDto());
        mv.setViewName("welcome");
        return mv;
    }

    @RequestMapping(value = "/tracking", method = RequestMethod.POST)
    public ModelAndView processOrder(@Valid OrderIdDto orderIdDto, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("welcome");
        }
        OrderRouteDto dto = service.getOrderRouteById(orderIdDto);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("tracking");
        mv.addObject("order", dto);
        return mv;
    }
}
