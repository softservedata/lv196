package com.softserve.edu.delivery.controller;

import com.softserve.edu.delivery.dto.UserAuthDTO;
import com.softserve.edu.delivery.dto.UserProfileDto;
import com.softserve.edu.delivery.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class AuthController {

    private final UserService service;

    @Autowired
    public AuthController(UserService service) {
        this.service = service;
    }

    @RequestMapping(value = "/login")
    public ModelAndView signIn() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("userAuthDto", new UserAuthDTO());
        mv.setViewName("login");
        return mv;
    }

    @RequestMapping(value = "/profile")
    public ModelAndView profile(@Valid UserAuthDTO user, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("login");
        }
        UserProfileDto userProfileDto = service.verificationLogin(user);
        ModelAndView mv = new ModelAndView();
        mv.addObject("userProfile", userProfileDto);
        return mv;
    }
}