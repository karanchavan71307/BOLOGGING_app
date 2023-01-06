package com.blogapplication.serviceimpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blogapplication.Exceptions.ResourceNotFoundExceptions;
import com.blogapplication.model.Role;
import com.blogapplication.model.User;
import com.blogapplication.paylaod.ConstantKeyword;
import com.blogapplication.paylaod.UserDto;
import com.blogapplication.repo.UserRepository;
import com.blogapplication.service.UserServiceI;

@Service
public class UserServiceImpl implements UserServiceI {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	//save user 
	@Override
	public UserDto createUser(UserDto userdto) {

		User user = this.DtoToUser(userdto);
		

		User user2 = this.userRepository.save(user);

		return this.userToDto(user2);

	}

	@Override
	public List<UserDto> getAllUser() {

		List<User> list = this.userRepository.findAll();

		List<UserDto> collect = list.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());

		boolean b = collect.isEmpty();
		System.out.println(b);

		if (b) {

			throw new NullPointerException("list is blank !!");

		} else {
			return collect;
		}
	}

	@Override
	public UserDto getUserByid(int id) {

		User user = this.userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundExceptions("user", " id ", id));

		return this.userToDto(user);
	}

	@Override
	public UserDto updateUser(UserDto userDto, int id) {

		User user = this.userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundExceptions("user", "id", id));

		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());

		User user2 = this.userRepository.save(user);

		return this.userToDto(user2);

	}

	@Override
	public void deleteUser(int id) {

		User user = this.userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundExceptions("user", " id ", id));
		this.userRepository.delete(user);
		System.out.println("delete user succesfully !!");

	}

	// mapper class
//	Dto to user
	public User DtoToUser(UserDto userDto) {

		User user = this.modelMapper.map(userDto, User.class);

//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());

		return user;
	}

//	user to dto

	public UserDto userToDto(User user) {

		UserDto dto = this.modelMapper.map(user, UserDto.class);

		return dto;
	}

	// Register  the new user 
	
	@Override
	public UserDto resitrationsFrom(UserDto userDto) {
		
		User user = this.modelMapper.map(userDto, User.class);
		
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		
		Role role=new Role();
		role.setId(ConstantKeyword.NORMAL_USER);
		role.setName("ROLE_NORMAL");
			
		Set<Role> roles=new HashSet<>();
		roles.add(role);
		
		user.setRole(roles);
		
		
		User updateUser = this.userRepository.save(user);
			
		return this.modelMapper.map(updateUser, UserDto.class);
	}

}
