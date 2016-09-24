package com.softserve.edu.delivery.dao.impl;

import com.softserve.edu.delivery.dao.OrderDao;
import com.softserve.edu.delivery.domain.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderDaoImpl extends BaseDaoImpl<Order, Long> implements OrderDao {

    public OrderDaoImpl() {
        super(Order.class);
    }

    @Override
    public Optional<String> findDriverNameByOrderId(Long id) {
        return getEntityManager()
                .createQuery("select concat(u.firstName, ' ', u.lastName) from User u " +
                        "join u.cars c " +
                        "join c.offers off " +
                        "join off.order ord " +
                        "where ord.id = :id and off.isApproved = true", String.class)
                .setParameter("id", id)
                .getResultList()
                .stream()
                .findFirst();
    }

    @Override
    public List<Order> findAllOrdersByStatus(String email, int page, int size, OrderStatus orderStatus) {
        return getEntityManager()
                .createQuery("select o from Order o where o.customer.email = :email " +
                        "and o.orderStatus = :orderStatus " +
                        "order by o.registrationDate", Order.class)
                .setParameter("email", email)
                .setParameter("orderStatus", orderStatus)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
    }

    public void changeStatus(String order_id, Boolean offerStatus) {

        getEntityManager()                                                      //update offer's with offerStatus where is our order
                .createQuery("update Offer set isApproved=:offerStatus where order = :order")
                .setParameter("order", Long.parseLong(order_id))
                .setParameter("offerStatus", offerStatus)
                .executeUpdate();
    }


    // next 4 methods author Ivan Synyshyn
    @Override
    public List<Order> getOrderByCityFrom(Long id) {
        EntityManager em = super.getEntityManager();
        Query query = em.createQuery("select o from Order o where o.cityFrom.cityId = :cityId");
        query.setParameter("cityId", id);
        return query.getResultList();
    }

    @Override
    public List<Order> getOrderByCityTo(Long id) {
        EntityManager em = super.getEntityManager();
        Query query = em.createQuery("select o from Order o where o.cityTo.cityId = :cityId");
        query.setParameter("cityId", id);
        return query.getResultList();
    }

    @Override
    public List<Order> getOrderByWeight(BigDecimal weight) {
        EntityManager em = super.getEntityManager();
        Query query = em.createQuery("select o from Order o where o.weight <= :weight");
        query.setParameter("weight", weight);
        return query.getResultList();
    }

    @Override
    public List<Order> getOrderByArrivalDate(Timestamp arrivalDate) {
        EntityManager em = super.getEntityManager();
        Query query = em.createQuery("select o from Order o where o.arrivalDate <= :arrivalDate");
        query.setParameter("arrivalDate", arrivalDate);
        return query.getResultList();
    }
}
