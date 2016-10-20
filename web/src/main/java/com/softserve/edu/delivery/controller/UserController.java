package com.softserve.edu.delivery.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.softserve.edu.delivery.dto.UserProfileDto;
import com.softserve.edu.delivery.service.UserService;

@RestController
@RequestMapping(path = "users")
public class UserController {
	
	   @Autowired
	   UserService userService;

		Logger logger = LoggerFactory.getLogger(UserController.class.getName());
	    
	    @RequestMapping(path = "email", method = RequestMethod.GET)
	    UserProfileDto getUser(@RequestParam("email") String email) {
			logger.info("Method UserController.getUser()");
	        return userService.getUser(email);
	    }
	    
	    @RequestMapping(path = "change-status", method = RequestMethod.PUT)
	    UserProfileDto changeUserStatus(@RequestParam("email") String email, @RequestParam("status") Boolean status) {
			logger.info("Method UserController.changeUserStatus()");
	        return userService.changeUserStatus(email, status);
	    }
	    
	    @RequestMapping(path = "count-items", method = RequestMethod.GET)
	    Long countUsers() {
			logger.info("Method UserController.countUsers()");
	        return userService.countItems();
	    }
	    
	    @RequestMapping(path = "filter", method = RequestMethod.POST)
	    List<UserProfileDto> allUsers(@RequestBody UserProfileDto dto   ) {
			logger.info("Method UserController.allUsers()");
	        return userService.findUsers(dto);
	    }
}