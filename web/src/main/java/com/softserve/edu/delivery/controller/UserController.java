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
	    UserProfileDto changeUserStatus(@RequestParam("email") String email, @RequestParam("status") String value) {
			logger.info("Method UserController.changeUserStatus()");
			Boolean status = null;
			try{
				status = Boolean.parseBoolean(value);
			} catch(IllegalArgumentException e) {
	            e.printStackTrace();
			}
	        return userService.changeUserStatus(email, status);
	    }
	    
	    @RequestMapping(path = "count-pages", method = RequestMethod.GET)
	    Long countUsers() {
			logger.info("Method UserController.countUsers()");
	        return userService.getPages();
	    }
	    
	    @RequestMapping(path = "filter", method = RequestMethod.GET)
	    List<UserProfileDto> allUsers(@RequestParam("rows") String arg0, @RequestParam("page") String arg1, @RequestParam("fname") String fname,
	    							  @RequestParam("lname") String lname, @RequestParam("email") String email, @RequestParam("role") String role,
	    							  @RequestParam("status") String blocked) {
			logger.info("Method UserController.allUsers()");
	        Integer rows = null;
	        Integer page = null;
	        Boolean status = null;
	        if (lname.equals("")) {
	        	lname = "%";
	        }
	        if (fname.equals("")) {
	        	fname = "%";
	        }
	        if (email.equals("")) {
	        	email = "%";
	        }
	        if (role.equals("")) {
	        	role = null;
	        }
	        try {
	        	rows = Integer.parseInt(arg0);
	        	page = Integer.parseInt(arg1);
	        	status = Boolean.parseBoolean(blocked);
	        } catch (IllegalArgumentException e) {
	            e.printStackTrace();
	        }
	        return userService.findUsers(fname, lname, email, role, status, rows, page);
	    }
}