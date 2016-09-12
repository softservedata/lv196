package com.softserve.edu.delivery.dao.impl;

import com.softserve.edu.delivery.dao.RouteDao;
import com.softserve.edu.delivery.domain.Route;

/**
 * Created by Ivan Rudnytskyi on 12.09.2016.
 */
public class RouteCityDao extends BaseDaoImpl<Route, Long> implements RouteDao {
    public RouteCityDao() {
        super(Route.class);
    }
}
