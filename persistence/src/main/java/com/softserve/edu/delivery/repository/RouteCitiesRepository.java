package com.softserve.edu.delivery.repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import com.softserve.edu.delivery.domain.Point;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.softserve.edu.delivery.domain.RouteCities;

public interface RouteCitiesRepository extends BaseRepository<RouteCities, Long>{

    List<RouteCities> findRouteCitiesByOrderId(@Param("orderId") Long orderId);

    @Query("select r from RouteCities r where r.order.id = :id and r.visitDate is not null")
    List<RouteCities> findByOrderIdDateNotNull(@Param("id") Long id);

    List<RouteCities> findByOrderId(@Param("id") Long id);

    @Query("select r from  RouteCities r WHERE r.visitDate = (SELECT MAX(s.visitDate) FROM RouteCities s where s.order.id = :id) and r.order.id = :id")
    Optional<RouteCities> findCurrentLocation(@Param("id") Long id);

    @Modifying
    @Query("update RouteCities r set r.visitDate = :date where r.order.id = :id and r.x = :x and r.y = :y")
    void update(@Param("id") Long id, @Param("date") Timestamp date, @Param("x") Double x, @Param("y") Double y);

    @Query("select r from RouteCities r where r.order.id = :id and r.visitDate is null")
    List<RouteCities> findPointToUpdate(@Param("id") Long id);
}
