package com.softserve.edu.delivery.dao;
/**
 * Author - Ivan Synyshyn
 */
import com.softserve.edu.delivery.domain.City;
import com.softserve.edu.delivery.domain.Point;


import java.math.BigDecimal;
import java.util.List;

public interface CityDao extends BaseDao<City, Long> {


    List<City> getCityByRegion(String region);

    List<City> getCityByName (String name);
    City findOne(Double x, Double y);

}
