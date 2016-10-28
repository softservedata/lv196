package com.softserve.edu.delivery.controller;

import com.softserve.edu.delivery.exception.ApplicationException;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandleController {

    Logger logger = LoggerFactory.getLogger(ExceptionHandleController.class.getName());

    @ExceptionHandler(ApplicationException.class)
    public ModelAndView errorHandle(ApplicationException ex) {
        logger.error("In method ExceptionHandleController.errorHandle().");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/welcome");
        mv.addObject("msg", ex.getMessage());
        return mv;
    }
}