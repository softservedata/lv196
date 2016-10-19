package com.softserve.edu.delivery.dao.impl;

import com.softserve.edu.delivery.dao.RouteCityDao;
import com.softserve.edu.delivery.domain.Order;
import com.softserve.edu.delivery.domain.RouteCities;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository("routeCityDao")
public class RouteCityDaoImpl extends BaseDaoImpl<RouteCities, Long> implements RouteCityDao {
    public RouteCityDaoImpl() {
        super(RouteCities.class);
    }

    /**author method - Petro Shtenovych
     * Method returns all RouteCities(tracking) by given Order
     * */
    @Override
    @SuppressWarnings("unchecked")
    public List<RouteCities> getRouteCitiesByOrder(Order order) {
        EntityManager em = super.getEntityManager();
        Query query = em.createQuery("select c from RouteCities c where c.route.id = :orderId");
        query.setParameter("orderId", order.getId());
        return query.getResultList();
    }

    /**
     * author - Petro Shtenovych
     * Method returns RouteCities when transporter last visited
     * */
    @Override
    @SuppressWarnings("unchecked")
    public RouteCities getRouteCityWhenLastVisitedByOrder(Order order) {
        EntityManager em = super.getEntityManager();
        // Query query = em.createQuery("select max(x.visitDate) from RouteCities x where x.route.id = :id");
        Query query = em.createQuery("select x from RouteCities x where x.route.id = :id");
        query.setParameter("id", order.getId());
        List<RouteCities> allRoutesCities = (List<RouteCities>) query.getResultList();
        return calculateLastVisited(allRoutesCities);
    }

    //<--------------------------------Private------------------------------------------->

    private static RouteCities calculateLastVisited(List<RouteCities> allRoutesCities) {
        RouteCities result = null;
        if ( ! allRoutesCities.isEmpty()) {
            result = allRoutesCities.get(0);
            for (RouteCities rc : allRoutesCities) {
                if (rc.getVisitDate().after(result.getVisitDate())) {
                    result = rc;
                }
            }
        }
        return result;
    }
    @Override
    public void deleteAll() {
        getEntityManager().createQuery("delete from RouteCities").executeUpdate();
    }
}
