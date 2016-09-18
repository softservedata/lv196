package com.softserve.edu.delivery.service;

import java.util.List;
import java.util.Map;

import com.softserve.edu.delivery.domain.User;
import com.softserve.edu.delivery.dto.UserAuthDTO;
import com.softserve.edu.delivery.dto.UserProfileDto;
import com.softserve.edu.delivery.dto.UserProfileFilterDto;

public interface UserService {
    boolean exists(String email);
    void register(User user);
    boolean verificationLogin(UserAuthDTO user);
    
	List<UserProfileDto> getAllUsers(int from, int count, UserProfileFilterDto filter);
	
	UserProfileDto changeUserStatus(String mail, boolean blocked);
	
	List<UserProfileDto> changeUsersStatus(Map<String, Boolean> map);

}
