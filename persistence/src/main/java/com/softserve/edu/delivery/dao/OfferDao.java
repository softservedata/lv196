package com.softserve.edu.delivery.dao;
/**
 * Author - Ivan Synyshyn
 */
import com.softserve.edu.delivery.domain.Offer;
import com.softserve.edu.delivery.domain.Order;

import java.util.List;

public interface OfferDao extends BaseDao<Offer, Long> {


    List getAllOffersByOrder(Order order);
}
