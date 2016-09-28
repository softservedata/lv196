package com.softserve.edu.delivery.dao.impl;

import java.util.List;

import com.softserve.edu.delivery.dao.UserDao;
import com.softserve.edu.delivery.domain.User;
import org.springframework.stereotype.Repository;

@Repository("userDao")
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
}
