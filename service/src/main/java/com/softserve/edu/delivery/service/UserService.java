package com.softserve.edu.delivery.service;

import com.softserve.edu.delivery.domain.User;
import com.softserve.edu.delivery.dto.DriverRegistrationDTO;
import com.softserve.edu.delivery.dto.UserProfileDto;
import com.softserve.edu.delivery.dto.UserRegistrationDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public interface UserService extends UserDetailsService {

    boolean exists(String email);

    @Override
    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;

    void register(UserRegistrationDTO userRegDTO, String url);

	void register(DriverRegistrationDTO driverRegDTO, String url);

    void verifyRegistration(String token);
    
    UserProfileDto changeUserStatus(String mail, boolean blocked);
	
	List<UserProfileDto> changeUsersStatus(Map<String, Boolean> map);
	
	UserProfileDto getUser(String email);
	
	Long countItems();
	
	List<UserProfileDto> findUsers(UserProfileDto filter);

    User findOne(String email);

    void save(User user);
    
    List<Long> countUsersByRole();
    
    List<Long> countUsersByRate();
    
    List<UserProfileDto> findTopFiveDriversByRate();

}
