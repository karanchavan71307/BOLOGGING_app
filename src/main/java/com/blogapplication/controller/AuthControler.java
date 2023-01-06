package com.blogapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.blogapplication.Exceptions.ResourceNotFoundExceptions;
import com.blogapplication.jwt.JwtAuthResponse;
import com.blogapplication.jwt.JwtTokenHelper;
import com.blogapplication.paylaod.JwtAuthRequest;
import com.blogapplication.paylaod.UserDto;
import com.blogapplication.service.UserServiceI;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthControler {

	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	@Autowired
	private UserServiceI userServiceI;
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request){
		
	  this.autheticate(request.getEmail(),request.getPassword());
		
	  UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getEmail());
	  	  
	  String token = this.jwtTokenHelper.generateToken(userDetails);
	  
	  JwtAuthResponse response=new JwtAuthResponse();
	  
	   response.setToken(token);
	   return new ResponseEntity<JwtAuthResponse>(response,HttpStatus.OK);
		
	}


	private void autheticate(String email, String password) {
		// TODO Auto-generated method stub
		
	 UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(email, password);
		
		
			try {
				authenticationManager.authenticate(usernamePasswordAuthenticationToken);
			} catch (BadCredentialsException e) {
				
				throw new ResourceNotFoundExceptions("User name or password worng !! try again !! ");
				
			}		
	}
	
	// register the new user 
	
	@PostMapping("/register")
	public ResponseEntity<UserDto> registerTheNewUser(@RequestBody UserDto userDto){
		
		UserDto registerdUser = this.userServiceI.resitrationsFrom(userDto);
		
		return new ResponseEntity<UserDto>(registerdUser,HttpStatus.CREATED);
	}
	
}
