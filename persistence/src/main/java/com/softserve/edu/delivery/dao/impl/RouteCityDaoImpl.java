package com.softserve.edu.delivery.dao.impl;

import com.softserve.edu.delivery.dao.RouteCityDao;
import com.softserve.edu.delivery.domain.Order;
import com.softserve.edu.delivery.domain.RouteCities;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class RouteCityDaoImpl extends BaseDaoImpl<RouteCities, Long> implements RouteCityDao {
    public RouteCityDaoImpl() {
        super(RouteCities.class);
    }

    //author method - Petro Shtenovych
    @Override
    @SuppressWarnings("unchecked")
    public List<RouteCities> getRouteCitiesByOrder(Order order) {
        EntityManager em = super.getEntityManager();
        Query query = em.createQuery("select c from RouteCities c where c.route.id = :orderId");
        query.setParameter("orderId", order.getId());
        return query.getResultList();
    }
}
