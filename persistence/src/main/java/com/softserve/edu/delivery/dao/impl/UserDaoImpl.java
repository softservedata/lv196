package com.softserve.edu.delivery.dao.impl;

import java.util.List;
import java.util.Optional;

import com.softserve.edu.delivery.dao.UserDao;
import com.softserve.edu.delivery.domain.Order;
import com.softserve.edu.delivery.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class UserDaoImpl extends BaseDaoImpl<User, String> implements UserDao {

    public UserDaoImpl() {
        super(User.class);
    }

    public boolean exists(String email) {
        return getEntityManager()
                .createQuery("select 1 from User u where u.email = :email")
                .setParameter("email", email)
                .getResultList()
                .size() > 0;
    }
    
	@Override
	public List<User> getAllUsersInRange(int page, int size) {
		return getEntityManager()
				.createQuery("from User", User.class)
				.setFirstResult((page - 1) * size)
				.setMaxResults(size)
				.getResultList();
	}

	@Override
	public List<User> findAll() {
		EntityManager em = super.getEntityManager();
		Query query = em.createQuery("select o from User o");
		return query.getResultList();
	}

	@Override
	public Optional<User> findOne(String email) {
		return getEntityManager()
				.createQuery("select u from User u where u.email=:email", User.class)
				.setParameter("email", email)
				.getResultList()
				.stream()
				.findFirst();
	}

}
