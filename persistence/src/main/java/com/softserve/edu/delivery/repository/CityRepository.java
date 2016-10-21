package com.softserve.edu.delivery.repository;

import com.softserve.edu.delivery.domain.City;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CityRepository extends BaseRepository<City, Long> {
    List<City> findTop10ByCityNameStartingWithIgnoreCaseOrderByCityName(String name);

    @Query("select c from City c where c.cityName = :name")
    List<City> getCityByName(@Param("name")String name);

    @Query("select c from City c where c.cityName = :cityName " +
            "and (:regionName is null or c.region.regionName = :regionName)" +
            "and (:stateName is null or c.region.state.stateName = :stateName)")
    City getCityByNameRegionNameStateName(@Param("cityName")String cityName,
                                          @Param("regionName") String regionName,
                                          @Param("stateName") String stateName);
    
}
