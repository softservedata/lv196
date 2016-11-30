package com.softserve.edu.delivery.service;

import com.softserve.edu.delivery.domain.User;
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

    boolean tokenExists(String token);

    void verifyRegistration(String token);
    
    UserProfileDto changeUserStatus(String mail, boolean blocked);
	
	List<UserProfileDto> changeUsersStatus(Map<String, Boolean> map);
	
	UserProfileDto getUser(String email);
	
	Long countItems(UserProfileDto filter);
	
	List<UserProfileDto> findUsers(UserProfileDto filter);

    User findOne(String email);

    void save(User user);

    void save(UserProfileDto userProfileDto);
    
    List<Long> countUsersByRole();
    
    List<Long> countUsersByRate();
    
    List<UserProfileDto> findTopFiveDriversByRate();

    boolean checkPassword(String email, CharSequence password);

    void changePassword(String email, CharSequence newPassword);

    void changeUserRole(UserProfileDto user);

}
