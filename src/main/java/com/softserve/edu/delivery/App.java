package com.softserve.edu.delivery;

import com.softserve.edu.delivery.domain.Role;
import com.softserve.edu.delivery.domain.User;
import com.softserve.edu.delivery.utils.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

// Just on purpose for quick testing ...
public class App {
    public static void main(String[] args) {
        Session session = Hibernate.openSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            session.persist(new User().setEmail("example@site.com").setUserRole(Role.ADMIN));
            tx.commit();
            System.out.println(session.find(User.class, 1L));
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            try {
                session.close();
            } catch (HibernateException he) {
                he.printStackTrace();
            }
        }

        Hibernate.close();
    }
}