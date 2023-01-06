package com.blogapplication.serviceimpl;

import javax.management.relation.RoleResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.blogapplication.model.Role;
import com.blogapplication.repo.RoleRepo;
import com.blogapplication.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleRepo roleRepo;
	
	
	@Override
	public Role createRole(Role role) {
		
		Role save = this.roleRepo.save(role);
		
		return save;
		
	}
	

}
