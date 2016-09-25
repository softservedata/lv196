package com.softserve.edu.delivery.utils;

import java.util.function.Supplier;

public class TransactionManager {

    public static <T> T withoutTransaction(Supplier<T> transactionBlock) {
        return transactionBlock.get();
    }

    public static void withoutTransaction(Runnable transactionBlock) {
        withoutTransaction(() -> {
            transactionBlock.run();
            return null;
        });
    }
}
