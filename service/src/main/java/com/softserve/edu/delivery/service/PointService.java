package com.softserve.edu.delivery.service;

import com.softserve.edu.delivery.domain.Point;

import java.math.BigDecimal;

/**
 * Created by Natalia on 12.10.2016.
 */
public interface PointService {
    void savePleace(Point point);
    void deleteAll();
}
