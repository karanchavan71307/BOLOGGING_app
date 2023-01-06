package com.blogapplication.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogapplication.model.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {

	
	
}
