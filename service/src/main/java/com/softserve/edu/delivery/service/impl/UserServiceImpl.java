package com.softserve.edu.delivery.service.impl;

import com.softserve.edu.delivery.dao.UserDao;
import com.softserve.edu.delivery.domain.User;
import com.softserve.edu.delivery.dto.UserAuthDTO;
import com.softserve.edu.delivery.dto.UserProfileDto;
import com.softserve.edu.delivery.dto.UserProfileFilterDto;
import com.softserve.edu.delivery.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean exists(String email) {
        return userDao.exists(email);
    }

    @Override
    public void register(User user) {
        if (!userDao.exists(user.getEmail())) {
            userDao.save(user);
        } else {
            throw new IllegalArgumentException("User with given email already exists.");
        }
    }

    /***
     * This method verifies user credentials(login page)
     * author Petro Shtenovych
     *
     * @param user with credentials email and password
     * @return true if user verification was success
     * @throws IllegalArgumentException if param ref null
     * @throws RuntimeException         if database errors occur
     */
    @Override
    public boolean verificationLogin(UserAuthDTO user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        EntityTransaction tx = null;
        try {
            EntityManager entityManager = /*Jpa.getEntityManager(); Entity manager in service? */ null;
            tx = entityManager.getTransaction();
            tx.begin();
            if (!this.userDao.exists(user.getEmail())) {
                tx.commit();
                return false;
            } else {
                User dbUser = this.userDao.findOne(user.getEmail()).get();
                tx.commit();
                System.out.println(dbUser);
                if (!checkPassword(user, dbUser)) {
                    return false;
                }
            }
        } catch (RuntimeException ex) {
            if (tx != null) {
                tx.rollback();
                throw ex;
            }
        }
        return true;
    }

    @Override
    public List<UserProfileDto> getAllUsers(int page, int size, UserProfileFilterDto filter) {
        return userDao
                .getAllUsersInRange(page, size)
                .stream()
                .filter(filter)
                .map(UserProfileDto::create)
                .collect(Collectors.toList());
    }

    @Override
    public UserProfileDto changeUserStatus(String mail, boolean blocked) throws IllegalStateException {
        return userDao
                .findOne(mail)
                .map(user -> userDao.update(user.setBlocked(blocked)))
                .map(UserProfileDto::create)
                .<IllegalStateException>orElseThrow(() -> new IllegalStateException("User: " + mail + " not found!"));
    }

    @Override
    public List<UserProfileDto> changeUsersStatus(Map<String, Boolean> map) {
        return map
                .keySet().stream()
                .map(mail -> changeUserStatus(mail, map.get(mail)))
                .collect(Collectors.toList());
    }

    //<---------------------Private------------------------->

    private static boolean checkPassword(UserAuthDTO user, User dbUser) {
        if (!user.getPassword().equals(dbUser.getPassword())) {
            return false;
        }
        return true;
    }


}
