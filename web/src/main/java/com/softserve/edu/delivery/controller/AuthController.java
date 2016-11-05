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
        logger.info("In method AuthController.welcome()");
        ModelAndView mv = new ModelAndView();
        mv.addObject("orderIdDto", new OrderIdDto());
        mv.addObject("userPrincipal", authenticationDetails.getAuthenticatedUserEmail());
        mv.setViewName("welcome");
        logger.info("Out of method AuthController.welcome()");
        return mv;
    }

    @RequestMapping(value = "/login")
    public ModelAndView signIn(@RequestParam(value = "auth", required = false) String auth,
                               @RequestParam(value = "logout", required = false) String logout){
        logger.info("In method AuthController.signIn()");

        ModelAndView mv = new ModelAndView();
        if (auth != null && auth.equals("false")) {
            mv.addObject("msg", "You fill wrong credentials or email wasn't verified");
        }else if (logout != null) {
            mv.addObject("msg", "You've successfully logout");
        }
        mv.addObject("userAuthDto", new UserAuthDTO());
        mv.addObject("userPrincipal", authenticationDetails.getAuthenticatedUserEmail());
        mv.setViewName("login");

        logger.info("Out of method AuthController.signIn()");
        return mv;
    }

    @RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
    public ModelAndView loginProcess(@ModelAttribute("userAuthDto") @Valid UserAuthDTO userAuthDTO,
                                     BindingResult result) {
        logger.info("In method AuthController.loginProcess()");
        if (result.hasErrors()) {
            logger.error("Binding error in AuthController().loginProcess(), user: " + userAuthDTO.getEmail());
            return new ModelAndView("login", "userAuthDto", userAuthDTO);
        }
        logger.info("Out of method AuthController.loginProcess()");
        return new ModelAndView("redirect:/welcome");
    }

    @RequestMapping(value = "/authRedirect", method = RequestMethod.GET)
    public ModelAndView authRedirect() {
        logger.info("In method AuthController.authRedirect()");
        ModelAndView mv = new ModelAndView();
        mv.setViewName(roleRedirect());
        logger.info("Out of method AuthController.authRedirect()");
        return mv;
    }

    @PreAuthorize(AUTHENTICATED)
    @RequestMapping(value = "role")
    public @ResponseBody StringResponse getRole() {
        String role = authenticationDetails.getAuthenticatedUserRole();
        logger.info("AuthController.getRole(), return: " + role);
        return new StringResponse(role);
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

    @RequestMapping(value = "/accessDenied")
    public ModelAndView accessDenied() {
        logger.error("Access Denied. Role isn't allow to get this resource. Method AuthController.accessDenied()");
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg", "Access denied");
        mv.addObject("orderIdDto", new OrderIdDto());
        mv.addObject("userPrincipal", authenticationDetails.getAuthenticatedUserEmail());
        mv.setViewName("welcome");
        return mv;
    }

    @RequestMapping(path = "/user/email", produces = "text/plain")
    @ResponseBody
    public String userEmail(Principal principal) {
        return principal.getName();
    }

    private String roleRedirect() {
        logger.info("In method AuthController.roleRedirect()");
        String authRole = this.authenticationDetails.getAuthenticatedUserRole();
        logger.info("Role of user is: " + authRole);
        if (authRole.equals(CUSTOMER_ROLE)) {
            logger.info("AuthController.roleRedirect() return: " + CUSTOMER_PAGE);
            return "redirect:" + CUSTOMER_PAGE;
        } else if (authRole.equals(DRIVER_ROLE)) {
            logger.info("AuthController.roleRedirect() return: " + DRIVER_PAGE);
            return "redirect:" + DRIVER_PAGE;
        } else if (authRole.equals(ADMIN_ROLE)) {
            logger.info("AuthController.roleRedirect() return: " + ADMIN_PAGE);
            return "redirect:" + ADMIN_PAGE;
        } else if (authRole.equals(MODERATOR_ROLE)) {
            logger.info("AuthController.roleRedirect() return: " + MODERATOR_PAGE);
            return "redirect:" + MODERATOR_PAGE;
        } else {
            logger.info("AuthController.roleRedirect() return: " + WELCOME_PAGE);
            return "redirect:" + WELCOME_PAGE;
        }
    }
}