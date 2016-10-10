package com.softserve.edu.delivery.repository;

import com.softserve.edu.delivery.domain.City;

import java.util.List;


public interface CityRepository extends BaseRepository<City, Long> {
    List<City> findTop10ByCityNameStartingWithIgnoreCaseOrderByCityName(String name);
}
