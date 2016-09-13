package com.softserve.edu.delivery.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Petro Shtenovych
 */
public class Jpa {
    private Jpa() {}

    private static EntityManagerFactory emf = createEntityManagerFactory();
    private static EntityManager em = getEntityManager();

    public static EntityManager getEntityManager() {
        if (em == null) {
            em = emf.createEntityManager();
        }
        return em;
    }

    private static EntityManagerFactory createEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("delivery");
    }

    public static void close() {
        if (em != null && em.isOpen()) {
            em.close();
        }

        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
