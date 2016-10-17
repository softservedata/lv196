package com.softserve.edu.delivery.controller;

import com.softserve.edu.delivery.domain.Role;
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

    private static final String CUSTOMER_ROLE = Role.CUSTOMER.getName();
    private static final String DRIVER_ROLE = Role.DRIVER.getName();
    private static final String ADMIN_ROLE = Role.ADMIN.getName();
    private static final String MODERATOR_ROLE = Role.MODERATOR.getName();

    private static final String WELCOME_PAGE = "/welcome";
    private static final String CUSTOMER_PATH = "/#/orders";
    private static final String DRIVER_PATH = "/#/all-orders";
    private static final String ADMIN_PATH = "/#/users";
    private static final String MODERATOR_PATH = "/#/feedbacks";

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
    public ModelAndView signIn(@RequestParam(value = "auth", required = false) String auth) {
        logger.info("In method AuthController.login()");

        ModelAndView mv = new ModelAndView();
        if (auth != null && auth.equals("false")) {
            mv.addObject("msg", "You fill wrong credentials or email wasn't verified");
        }
        mv.addObject("userAuthDto", new UserAuthDTO());
        mv.addObject("userPrincipal", authenticationDetails.getAuthenticatedUserEmail());
        mv.setViewName("login");

        logger.info("Out of method AuthController.signIn()");
        return mv;
    }

    @RequestMapping(value = "/authRedirect", method = RequestMethod.GET)
    public ModelAndView loginProcess() {
        logger.info("In method AuthController.loginProcess()");
        ModelAndView mv = new ModelAndView();
        mv.setViewName(roleRedirect());
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
            ModelAndView mv = new ModelAndView("registration", "userRegistration", userRegDTO);
            mv.addObject("msg", "Please fill correct registration form");
            return mv;
        } else {
            String url = request.getRequestURL().toString();
            logger.info("Before service.register()");
            service.register(userRegDTO, url);
            logger.info("After service.register()");
            ModelAndView mv = new ModelAndView();
            mv.addObject("msg", "Please check your mail box and verify your email");
            mv.addObject("userAuthDto", new UserAuthDTO());
            mv.setViewName("login");
            logger.info("Out of method AuthController.reg()");
            return mv;
        }
    }

    @RequestMapping(value = "/driverRegister", method = RequestMethod.POST)
    public ModelAndView regDriver(@ModelAttribute("driverRegistration") @Valid DriverRegistrationDTO driverRegDTO,
                                  BindingResult result, HttpServletRequest request) {
        logger.info("In method AuthController.regDriver()");
        if (result.hasErrors()) {
            logger.error("Registration binding process has some error");
            return new ModelAndView("registration", "driverRegistration", driverRegDTO);
        } else {
            String url = request.getRequestURL().toString();
            service.register(driverRegDTO, url);
            ModelAndView mv = new ModelAndView();
            mv.addObject("userAuthDto", new UserAuthDTO());
            mv.addObject("msg", "Please check your mail box and verify your email");
            mv.setViewName("login");
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
        return mv;
    }

    @RequestMapping(value = "/logout")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
        logger.info("In method AuthController.logout()");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        logger.info("Out of method AuthController.logout()");
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg", "You've successfully logout");
        mv.addObject("userAuthDto", new UserAuthDTO());
        mv.setViewName("login");
        return mv;
    }

    @RequestMapping(value = "/accessDenied")
    public ModelAndView accessDenied() {
        logger.error("Access Denied. Role isn't allow to get this resource. Method AuthController.accessDenied()");
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg", "Access denied");
        mv.addObject("userPrincipal", authenticationDetails.getAuthenticatedUserEmail());
        mv.setViewName("welcome");
        return mv;
    }

    private String roleRedirect() {
        logger.info("In method AuthController.roleRedirect()");
        String authRole = this.authenticationDetails.getAuthenticatedUserRole();
        logger.info("Role of user is: " + authRole);
        if (authRole.equals(CUSTOMER_ROLE)) {
            logger.info("AuthController.roleRedirect() return: " + CUSTOMER_PATH);
            return "redirect:" + CUSTOMER_PATH;
        } else if (authRole.equals(DRIVER_ROLE)) {
            logger.info("AuthController.roleRedirect() return: " + DRIVER_PATH);
            return "redirect:" + DRIVER_PATH;
        } else if (authRole.equals(ADMIN_ROLE)) {
            logger.info("AuthController.roleRedirect() return: " + ADMIN_PATH);
            return "redirect:" + ADMIN_PATH;
        } else if (authRole.equals(MODERATOR_ROLE)) {
            logger.info("AuthController.roleRedirect() return: " + MODERATOR_PATH);
            return "redirect:" + MODERATOR_PATH;
        } else {
            logger.info("AuthController.roleRedirect() return: " + WELCOME_PAGE);
            return "redirect:" + WELCOME_PAGE;
        }
    }
}