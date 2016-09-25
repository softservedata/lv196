package com.softserve.edu.delivery.dao.impl;

import com.softserve.edu.delivery.dao.RegionDao;
import com.softserve.edu.delivery.domain.Region;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Ivan Rudnytskyi on 12.09.2016.
 */
@Repository("regionDao")
public class RegionDaoImpl extends BaseDaoImpl<Region, Long> implements RegionDao {
    public RegionDaoImpl() {
        setClazz(Region.class);
    }


    @Override
    public List<Region> getRegionByState(String state) {
        return getEntityManager()
                .createQuery("select r from Region r where r.state.stateName =: state", Region.class)
                .setParameter("state", state)
                .getResultList();
    }

}
