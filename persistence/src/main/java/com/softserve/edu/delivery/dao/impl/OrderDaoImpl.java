package com.softserve.edu.delivery.dao.impl;

import com.softserve.edu.delivery.dao.OrderDao;
import com.softserve.edu.delivery.domain.Feedback;
import com.softserve.edu.delivery.domain.Offer;
import com.softserve.edu.delivery.domain.Order;
import com.softserve.edu.delivery.domain.OrderStatus;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class OrderDaoImpl extends BaseDaoImpl<Order, Long> implements OrderDao {

    public OrderDaoImpl() {
        super(Order.class);
    }

    public List<Order> findAllOrdersByStatus(String email, int page, int size, OrderStatus orderStatus) {
        return getEntityManager()
                .createQuery("select o from Order o where o.user.email = :email " +
                        "and o.orderStatus = :orderStatus " +
                        "order by o.registrationDate", Order.class)
                .setParameter("email", email)
                .setParameter("orderStatus", orderStatus)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
    }


    public List<Order> changeStatus(String id, Offer offer) {
        return findAll();
    }

    @Override
    public String feedback(String order_id, Feedback feedback) {
        return null;
    }

    // next 4 methods author Ivan Synyshyn
    @Override
    public List<Order> getOrderByCityFrom(String name) {
        EntityManager em = super.getEntityManager();
        Query query = em.createQuery("select from Order where cityFrom = :name");
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public List<Order> getOrderByCityTo(String name) {
        EntityManager em = super.getEntityManager();
        Query query = em.createQuery("select from Order where cityTo = :name");
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public List<Order> getOrderByWeight(BigDecimal weight) {
        EntityManager em = super.getEntityManager();
        Query query = em.createQuery("select from Order where weight = :weight");
        query.setParameter("weight", weight);
        return query.getResultList();
    }

    @Override
    public List<Order> getOrderByArrivalDate(Timestamp arrivalDate) {
        EntityManager em = super.getEntityManager();
        Query query = em.createQuery("select from Order where arrivalDate = :arrivalDate");
        query.setParameter("arrivalDate", arrivalDate);
        return query.getResultList();
    }
}
