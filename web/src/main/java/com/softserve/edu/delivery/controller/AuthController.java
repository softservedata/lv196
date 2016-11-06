package com.softserve.edu.delivery.controller;

import com.softserve.edu.delivery.dto.*;
import com.softserve.edu.delivery.service.UserAuthenticationDetails;
import com.softserve.edu.delivery.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

import static com.softserve.edu.delivery.config.SecurityConstraints.*;

@Controller
public class AuthController {

    @Autowired
    private UserService service;

    @Autowired
    private UserAuthenticationDetails authenticationDetails;

    private final Logger logger = LoggerFactory.getLogger(AuthController.class.getName());

    @RequestMapping(value = {"/welcome"})
    public ModelAndView welcome() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("orderIdDto", new OrderIdDto());
        mv.setViewName("welcome");
        logger.info("Return welcome page");
        return mv;
    }

    @RequestMapping(value = "/login")
    public ModelAndView signIn(@RequestParam(value = "auth", required = false) String auth,
                               @RequestParam(value = "logout", required = false) String logout){
        ModelAndView mv = new ModelAndView();
        if (auth != null && auth.equals("false")) {
            mv.addObject("msg", "You fill wrong credentials or email wasn't verified");
            logger.warn("User entered wrong credentials");
        }else if (logout != null) {
            mv.addObject("msg", "You've successfully logout");
            logger.info("Logout");
        }
        mv.addObject("userAuthDto", new UserAuthDTO());
        mv.setViewName("login");

        logger.info("Return login page");
        return mv;
    }

    @RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
    public ModelAndView loginProcess(@ModelAttribute("userAuthDto") @Valid UserAuthDTO userAuthDTO,
                                     BindingResult result) {
        if (result.hasErrors()) {
            logger.error("Binding error, user: " + userAuthDTO.getEmail());
            return new ModelAndView("login", "userAuthDto", userAuthDTO);
        }
        logger.info("Return welcome page");
        return new ModelAndView("redirect:/welcome");
    }

    @RequestMapping(value = "/authRedirect", method = RequestMethod.GET)
    public ModelAndView authRedirect() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName(roleRedirect());
        logger.info("Return redirecting page");
        return mv;
    }

    @PreAuthorize(AUTHENTICATED)
    @RequestMapping(value = "role")
    public @ResponseBody StringResponse getRole() {
        String role = authenticationDetails.getAuthenticatedUserRole();
        logger.info("Return user role: " + role);
        return new StringResponse(role);
    }

    @RequestMapping(value = "/registration")
    public ModelAndView registration() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("userRegistration", new UserRegistrationDTO());
        mv.setViewName("registration");
        logger.info("Return registration page");
        return mv;
    }

    @RequestMapping(value = "/driverRegistration")
    public ModelAndView driverRegistration() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("driverRegistration", new DriverRegistrationDTO());
        mv.setViewName("driverRegistration");
        logger.info("Return registration page");
        return mv;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView reg(@ModelAttribute("userRegistration") @Valid UserRegistrationDTO userRegDTO,
                            BindingResult result, HttpServletRequest request) {
        if (result.hasErrors()) {
            logger.error("Registration binding process has errors, returning registration form back");
            ModelAndView mv = new ModelAndView("registration", "userRegistration", userRegDTO);
            mv.addObject("msg", "Please fill correct registration form");
            return mv;
        } else {
            String url = request.getRequestURL().toString();
            service.register(userRegDTO, url);
            ModelAndView mv = new ModelAndView();
            mv.addObject("msg", "Please check your mail box and verify your email");
            mv.addObject("userAuthDto", new UserAuthDTO());
            mv.setViewName("login");
            logger.info("Return login page");
            return mv;
        }
    }

    @RequestMapping(value = "/driverRegister", method = RequestMethod.POST)
    public ModelAndView regDriver(@ModelAttribute("driverRegistration") @Valid DriverRegistrationDTO driverRegDTO,
                                  BindingResult result, HttpServletRequest request) {
        if (result.hasErrors()) {
            logger.error("Registration binding process has errors, returning registration form back");
            return new ModelAndView("registration", "driverRegistration", driverRegDTO);
        } else {
            String url = request.getRequestURL().toString();
            service.register(driverRegDTO, url);
            ModelAndView mv = new ModelAndView();
            mv.addObject("userAuthDto", new UserAuthDTO());
            mv.addObject("msg", "Please check your mail box and verify your email");
            mv.setViewName("login");
            logger.info("Return login page");
            return mv;
        }
    }

    @RequestMapping(value = {"/register", "/driverRegister"}, method = RequestMethod.GET)
    public ModelAndView reg(@RequestParam("token") String token) {
        service.verifyRegistration(token);
        ModelAndView mv = new ModelAndView();
        mv.addObject("userAuthDto", new UserAuthDTO());
        mv.addObject("msg", "You've successful verified your email. Please sign in.");
        mv.setViewName("login");
        logger.info("Email was verified. Return login page");
        return mv;
    }

    @RequestMapping(value = "/accessDenied")
    public ModelAndView accessDenied() {
        logger.warn("Access Denied. Role isn't allow to get this resource.");
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg", "Access denied");
        mv.addObject("orderIdDto", new OrderIdDto());
        logger.info("Return welcome page");
        mv.setViewName("welcome");
        return mv;
    }

    @RequestMapping(path = "/user/email", produces = "text/plain")
    @ResponseBody
    public String userEmail(Principal principal) {
        return principal.getName();
    }

    private String roleRedirect() {
        String authRole = this.authenticationDetails.getAuthenticatedUserRole();
        logger.info("Role of user is: " + authRole);
        if (authRole.equals(CUSTOMER_ROLE)) {
            logger.info("Return customer page: " + CUSTOMER_PAGE);
            return "redirect:" + CUSTOMER_PAGE;
        } else if (authRole.equals(DRIVER_ROLE)) {
            logger.info("Return driver page: " + DRIVER_PAGE);
            return "redirect:" + DRIVER_PAGE;
        } else if (authRole.equals(ADMIN_ROLE)) {
            logger.info("Return admin page: " + ADMIN_PAGE);
            return "redirect:" + ADMIN_PAGE;
        } else if (authRole.equals(MODERATOR_ROLE)) {
            logger.info("Return moderator page: " + MODERATOR_PAGE);
            return "redirect:" + MODERATOR_PAGE;
        } else {
            logger.info("Return welcome page: " + WELCOME_PAGE);
            return "redirect:" + WELCOME_PAGE;
        }
    }
}