package com.softserve.edu.delivery.service;

import java.util.List;
import java.util.Map;

import com.softserve.edu.delivery.domain.User;
import com.softserve.edu.delivery.dto.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface UserService extends UserDetailsService {

    boolean exists(String email);

    @Override
    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;

    void register(UserRegistrationDTO userRegDTO, String url);

	void register(DriverRegistrationDTO driverRegDTO, String url);

    void verifyRegistration(String token);
    
    List<UserProfileDto> getAllUsers(int page, int size, UserProfileFilterDto filter);
	
    UserProfileDto changeUserStatus(String mail, boolean blocked);
	
	List<UserProfileDto> changeUsersStatus(Map<String, Boolean> map);
	
	List<UserProfileDto> getAllUsers();
	
	UserProfileDto getUser(String email);
	
	List<UserProfileDto> findUsersByBanStatus(Boolean status);
	
	List<UserProfileDto> findUsersByEmail(String value);
	
	List<UserProfileDto> findUsersByFirstName(String value);
	
	List<UserProfileDto> findUsersByLastName(String value);
	
	List<UserProfileDto> findUsersByRole(String value);

}
