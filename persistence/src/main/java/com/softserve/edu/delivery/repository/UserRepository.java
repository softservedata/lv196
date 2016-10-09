package com.softserve.edu.delivery.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.softserve.edu.delivery.domain.User;

public interface UserRepository extends BaseRepository<User, String>, JpaRepository<User, String> {
	
	
}