package com.softserve.edu.delivery.controller;

import com.softserve.edu.delivery.exception.ApplicationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
