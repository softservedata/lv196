package com.softserve.edu.delivery.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/*This class is responsible for configuring
hibernate session factory.*/
public class Hibernate {
    private Hibernate(){}

    private static SessionFactory sessionFactory;

    static {
        configure();
    }

    //Configuring SessionFactory
    private static void configure() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
            e.printStackTrace();
        }
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null || sessionFactory.isClosed()) {
            configure();
        }
        return sessionFactory;
    }

    //Destroying SessionFactory
    public static void close() {
        if (sessionFactory != null && ! sessionFactory.isClosed()) {
            sessionFactory.close();
        }
    }
}