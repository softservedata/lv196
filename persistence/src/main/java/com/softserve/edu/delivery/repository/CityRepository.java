package com.softserve.edu.delivery.repository;

import com.softserve.edu.delivery.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface CityRepository extends BaseRepository<City, Long>, JpaRepository<City, Long> {
    List<City> findByCityNameContainingIgnoreCase(String name);
}
