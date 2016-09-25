package com.softserve.edu.delivery.dao.impl;

import com.softserve.edu.delivery.dao.CarDao;
import com.softserve.edu.delivery.domain.Car;
import org.springframework.stereotype.Repository;

@Repository("carDao")
public class CarDaoImpl extends BaseDaoImpl<Car, Long> implements CarDao {

    public CarDaoImpl() {
        setClazz(Car.class);
    }

}
