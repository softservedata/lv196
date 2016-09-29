package com.softserve.edu.delivery.dao;

import com.softserve.edu.delivery.domain.City;



import java.util.List;

public interface CityDao extends BaseDao<City, Long> {
    /**
     * Finds all city in region
     * @param region - name of region
     * @return list of city in region
     */
    List<City> getCityByRegion(String region);

    List<City> getCityByName (String name);

}
