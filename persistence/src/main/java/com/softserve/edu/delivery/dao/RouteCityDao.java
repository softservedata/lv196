package com.softserve.edu.delivery.dao;

import com.softserve.edu.delivery.domain.Order;
import com.softserve.edu.delivery.domain.RouteCities;

import java.util.List;

/**
 * Created by Ivan Rudnytskyi on 11.09.2016.
 */

public interface RouteCityDao extends BaseDao<RouteCities, Long> {

    //Petro Shtenovych
    List<RouteCities> getRouteCitiesByOrder(Order order);
    //Petro Shtenovych
    RouteCities getRouteCityWhenLastVisitedByOrder(Order order);


}
