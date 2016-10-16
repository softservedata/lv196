package com.softserve.edu.delivery.controller;

import com.softserve.edu.delivery.dto.DriverRegistrationDTO;
import com.softserve.edu.delivery.dto.OrderIdDto;
import com.softserve.edu.delivery.dto.UserAuthDTO;
import com.softserve.edu.delivery.dto.UserRegistrationDTO;
import com.softserve.edu.delivery.service.UserAuthenticationDetails;
import com.softserve.edu.delivery.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
    public ModelAndView loginProcess(@ModelAttribute("userAuthDto") @Valid UserAuthDTO user, BindingResult result) {
        logger.info("In method AuthController.loginProcess()");
        if (result.hasErrors()) {
            logger.error("Has some binding error, method AuthController.loginProcess()");
            return new ModelAndView("login", "userAuthDto", user);
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

    @RequestMapping(value = "/driverRegistration")
    public ModelAndView driverRegistration() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("driverRegistration", new DriverRegistrationDTO());
        mv.setViewName("driverRegistration");
        return mv;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView reg(@ModelAttribute("userRegistration") @Valid UserRegistrationDTO userRegDTO,
                            BindingResult result, HttpServletRequest request) {
        logger.info("In method AuthController.reg()");
        if (result.hasErrors()) {
            logger.error("Registration binding process has some error");
            return new ModelAndView("registration", "userRegistration", userRegDTO);
        }else {
            String url = request.getRequestURL().toString();
            service.register(userRegDTO, url);
            return new ModelAndView("redirect:/login");
        }
    }

    @RequestMapping(value = "/driverRegister", method = RequestMethod.POST)
    public ModelAndView regDriver(@ModelAttribute("driverRegistration") @Valid DriverRegistrationDTO driverRegDTO,
                            BindingResult result, HttpServletRequest request) {
        logger.info("In method AuthController.regDriver()");
        if (result.hasErrors()) {
            logger.error("Registration binding process has some error");
            return new ModelAndView("registration", "userRegistration", driverRegDTO);
        }else {
            String url = request.getRequestURL().toString();
            service.register(driverRegDTO, url);
            return new ModelAndView("redirect:/login");
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView reg(@RequestParam("token") String token) {
        service.verifyRegistration(token);
        return new ModelAndView("redirect:/login");
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