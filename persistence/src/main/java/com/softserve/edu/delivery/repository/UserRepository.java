package com.softserve.edu.delivery.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.softserve.edu.delivery.domain.Role;
import com.softserve.edu.delivery.domain.User;

public interface UserRepository extends BaseRepository<User, String> {
	
    @Query( "select u from User u " +
            "where (:firstName is null or u.firstName like concat('%',:firstName,'%'))" +
    		"and (:lastName is null or u.lastName like concat('%',:lastName,'%'))" +
    		"and (:email is null or u.email like concat('%',:email,'%'))" +
    		"and (:userRole is null or u.userRole =:userRole)" +
    		"and (:blocked is null or u.blocked =:blocked)")
    Page<User> findUsers(@Param("firstName") String firstName, @Param("lastName")String lastName,
    					 @Param("email")String email, @Param("userRole") Role userRole, 
    					 @Param("blocked") Boolean blocked, Pageable pageable);
	
    Long countByUserRole(Role userRole);
    
    List<User> findTop5ByUserRoleOrderByRateDesc(Role userRole);
    
    @Query("select count(u) from User u group by u.rate")
    List<Long> countByRate();
}