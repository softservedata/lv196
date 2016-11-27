package com.softserve.edu.delivery.service.impl;

import com.softserve.edu.delivery.domain.Location;
import com.softserve.edu.delivery.repository.LocationRepository;
import com.softserve.edu.delivery.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public Location persistLocation(Location location) {
        if (location.isCustom()) {
            location.setId(UUID.randomUUID().toString());
        }
        return locationRepository.save(location);
    }
}
