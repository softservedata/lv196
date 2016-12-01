package com.softserve.edu.delivery.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class ErrorHandleController {

    private static Logger logger = LoggerFactory.getLogger(ErrorHandleController.class);

    @RequestMapping(value = "/notFound", method = {GET, POST})
    public ModelAndView pageNotFound() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("error");
        mv.addObject("error_code", "404");
        mv.addObject("error_msg", "page_not_found");

        logger.error("Return error page: 404, page not found");
        return mv;
    }

    @RequestMapping(value = "/serverError", method = {GET, POST})
    public ModelAndView serverError() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("error");
        mv.addObject("error_code", "500");
        mv.addObject("error_msg", "internal_server_error");

        logger.error("Return error page: 500, internal server error");
        return mv;
    }

    @RequestMapping(value = "/accessDenied", method = {GET, POST})
    public ModelAndView accessDenied() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("error");
        mv.addObject("error_code", "403");
        mv.addObject("error_msg", "access_denied");

        logger.error("Return error page: 403, access denied");
        return mv;
    }
}
