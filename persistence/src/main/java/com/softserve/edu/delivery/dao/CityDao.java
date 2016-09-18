package com.softserve.edu.delivery.dao;
/**
 * Author - Ivan Synyshyn
 */
import com.softserve.edu.delivery.domain.City;



import java.util.List;

public interface CityDao extends BaseDao<City, Long> {


    List<City> getCityByRegion(String region);

    List<City> getCityByName (String name);

}
