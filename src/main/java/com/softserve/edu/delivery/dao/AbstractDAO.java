package com.softserve.edu.delivery.dao;

import com.softserve.edu.delivery.utils.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public abstract class AbstractDAO<T> implements DAO<T> {

    private Class<T> clazz;

    public AbstractDAO(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public void add(T element) {
        Session session = null;
        Transaction tx = null;
        try {
            session = Hibernate.openSession();
            tx = session.beginTransaction();
            session.save(element);
            tx.commit();
        }catch (Exception exception) {
           rollback(tx);
        }finally{
            closeSession(session);
        }
    }

    @Override
    public void update(T element) {
        Session session = null;
        Transaction tx = null;
        try {
            session = Hibernate.openSession();
            tx = session.beginTransaction();
            session.update(element);
            tx.commit();
        }catch (Exception exception) {
            rollback(tx);
        }finally {
            closeSession(session);
        }
    }

    @Override
    public void remove(T element) {
        Session session = null;
        Transaction tx = null;
        try {
            session = Hibernate.openSession();
            tx = session.beginTransaction();
            session.delete(element);
            tx.commit();
        }catch (Exception exception) {
            rollback(tx);
        }finally {
            closeSession(session);
        }
    }

    @Override
    public T getById(Long id) {
        Session session = null;
        Transaction tx = null;
        T result = null;
        try {
            session = Hibernate.openSession();
            tx = session.beginTransaction();
            result = session.get(this.clazz, id); // return null if id is not exists in db
            tx.commit();
        }catch (Exception exeption) {
            rollback(tx);
        }finally {
            closeSession(session);
        }
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        Session session = null;
        Transaction tx = null;
        List<T> result = null;
        try {
            session = Hibernate.openSession();
            tx = session.beginTransaction();
            result = session.createCriteria(clazz).list();
            tx.commit();
        }catch (Exception exception) {
            rollback(tx);
        }finally {
            closeSession(session);
        }
        return  result;
    }


    private static void rollback(Transaction tx) {
        if (tx != null) {
            tx.rollback();
        }
    }

    private static void closeSession(Session session) {
        if (session != null && session.isOpen()) {
            session.close();
        }
    }
}