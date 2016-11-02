package com.softserve.edu.delivery.repository;

import com.softserve.edu.delivery.domain.Car;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarRepository extends BaseRepository<Car, Long>{
    @Query
    List<Car> findByDriverEmail(String driverId);
}
