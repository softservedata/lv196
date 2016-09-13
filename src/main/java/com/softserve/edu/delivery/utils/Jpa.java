package com.softserve.edu.delivery.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Jpa {
    private Jpa() {
    }

    private static EntityManagerFactory emf = createEntityManagerFactory();
    private static EntityManager em = getEntityManager();

    public static EntityManager getEntityManager() {
        if (em == null) {
            try {
                em = emf.createEntityManager();
            } catch (Exception e) {
                emf.close();
                throw e;
            }
        }
        return em;
    }

    private static EntityManagerFactory createEntityManagerFactory() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("delivery");
        }
        return emf;
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
