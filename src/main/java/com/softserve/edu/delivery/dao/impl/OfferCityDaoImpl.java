package com.softserve.edu.delivery.dao.impl;

import com.softserve.edu.delivery.dao.OfferDao;
import com.softserve.edu.delivery.domain.Offer;

/**
 * Created by Ivan Rudnytskyi on 12.09.2016.
 */
public class OfferCityDaoImpl extends BaseDaoImpl<Offer> implements OfferDao {
    public OfferCityDaoImpl() {
        super(Offer.class);
    }
}
