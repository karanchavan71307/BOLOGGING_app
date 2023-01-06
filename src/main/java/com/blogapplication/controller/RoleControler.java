package com.blogapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogapplication.model.Role;
import com.blogapplication.service.RoleService;

@RestController
@RequestMapping("/user")
public class RoleControler {

	@Autowired
	private RoleService roleService;
	
	//create role
	
	@PostMapping("/role")
	public ResponseEntity<Role> createRole(@RequestBody Role role){
		
		
	  Role createRole = this.roleService.createRole(role);
	  
	  return new ResponseEntity<Role>(createRole,HttpStatus.CREATED);
		
	}
	
	
}
