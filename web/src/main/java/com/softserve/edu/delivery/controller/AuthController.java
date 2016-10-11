package com.softserve.edu.delivery.controller;

import com.softserve.edu.delivery.dto.OrderIdDto;
import com.softserve.edu.delivery.dto.UserAuthDTO;
import com.softserve.edu.delivery.dto.UserProfileDto;
import com.softserve.edu.delivery.dto.UserRegistrationDTO;
import com.softserve.edu.delivery.service.UserAuthenticationDetails;
import com.softserve.edu.delivery.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Null;

@Controller
public class AuthController {

    @Autowired
    private UserService service;

    @Autowired
    private UserAuthenticationDetails authenticationDetails;

    private final Logger logger = LoggerFactory.getLogger(AuthController.class.getName());

    @RequestMapping(value = {"/welcome"})
    public ModelAndView welcome() {
        logger.info("In method AuthController.welcome()");
        ModelAndView mv = new ModelAndView();
        mv.addObject("orderIdDto", new OrderIdDto());
        mv.addObject("userPrincipal", authenticationDetails.getAuthenticatedUserEmail());
        mv.setViewName("welcome");
        logger.info("Out of method AuthController.welcome()");
        return mv;
    }


    @RequestMapping(value = "/login")
    public ModelAndView signIn() {
        logger.info("In method AuthController.login()");

        ModelAndView mv = new ModelAndView();
        mv.addObject("userAuthDto", new UserAuthDTO());
        mv.addObject("userPrincipal", authenticationDetails.getAuthenticatedUserEmail());
        mv.setViewName("login");

        logger.info("Out of method AuthController.signIn()");
        return mv;
    }

    @RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
    public ModelAndView loginProcess(@Valid UserAuthDTO user, BindingResult result) {
        logger.info("In method AuthController.loginProcess()");
        if (result.hasErrors()) {
            logger.error("Has some binding error, method AuthController.loginProcess()");
            return new ModelAndView("login");
        }
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/welcome");
        logger.info("Out of method AuthController.loginProcess()");
        return mv;
    }

    @RequestMapping(value = "/registration")
    public ModelAndView registration() {
        logger.info("In method AuthController.registration()");
        ModelAndView mv = new ModelAndView();
        mv.addObject("userRegistration", new UserRegistrationDTO());
        mv.setViewName("registration");
        logger.info("Out of method AuthController.registration");
        return mv;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView reg(@ModelAttribute("userRegistration") @Valid UserRegistrationDTO userRegDTO, BindingResult result) {
        logger.info("In method AuthController.registration()");
        if (result.hasErrors()) {
            logger.error("Registration binding process has some error");
            return new ModelAndView("registration");
        }else {
            service.register(userRegDTO);
            return new ModelAndView("redirect:/login");
        }
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        logger.info("In method AuthController.logout()");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        logger.info("Out of method AuthController.logout()");
        return "redirect:/login";
    }

    @RequestMapping(value = "/accessDenied")
    public ModelAndView accessDenied() {
        logger.error("Access Denied. Role isn't allow to get this resource. Method AuthController.accessDenied()");
        ModelAndView mv = new ModelAndView();
        mv.addObject("userPrincipal", authenticationDetails.getAuthenticatedUserEmail());
        mv.setViewName("accessDenied");
        return mv;
    }
}