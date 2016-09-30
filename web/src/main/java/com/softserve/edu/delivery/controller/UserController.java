package com.softserve.edu.delivery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.softserve.edu.delivery.dto.UserProfileDto;
import com.softserve.edu.delivery.service.UserService;

@RestController
@RequestMapping(path = "users")
public class UserController {
	
	   @Autowired
	   UserService userService;

	    @RequestMapping(path = "all", method = RequestMethod.GET)
	    List<UserProfileDto> allUsers() {
	        return userService.getAllUsers();
	    }
}