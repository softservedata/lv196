package com.softserve.edu.delivery.service;

import com.softserve.edu.delivery.domain.City;
import com.softserve.edu.delivery.domain.Region;
import com.softserve.edu.delivery.domain.State;

import java.util.Set;

/**
 * Author - Ivan Synyshyn
 */
public interface CityService {
	
	City addCity(City city);
	City editCity(City city);
	City deleteCity(City city);
	Set<City> getAllCities();
	Set<City> findCityByRegion(Region region);
	Set<City> findCityByState(State state);
	Set<City> getCityByName(String name);

}
