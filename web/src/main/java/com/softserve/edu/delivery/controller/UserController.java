package com.softserve.edu.delivery.controller;

import com.softserve.edu.delivery.dto.UserProfileDto;
import com.softserve.edu.delivery.service.NotificationService;
import com.softserve.edu.delivery.service.UserService;
import com.softserve.edu.delivery.service.impl.UserAuthenticationDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.softserve.edu.delivery.config.SecurityConstraints.ADMIN;

@RestController
@RequestMapping(path = "users")
public class UserController {

	   @Autowired
	   UserService userService;
	   @Autowired
	   private NotificationService notificationService;

		@Autowired
		private UserAuthenticationDetailsImpl userDetails;

		Logger logger = LoggerFactory.getLogger(UserController.class.getName());

		@PreAuthorize(ADMIN)
		@RequestMapping(path = "email", method = RequestMethod.GET)
	    UserProfileDto getUser(@RequestParam("email") String email) {
			logger.info("Method UserController.getUser()");
	        return userService.getUser(email);
	    }

	    @PreAuthorize(ADMIN)
	    @RequestMapping(path = "change-status", method = RequestMethod.PUT)
	    UserProfileDto changeUserStatus(@RequestParam("email") String email, @RequestParam("status") Boolean status) {
			logger.info("Method UserController.changeUserStatus()");
			notificationService.changeUserStatus(email,status);
	        return userService.changeUserStatus(email, status);
	    }

	    @PreAuthorize(ADMIN)
	    @RequestMapping(path = "count-items", method = RequestMethod.GET)
	    Long countUsers() {
			logger.info("Method UserController.countUsers()");
	        return userService.countItems();
	    }

	    @PreAuthorize(ADMIN)
	    @RequestMapping(path = "filter", method = RequestMethod.POST)
	    List<UserProfileDto> allUsers(@RequestBody UserProfileDto dto   ) {
			logger.info("Method UserController.allUsers()");
	        return userService.findUsers(dto);
	    }
}