package com.blogapplication.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogapplication.paylaod.ApiResponse;
import com.blogapplication.paylaod.UserDto;
import com.blogapplication.serviceimpl.UserServiceImpl;

@RestController
@RequestMapping("/create/")
public class UserController {

	@Autowired
	private UserServiceImpl userServiceImpl;

	/// save the user

	@PostMapping("/users")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {

		System.out.println(userDto);

		UserDto userDto2 = this.userServiceImpl.createUser(userDto);

		return new ResponseEntity<>(userDto2, HttpStatus.CREATED);
	}

	// get all user
	@GetMapping("/users")
	public ResponseEntity<List<UserDto>> getAllUser() {

		
			List<UserDto> list = this.userServiceImpl.getAllUser();
			return new ResponseEntity<List<UserDto>>(list, HttpStatus.OK);
		
	}

//	get user by id

	@GetMapping("/users/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable("id") int id) {

		UserDto dto = this.userServiceImpl.getUserByid(id);

		return new ResponseEntity<UserDto>(dto, HttpStatus.OK);
	}

	// update user

	@PutMapping("/users/{id}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("id") int id) {

		UserDto dto = this.userServiceImpl.updateUser(userDto, id);

		return new ResponseEntity<UserDto>(dto, HttpStatus.OK);

	}
	// delete user
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") int id) {

		try {
			this.userServiceImpl.deleteUser(id);
			return ResponseEntity.status(HttpStatus.OK).body("user deleted successfully!  with this id  !! " + id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse("user can't find with  thise id " + id, false));
		}
	}
}
