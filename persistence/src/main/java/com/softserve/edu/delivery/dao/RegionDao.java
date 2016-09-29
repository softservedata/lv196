package com.softserve.edu.delivery.dao;

import com.softserve.edu.delivery.domain.Region;
import java.util.List;

public interface RegionDao extends BaseDao<Region, Long> {
    /**
     * Finds all region in state
     * @param state name of state which chosen by user
     * @return list of region in state
     */
    List<Region> getRegionByState(String state);
}
