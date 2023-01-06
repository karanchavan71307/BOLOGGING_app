package com.blogapplication.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.blogapplication.Exceptions.ResourceNotFoundExceptions;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtTokenHelper jwtTokenHelper;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// get token

		String requestToken = request.getHeader("Authorization");

		// Bearer 234e3423

		System.out.println(requestToken);

		String userName = null;

		String token = null;

		if (requestToken != null && requestToken.startsWith("Bearer ")) {

			token = requestToken.substring(7); // wi th out bearer

			try {
				 userName = this.jwtTokenHelper.extractUsername(token);

			} catch (IllegalArgumentException | ExpiredJwtException | MalformedJwtException e) {

				System.out.println("unable to get  jwt token !!");
			}

		} else {

			System.out.println("jwt token does not begin with bearer !!");
		}

		// once we get the token now wth validate

		if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);

			if (this.jwtTokenHelper.validateToken(token, userDetails)) {

				// sahi chal raha hai
				// authentications set karna hai

				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());

				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

			} else {
				System.out.println("invalid jwt token !!");
			}

		} else {

			//throw new ResourceNotFoundExceptions("unable to finds somethinfgs went wrong !!");
			System.out.println("User name or context is Null !!");
		}

		filterChain.doFilter(request, response);
	}

}
