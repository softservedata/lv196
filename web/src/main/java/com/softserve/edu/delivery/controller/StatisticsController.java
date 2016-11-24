package com.softserve.edu.delivery.controller;

import static com.softserve.edu.delivery.config.SecurityConstraints.ADMIN;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.softserve.edu.delivery.dto.DataDto;
import com.softserve.edu.delivery.dto.UserProfileDto;
import com.softserve.edu.delivery.service.OrderService;
import com.softserve.edu.delivery.service.UserService;

@RestController
@RequestMapping(path = "data")
public class StatisticsController {
	
	   @Autowired
	   UserService userService;
	   
	   @Autowired
	   OrderService orderService;
	   
	   Logger logger = LoggerFactory.getLogger(UserController.class.getName());
	   
	   @PreAuthorize(ADMIN)
	   @RequestMapping(path = "day-orders", method = RequestMethod.GET)
	   List<DataDto> countAllPerHour(@RequestParam("date") String date) {
	       return orderService.countClosedOrdersPerHour(date);
	   }
	   
	   @PreAuthorize(ADMIN)
	   @RequestMapping(path = "month-orders", method = RequestMethod.GET)
	   List<DataDto> countAllPerDay() {
	       return orderService.countClosedOrdersPerDay();
	   }
	   
	   @PreAuthorize(ADMIN)
	   @RequestMapping(path = "year-orders", method = RequestMethod.GET)
	   List<DataDto> countAllPerMonth() {
	       return orderService.countClosedOrdersMonth();
	   }
	   
	   @PreAuthorize(ADMIN)
	   @RequestMapping(path = "top-5-drivers", method = RequestMethod.GET)
	   List<UserProfileDto> getTopFiveDrivers() {
		   logger.info("Method UserController.getTopFiveDrivers()");
	       return userService.findTopFiveDriversByRate();
	   }
	   
	   @PreAuthorize(ADMIN)
	   @RequestMapping(path = "users-by-role", method = RequestMethod.GET)
	   List<Long> countUsersByRole() {
		   logger.info("Method StatisticsController.countUsersByRole()");
	       return userService.countUsersByRole();
	   }
	   
	   @PreAuthorize(ADMIN)
	   @RequestMapping(path = "users-by-rate", method = RequestMethod.GET)
	   List<Long> countUsersByRate() {
		   logger.info("Method StatisticsController.countUsersByRole()");
	       return userService.countUsersByRate();
	   }

}

