package com.softserve.edu.delivery.dao;


import com.softserve.edu.delivery.domain.Region;

import java.util.List;

/**
 * Created by Ivan Rudnytskyi on 11.09.2016.
 */
public interface RegionDao extends BaseDao<Region, Long> {
    List<Region> getRegionByState(String state);
}
