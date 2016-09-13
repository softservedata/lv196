package com.softserve.edu.delivery.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Petro Shtenovych
 */
public class Jpa {
    private Jpa(){};

    static {
        init();
    }

    private static EntityManager entityManager;

    public static EntityManager getEntityManager() {
        if (entityManager == null) {
            synchronized (Jpa.class) {
                if (entityManager == null) {
                    init();
                }
            }
        }
        return entityManager;
    }

    private static void init() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("delivery");
        entityManager = factory.createEntityManager();
    }

    public static void close() {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
    }
}
