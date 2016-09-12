package com.softserve.edu.delivery.dao.impl;

import com.softserve.edu.delivery.dao.RegionDao;
import com.softserve.edu.delivery.domain.Region;

/**
 * Created by Ivan Rudnytskyi on 12.09.2016.
 */
public class RegionDaoImpl extends BaseDaoImpl<Region, Long> implements RegionDao {
    public RegionDaoImpl() {
        super(Region.class);
    }
}
