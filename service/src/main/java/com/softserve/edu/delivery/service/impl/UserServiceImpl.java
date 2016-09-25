package com.softserve.edu.delivery.service.impl;

import com.softserve.edu.delivery.dao.UserDao;
import com.softserve.edu.delivery.domain.User;
import com.softserve.edu.delivery.dto.UserAuthDTO;
import com.softserve.edu.delivery.dto.UserProfileDto;
import com.softserve.edu.delivery.dto.UserProfileFilterDto;
import com.softserve.edu.delivery.exception.UserNotFoundException;
import com.softserve.edu.delivery.exception.WrongPasswordException;
import com.softserve.edu.delivery.service.UserService;
import com.softserve.edu.delivery.utils.Jpa;
import com.softserve.edu.delivery.utils.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean exists(String email) {
        return TransactionManager.withoutTransaction(() -> userDao.exists(email));
    }

    @Override
    public void register(User user) {
        TransactionManager.withoutTransaction(() -> {
            if (!userDao.exists(user.getEmail())) {
                userDao.save(user);
            } else {
                throw new IllegalArgumentException("User with given email already exists.");
            }
        });
    }


    public void register(User user, boolean withoutLambda) {
        if (userDao.exists(user.getEmail())) {
            throw new IllegalArgumentException("User with given email already exists.");
        } else {
            userDao.save(user);
        }
    }

    /*** This method verifies user credentials(login page)
     * author Petro Shtenovych
     * @param userAuthDTO with credentials email and password
     * @throws IllegalArgumentException if param ref null
     * @throws UserNotFoundException if user isn't registered
     * @throws WrongPasswordException if user typed wrong password
     * @return UserProfileDto instance which has user profile information
     */
    @Override
    public UserProfileDto verificationLogin(UserAuthDTO userAuthDTO) {
        if (userAuthDTO == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (this.userDao.exists(userAuthDTO.getEmail())) {
            User dbUser = this.userDao.findOne(userAuthDTO.getEmail()).get();
            if ( ! checkPassword(userAuthDTO, dbUser)) {
                throw new WrongPasswordException();
            }
            return UserProfileDto.create(dbUser);
        }else {
            throw new UserNotFoundException(userAuthDTO.getEmail());
        }
    }
    
	@Override
	public List<UserProfileDto> getAllUsers(int page, int size, UserProfileFilterDto filter) {
		return TransactionManager.withoutTransaction(() ->
				 userDao
						.getAllUsersInRange(page, size)
						.stream()
						.filter(filter)
						.map(UserProfileDto::create)
						.collect(Collectors.toList())
		);
	}

	@Override
	public UserProfileDto changeUserStatus(String mail, boolean blocked) throws IllegalStateException{
		return TransactionManager.withoutTransaction(() ->
				 userDao
						.findOne(mail)
						.map(user -> userDao.update(user.setBlocked(blocked)))
						.map(UserProfileDto::create)
						.<IllegalStateException>orElseThrow(() -> new IllegalStateException("User: " + mail + " not found!"))
		);
	}
	
	@Override
	public List<UserProfileDto> changeUsersStatus(Map<String, Boolean> map) {
		return TransactionManager.withoutTransaction(() ->
				map
					.keySet().stream()
					.map(mail -> changeUserStatus(mail, map.get(mail)))
					.collect(Collectors.toList())
		);			
	}

	//<---------------------Private------------------------->

    private static boolean checkPassword(UserAuthDTO user, User dbUser) {
        if( ! user.getPassword().equals(dbUser.getPassword())) {
            return false;
        }
        return true;
    }
}