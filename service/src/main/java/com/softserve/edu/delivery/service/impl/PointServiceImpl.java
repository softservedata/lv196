package com.softserve.edu.delivery.service.impl;

import com.softserve.edu.delivery.dao.CityDao;
import com.softserve.edu.delivery.domain.City;
import com.softserve.edu.delivery.domain.Point;
import com.softserve.edu.delivery.domain.RouteCities;
import com.softserve.edu.delivery.repository.RouteCitiesRepository;
import com.softserve.edu.delivery.service.PointService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

/**
 * Created by Natalia on 12.10.2016.
 */
@Service
@Transactional
public class PointServiceImpl implements PointService{
    private final RouteCitiesRepository routeCitiesRepository;

    public PointServiceImpl(CityDao cityDao, RouteCitiesRepository routeCitiesRepository){
        this.routeCitiesRepository = routeCitiesRepository;
    }
    @Override
    public void savePlace(Point point){
        Timestamp now = new Timestamp(new java.util.Date().getTime());

        RouteCities routeCities = new RouteCities();
        routeCities.setVisitDate(now);
        routeCities.setLatitude(point.getX());
        routeCities.setLongitude(point.getY());

        routeCitiesRepository.save(routeCities);
    }
    @Override
    public void deleteAll(){
        routeCitiesRepository.deleteAll();
    }
}
