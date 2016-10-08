package com.softserve.edu.delivery.dao.impl;

import com.softserve.edu.delivery.dao.OrderDao;
import com.softserve.edu.delivery.domain.Order;
import com.softserve.edu.delivery.domain.OrderStatus;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository("orderDao")
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
                        "where ord.id = :id and off.approved = true", String.class)
                .setParameter("id", id)
                .getResultList()
                .stream()
                .findFirst();
    }

    @Override
    public List<Order> findActiveOrders(String email) {
        return getEntityManager()
                .createQuery("select o from Order o where o.customer.email = :email " +
                        "and o.orderStatus in ('OPEN', 'IN_PROGRESS')" +
                        "order by o.registrationDate", Order.class)
                .setParameter("email", email)
                .getResultList();
    }

    @Override
    public List<Order> findClosedOrders(String email) {
        return getEntityManager()
                .createQuery("select o from Order o where o.customer.email = :email " +
                        "and o.orderStatus = 'CLOSED'" +
                        "order by o.registrationDate", Order.class)
                .setParameter("email", email)
                .getResultList();
    }

    @Override
    public List<Order> findAllOrdersByStatusPagination(String email, OrderStatus orderStatus, int page, int size) {
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

    @Override
    public List<Order> getOrderByCityFrom(Long id) {
        EntityManager em = super.getEntityManager();
        TypedQuery<Order> query = em.createQuery("select o from Order o where o.cityFrom.cityId = :cityId " +
                "and o.orderStatus = 'OPEN'", Order.class);
        query.setParameter("cityId", id);
        return query.getResultList();
    }

    @Override
    public List<Order> getOrderByCityTo(Long id) {
        EntityManager em = super.getEntityManager();
        TypedQuery<Order> query = em.createQuery("select o from Order o where o.cityTo.cityId = :cityId " +
                "and o.orderStatus = 'OPEN'", Order.class);
        query.setParameter("cityId", id);
        return query.getResultList();
    }

    @Override
    public List<Order> getOrderByWeight(BigDecimal weight) {
        EntityManager em = super.getEntityManager();
        TypedQuery<Order> query = em.createQuery("select o from Order o where o.weight <= :weight " +
                "and o.orderStatus = 'OPEN'", Order.class);
        query.setParameter("weight", weight);
        return query.getResultList();
    }

    @Override
    public List<Order> getOrderByArrivalDate(Timestamp arrivalDate) {
        EntityManager em = super.getEntityManager();
        TypedQuery<Order> query = em.createQuery("select o from Order o where o.arrivalDate >= :arrivalDate " +
                "and o.orderStatus = 'OPEN'", Order.class);
        query.setParameter("arrivalDate", arrivalDate);
        return query.getResultList();
    }

    @Override
    public List<Order> getAllOpenOrder() {
        EntityManager em = super.getEntityManager();
        TypedQuery<Order> query = em.createQuery("select o from Order o where o.orderStatus = 'OPEN'", Order.class);
        return query.getResultList();
    }
}
