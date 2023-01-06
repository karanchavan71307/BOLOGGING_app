package com.blogapplication.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogapplication.model.User;
import com.blogapplication.paylaod.UserDto;

public interface UserRepository extends JpaRepository<User, Integer> {

	 Optional<User> findByEmail(String email); 
	 
	 
	 
	
}
