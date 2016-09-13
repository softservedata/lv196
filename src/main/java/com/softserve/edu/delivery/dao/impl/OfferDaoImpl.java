package com.softserve.edu.delivery.dao.impl;

import com.softserve.edu.delivery.dao.OfferDao;
import com.softserve.edu.delivery.domain.Offer;

/**
 * Created by Ivan Rudnytskyi on 12.09.2016.
 */
public class OfferDaoImpl extends BaseDaoImpl<Offer, Long> implements OfferDao {
    public OfferDaoImpl() {
        super(Offer.class);
    }
}
