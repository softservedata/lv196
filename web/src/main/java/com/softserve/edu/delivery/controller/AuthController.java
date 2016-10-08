package com.softserve.edu.delivery.controller;

import com.softserve.edu.delivery.dto.UserAuthDTO;
import com.softserve.edu.delivery.dto.UserProfileDto;
import com.softserve.edu.delivery.dto.UserRegistrationDTO;
import com.softserve.edu.delivery.service.UserService;
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

    private final UserService service;

    @Autowired
    public AuthController(UserService service) {
        this.service = service;
    }

    @RequestMapping(value = "/login")
    public ModelAndView signIn() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("userAuthDto", new UserAuthDTO());
        mv.addObject("userPrincipal", getPrincipal());
        mv.setViewName("login");
        return mv;
    }

    @RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
    public ModelAndView profile(@Valid UserAuthDTO user, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("login");
        }
        UserProfileDto userProfileDto = service.verificationLogin(user);
        ModelAndView mv = new ModelAndView();
        mv.addObject("userProfile", userProfileDto);
        mv.addObject("userPrincipal", getPrincipal());
        mv.setViewName("profile");
        return mv;
    }

    @RequestMapping(value = "/registration")
    public ModelAndView registration() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("userRegistration", new UserRegistrationDTO());
        mv.setViewName("registration");
        return mv;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView reg(@ModelAttribute("userRegistration") @Valid UserRegistrationDTO userRegDTO, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("registration");
        }else {
            service.register(userRegDTO);
            return new ModelAndView("redirect:/login");
        }
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }

    @RequestMapping(value = "/admin")
    public ModelAndView adminPage() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("userPrincipal", getPrincipal());
        mv.setViewName("admin");
        return mv;
    }

    @RequestMapping(value = "/moderator")
    public ModelAndView moderatorPage() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("userPrincipal", getPrincipal());
        mv.setViewName("moderator");
        return mv;
    }

    @RequestMapping(value = "/accessDenied")
    public ModelAndView accessDenied() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("userPrincipal", getPrincipal());
        mv.setViewName("accessDenied");
        return mv;
    }

    private String getPrincipal() {
        String userName;
        Object principal;
        try {
            principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (NullPointerException ex) {
            return "";
        }
        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        }else {
            userName = principal.toString();
        }
        return userName;
    }
}