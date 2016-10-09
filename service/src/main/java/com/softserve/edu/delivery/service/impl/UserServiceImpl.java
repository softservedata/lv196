package com.softserve.edu.delivery.service.impl;

import com.softserve.edu.delivery.dao.UserDao;
import com.softserve.edu.delivery.domain.Role;
import com.softserve.edu.delivery.domain.User;
import com.softserve.edu.delivery.dto.UserAuthDTO;
import com.softserve.edu.delivery.dto.UserProfileDto;
import com.softserve.edu.delivery.dto.UserProfileFilterDto;
import com.softserve.edu.delivery.dto.UserRegistrationDTO;
import com.softserve.edu.delivery.exception.EmailExistsException;
import com.softserve.edu.delivery.exception.UserNotFoundException;
import com.softserve.edu.delivery.exception.WrongPasswordException;
import com.softserve.edu.delivery.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if ( ! this.exists(email)) {
            throw new UsernameNotFoundException("Email " + email + " is not registered");
        }

        User user = userDao.findOne(email).get();

        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNotExpired = true;
        boolean accountNonLocked = ! user.getBlocked();
        String role = user.getUserRole().getName();
        List<GrantedAuthority> listUserRoles = new ArrayList<>();
        listUserRoles.add(new SimpleGrantedAuthority(role));


        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), enabled, accountNonExpired,
                credentialsNotExpired, accountNonLocked, listUserRoles);
    }

    @Override
    public boolean exists(String email) {
        return userDao.exists(email);
    }

    @Override
    public void register(UserRegistrationDTO userRegDTO) {
        if (exists(userRegDTO.getEmail())) {
            throw new EmailExistsException(userRegDTO.getEmail());
        } else {
            User newUser = new User();
            newUser.setEmail(userRegDTO.getEmail());
            newUser.setPassword(this.passwordEncoder.encode(userRegDTO.getPassword()));
            newUser.setFirstName(userRegDTO.getFirstName());
            newUser.setLastName(userRegDTO.getLastName());
            newUser.setPhone(userRegDTO.getPhoneNumber());
            newUser.setPassport(userRegDTO.getPassport());
            newUser.setPhotoUrl(userRegDTO.getPhotoUrl());
            newUser.setBlocked(false);
            newUser.setUserRole(Role.CUSTOMER);

            userDao.save(newUser);
        }
    }


    public void register(User user) {
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
     * @return UserProfileDto instance which has user profile information
     */
    @Override
    public UserProfileDto verificationLogin(UserAuthDTO userAuthDTO) {
        if (userAuthDTO == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (this.userDao.exists(userAuthDTO.getEmail())) {
            User dbUser = this.userDao.findOne(userAuthDTO.getEmail()).get();
            return UserProfileDto.create(dbUser);
        }else {
            throw new UserNotFoundException(userAuthDTO.getEmail());
        }
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
	public UserProfileDto changeUserStatus(String mail, boolean blocked) throws IllegalStateException{
		return userDao
                .findOne(mail)
				.map(user -> userDao.update(user.setBlocked(blocked)))
                .map(UserProfileDto::create)
                .<IllegalStateException>orElseThrow(() -> new IllegalStateException("User: " + mail + " not found!"));
	}

	@Override
	public List<UserProfileDto> changeUsersStatus(Map<String, Boolean> map) {
		return 	map
			.keySet().stream()
			.map(mail -> changeUserStatus(mail, map.get(mail)))
			.collect(Collectors.toList());
	}

    @Override
    public List<UserProfileDto> getAllUsers() {
        List<UserProfileDto> result = new LinkedList<>();
        List<User> allUsers = userDao.findAll();
        for (User user : allUsers) {
            result.add(UserProfileDto.create(user));
        }
        return result;
    }

    //<---------------------Private------------------------->

    private static boolean checkPassword(UserAuthDTO user, User dbUser) {
        if( ! user.getPassword().equals(dbUser.getPassword())) {
            return false;
        }
        return true;
    }
}