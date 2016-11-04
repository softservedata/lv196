package com.softserve.edu.delivery.controller;

import static com.softserve.edu.delivery.config.SecurityConstraints.ADMIN;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
	   List<List<Long>> countOrdersByTime() {
		   logger.info("Method StatisticsController.countOrdersByTime()");
		   List<List<Long>> ordersQuantity = new ArrayList<>();
		   ordersQuantity.add(orderService.countOrdersByTime());
	       return ordersQuantity;
	   }
	   
	   @PreAuthorize(ADMIN)
	   @RequestMapping(path = "hours", method = RequestMethod.GET)
	   List<String> getHoursToThisMoment() {
		   logger.info("Method StatisticsController.getDayHours()");
	       return orderService.getHoursToThisMoment();
	   }
	   
	   @PreAuthorize(ADMIN)
	   @RequestMapping(path = "month-orders", method = RequestMethod.GET)
	   List<List<Long>> countOrdersByDay() {
		   logger.info("Method StatisticsController.countOrdersByTime()");
		   List<List<Long>> ordersQuantity = new ArrayList<>();
		   ordersQuantity.add(orderService.countOrdersByDay());
	       return ordersQuantity;
	   }
	   
	   @PreAuthorize(ADMIN)
	   @RequestMapping(path = "days", method = RequestMethod.GET)
	   List<String> getDaysToThisMoment() {
		   logger.info("Method StatisticsController.getDayHours()");
	       return orderService.getDaysToThisMoment();
	   }
	   
	   @PreAuthorize(ADMIN)
	   @RequestMapping(path = "year-orders", method = RequestMethod.GET)
	   List<List<Long>> countOrdersByMonth() {
		   logger.info("Method StatisticsController.countOrdersByTime()");
		   List<List<Long>> ordersQuantity = new ArrayList<>();
		   ordersQuantity.add(orderService.countOrdersByMonth());
	       return ordersQuantity;
	   }
	   
	   @PreAuthorize(ADMIN)
	   @RequestMapping(path = "month", method = RequestMethod.GET)
	   List<String> getMonthDays() {
	       logger.info("Method StatisticsController.getDayHours()");
	       return orderService.getMonthsToThisMoment();
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

