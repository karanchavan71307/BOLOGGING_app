package com.blogapplication.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blogapplication.Exceptions.ResourceNotFoundExceptions;
import com.blogapplication.model.User;
import com.blogapplication.repo.UserRepository;

@Service
public class CustomeUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = this.userRepository.findByEmail(username)
				.orElseThrow(()->new ResourceNotFoundExceptions("email", "user email", 1));	
		
		//System.out.println("user from data base "+user);
		return user;
	}

}
