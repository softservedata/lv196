package com.softserve.edu.delivery.dao.impl;

import com.softserve.edu.delivery.dao.OfferDao;
import com.softserve.edu.delivery.domain.Offer;
import com.softserve.edu.delivery.domain.Order;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Ivan Rudnytskyi on 12.09.2016.
 */
public class OfferDaoImpl extends BaseDaoImpl<Offer, Long> implements OfferDao {
    public OfferDaoImpl() {
        super(Offer.class);
    }


    /**Method returns all offers by given order
     * Author - Petro Shtenovych
     * */
    @Override
    @SuppressWarnings("unchecked")
    public List<Offer> getAllOffersByOrder(Order order) {
        EntityManager em = super.getEntityManager();
        Query query = em.createQuery("select of, o from Order of join of.offers o where o.id = :id ");
        query.setParameter("id", order.getId());
        return (List<Offer>) query.getResultList();
    }
}
