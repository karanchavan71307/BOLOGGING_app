package com.blogapplication.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.blogapplication.jwt.JwtAuthenticationFilter;
import com.blogapplication.jwt.JwtAuthenticationsEntryPoint;
import com.blogapplication.paylaod.ConstantKeyword;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfigurationsDemo extends WebSecurityConfigurerAdapter{

	
	@Autowired
	private CustomeUserDetailsService customeUserDetailsService;
	
	
	@Autowired
	private JwtAuthenticationsEntryPoint jwtAuthenticationsEntryPoint;
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
					
	@Override
	protected void configure(HttpSecurity http) throws Exception {


	     http.csrf().disable()
	    .authorizeHttpRequests()
	    .antMatchers(ConstantKeyword.PUBLIC_URLS).permitAll()
	    .antMatchers(HttpMethod.GET).permitAll()
	    .anyRequest()
	    .authenticated()
	    .and()
	    .exceptionHandling().authenticationEntryPoint(this.jwtAuthenticationsEntryPoint)
	    .and()
	    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
				
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(this.customeUserDetailsService).passwordEncoder(this.passwordEncoder());
		
	}

	// bCryptPasswordEncoder 
	@Bean
	public PasswordEncoder passwordEncoder() {			
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {		
		
		return super.authenticationManagerBean();
	}
	
	
	
	
}
