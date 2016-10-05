package com.softserve.edu.delivery.controller;

import com.softserve.edu.delivery.exception.ApplicationException;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandleController {

    @ExceptionHandler(ApplicationException.class)
    public ModelAndView errorHandle(ApplicationException ex) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("404");
        mv.addObject("msg", ex.getMessage());
        return mv;
    }
}