package com.softserve.edu.delivery.dao.impl;

import com.softserve.edu.delivery.dao.CityDao;
import com.softserve.edu.delivery.domain.City;

public class CityDaoImpl extends BaseDaoImpl<City, Long> implements CityDao {
    public CityDaoImpl(Class<City> clazz) {
        super(clazz);
    }
}
