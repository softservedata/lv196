package com.softserve.edu.delivery.repository;

import com.softserve.edu.delivery.domain.Location;

import java.util.List;


public interface LocationRepository extends BaseRepository<Location, String> {
    List<Location> findTop10ByFormattedStartingWithIgnoreCaseOrderByCity(String name);
}
