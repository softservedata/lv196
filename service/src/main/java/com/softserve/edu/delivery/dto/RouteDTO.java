package com.softserve.edu.delivery.dto;

import com.softserve.edu.delivery.domain.Baggage;
import com.softserve.edu.delivery.domain.City;
import com.softserve.edu.delivery.domain.User;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Petro Shtenovych
 * */

public class RouteDTO {

    private City lastLocation;

    private Timestamp lastTimeVisited;

    private List<City> visitedLocations;

    private Baggage baggage;

    private User owner;

    private User transporter;


}
