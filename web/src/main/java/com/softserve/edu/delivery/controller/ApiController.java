package com.softserve.edu.delivery.controller;

import com.softserve.edu.delivery.dto.ConfigDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class ApiController {

    @Value("${websocket.endpoint}")
    String wsEndpoint;

    @Value("${google.geocode.key}")
    String googleGeocodeKey;

    @RequestMapping(path = "configure", method = RequestMethod.GET)
    ConfigDto configure(Principal principal) {
        return new ConfigDto(principal.getName(), wsEndpoint, googleGeocodeKey);
    }

}
