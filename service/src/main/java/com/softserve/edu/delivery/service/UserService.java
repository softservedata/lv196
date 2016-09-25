package com.softserve.edu.delivery.service;

import java.util.List;
import java.util.Map;

import com.softserve.edu.delivery.domain.User;
import com.softserve.edu.delivery.dto.UserAuthDTO;
import com.softserve.edu.delivery.dto.UserProfileDto;
import com.softserve.edu.delivery.dto.UserProfileFilterDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface UserService {
    boolean exists(String email);
    void register(User user);
    UserProfileDto verificationLogin(UserAuthDTO user);
    
    List<UserProfileDto> getAllUsers(int page, int size, UserProfileFilterDto filter);
	
    UserProfileDto changeUserStatus(String mail, boolean blocked)throws IllegalStateException;
	
	List<UserProfileDto> changeUsersStatus(Map<String, Boolean> map);

}
