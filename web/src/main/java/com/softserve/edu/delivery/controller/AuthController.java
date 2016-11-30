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
import java.util.Objects;

import static com.softserve.edu.delivery.config.SecurityConstraints.*;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserAuthenticationDetails authenticationDetails;

    private final Logger logger = LoggerFactory.getLogger(AuthController.class.getName());

    @GetMapping(value = "/welcome")
    public ModelAndView welcome() {
        logger.info("Return welcome page");
        return new ModelAndView("welcome")
        .addObject("orderIdDto", new OrderIdDto());
    }

    @GetMapping(value = "/login")
    public ModelAndView signIn(@RequestParam(value = "authError", required = false) String auth,
                               @RequestParam(value = "logout", required = false) String logout){
        ModelAndView mv = new ModelAndView();
        if (Objects.nonNull(auth)) {
            mv.addObject("wrong_login", "");
            logger.error("Wrong authentication");
        } else if (Objects.nonNull(logout)) {
            mv.addObject("success_logout", "");
            logger.info("Logout");
        }
        mv.addObject("userAuthDto", new UserAuthDTO());
        mv.setViewName("login");

        logger.info("Return login page");
        return mv;
    }

    @GetMapping(value = "/about")
    public ModelAndView about() {
        logger.info("Return about us page");
        return new ModelAndView("about");
    }

    @GetMapping(value = "/authRedirect")
    public ModelAndView authRedirect() {
        logger.info("Return redirecting page");
        return new ModelAndView(roleRedirect());
    }

    @GetMapping(value = "/registration")
    public ModelAndView registration() {
        logger.info("Return registration page");
        return new ModelAndView("registration")
        .addObject("userRegistration", new UserRegistrationDTO());
    }

    @PostMapping(value = "/register")
    public ModelAndView register(@ModelAttribute("userRegistration") @Valid UserRegistrationDTO userRegDTO,
                            BindingResult result, HttpServletRequest request) {
        if (result.hasErrors()) {
            logger.error("Registration binding process has errors, returning registration form back");
            return new ModelAndView("registration", "userRegistration", userRegDTO)
            .addObject("wrong_register", "");
        } else {
            String url = request.getRequestURL().toString();
            userService.register(userRegDTO, url);
            ModelAndView mv = new ModelAndView();
            mv.addObject("success_register", "");
            mv.addObject("userAuthDto", new UserAuthDTO());
            mv.setViewName("login");
            logger.info("Return login page");
            return mv;
        }
    }

    @GetMapping(value = "/register")
    public ModelAndView verifyRegistration(@RequestParam("token") String token) {
        if (userService.tokenExists(token)) {
            userService.verifyRegistration(token);

            logger.info("Email was verified. Return login page");
            return new ModelAndView("login")
            .addObject("userAuthDto", new UserAuthDTO())
            .addObject("email_verified", "");
        } else {
            return new ModelAndView(PAGE_NOT_FOUND_URL);
        }
    }

    @PreAuthorize(AUTHENTICATED)
    @RequestMapping(path = "/user/email", produces = "text/plain")
    public @ResponseBody String userEmail(Principal principal) {
        return principal.getName();
    }

    @PreAuthorize(AUTHENTICATED)
    @GetMapping(value = "role")
    public @ResponseBody StringResponse getRole() {
        String role = authenticationDetails.getAuthenticatedUserRole();
        logger.info("Return user role: " + role);
        return new StringResponse(role);
    }

    private String roleRedirect() {
        String role = this.authenticationDetails.getAuthenticatedUserRole();
        String redirect = "redirect:";
        switch (role) {
            case "Customer":
                return redirect += CUSTOMER_PAGE;
            case "Driver":
                return redirect += DRIVER_PAGE;
            case "Moderator":
                return redirect += MODERATOR_PAGE;
            case "Admin" :
            case "Manager":
                return redirect += ADMIN_PAGE;
            default:
                return redirect += WELCOME_PAGE;
        }
    }
}