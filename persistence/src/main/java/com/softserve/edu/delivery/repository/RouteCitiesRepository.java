package com.softserve.edu.delivery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.softserve.edu.delivery.domain.RouteCities;

public interface RouteCitiesRepository extends BaseRepository<RouteCities, Long>{
    
    @Query("select c from RouteCities c where c.route.id = :orderId")
    List<RouteCities> findRouteCitiesByOrderId(@Param("orderId") Long orderId);
    
}
