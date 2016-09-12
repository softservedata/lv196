package com.softserve.edu.delivery.dao.impl;

import com.softserve.edu.delivery.dao.RouteDao;
import com.softserve.edu.delivery.domain.Route;

/**
 * Created by Ivan Rudnytskyi on 12.09.2016.
 */
public class RouteCityDaoImpl extends BaseDaoImpl<Route, Long> implements RouteDao {
    public RouteCityDaoImpl() {
        super(Route.class);
    }
}
