package com.softserve.edu.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @RequestMapping("/")
    public String welcome(Model model) {
        model.addAttribute("firstPageMessage", "This is redirect");
        return "redirect:/index";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView homeHandler(ModelAndView model) {
        // model.addAttribute("firstPageMessage", "This is home");
        model.addObject("firstPageMessage",
                model.getModel().get("firstPageMessage") + " This is HOME");
        model.setViewName("home");
        return model;
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    // public String indexHandler(Model model) {
    // model.addAttribute("firstPageMessage", "index");
    public String indexHandler(Model model) {
        if (model.asMap().get("firstPageMessage") != null) {
            model.addAttribute("firstPageMessage",
                    model.asMap().get("firstPageMessage").toString() + " This is INDEX");
        } else {
            model.addAttribute("firstPageMessage", "This is INDEX");
        }
        return "index";

        // model.addObject("firstPageMessage",
        // model.getModel().get("firstPageMessage").toString()+" This is
        // INDEX");
        // model.setViewName("index");
        // return model;

    }

}
