package com.softserve.edu.delivery.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.softserve.edu.delivery.domain.Role;
import com.softserve.edu.delivery.domain.User;

public interface UserRepository extends BaseRepository<User, String> {
	
    Long countByUserRole(Role userRole);
    
    List<User> findTop5ByUserRoleOrderByRateDesc(Role userRole);
    
    @Query("select count(u) from User u group by u.rate")
    List<Long> countByRate();
}