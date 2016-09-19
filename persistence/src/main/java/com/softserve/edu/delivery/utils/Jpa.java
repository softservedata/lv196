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
        if (emf == null) {
            throw new IllegalStateException("Entity manager factory is not defined");
        }
        return emf.createEntityManager();
    }

    private static EntityManagerFactory createEntityManagerFactory() {
        if (emf == null) {
            try {
                emf = Persistence.createEntityManagerFactory("delivery");
            } catch (Exception e) {
                e.printStackTrace();
                close();
            }
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
