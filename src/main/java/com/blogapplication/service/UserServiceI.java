package com.blogapplication.service;

import java.util.List;

import com.blogapplication.paylaod.UserDto;

public interface UserServiceI {

	
	public UserDto createUser(UserDto userdto);
	
	public List<UserDto> getAllUser();
	
	public UserDto getUserByid(int id);
	
	public UserDto updateUser(UserDto userDto,int id);
	
	public void deleteUser(int id);
	
	public UserDto resitrationsFrom(UserDto userDto);
}
