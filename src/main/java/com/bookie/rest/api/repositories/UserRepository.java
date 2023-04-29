package com.bookie.rest.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookie.rest.api.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmailId(String emailId);
    Boolean existsByEmailId(String emailId);
    User findByEmailIdAndPassword(String emailId, String password);
    
	
}
