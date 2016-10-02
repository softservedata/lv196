package com.softserve.edu.delivery.service;

import java.util.List;
import java.util.Map;

import com.softserve.edu.delivery.dto.UserAuthDTO;
import com.softserve.edu.delivery.dto.UserProfileDto;
import com.softserve.edu.delivery.dto.UserProfileFilterDto;
import com.softserve.edu.delivery.dto.UserRegistrationDTO;
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

    void register(UserRegistrationDTO userRegDTO);

    UserProfileDto verificationLogin(UserAuthDTO user);
    
    List<UserProfileDto> getAllUsers(int page, int size, UserProfileFilterDto filter);
	
    UserProfileDto changeUserStatus(String mail, boolean blocked)throws IllegalStateException;
	
	List<UserProfileDto> changeUsersStatus(Map<String, Boolean> map);

}
