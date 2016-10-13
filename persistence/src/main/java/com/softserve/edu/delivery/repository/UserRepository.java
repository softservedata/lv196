package com.softserve.edu.delivery.repository;

import java.util.List;

import com.softserve.edu.delivery.domain.Role;
import com.softserve.edu.delivery.domain.User;

public interface UserRepository extends BaseRepository<User, String> {
	
	List<User> findByBlockedOrderByLastNameAsc(Boolean value);
	
	List<User> findByFirstNameStartsWithOrderByLastNameAsc(String value);
	
	List<User> findByLastNameStartsWithOrderByLastNameAsc(String value);
	
	List<User> findByEmailStartsWithOrderByLastNameAsc(String value);

	List<User> findByUserRoleOrderByLastNameAsc(Role value);
	
}