package com.softserve.edu.delivery.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.function.Supplier;

public class TransactionManager {
    private TransactionManager() {
    }

    public static <T> T withTransaction(Supplier<T> transactionBlock) {
        EntityManager em = Jpa.getEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = em.getTransaction();
            transaction.begin();
            T result = transactionBlock.get();
            transaction.commit();
            return result;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }

    }

    public static void withTransaction(Runnable transactionBlock) {
        withTransaction(() -> {
            transactionBlock.run();
            return null;
        });
    }
}
