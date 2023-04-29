package com.bookie.rest.api.controller;

import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookie.rest.api.entity.AuthRequest;
import com.bookie.rest.api.entity.AuthenticationResponse;
import com.bookie.rest.api.entity.Profile;
import com.bookie.rest.api.entity.User;
import com.bookie.rest.api.service.JwtService;
import com.bookie.rest.api.serviceImpl.CustomUserDetailService;
import com.bookie.rest.api.serviceImpl.JwtUtils;
import com.bookie.rest.api.serviceImpl.UserServiceImpl;
import com.bookie.rest.api.utility.ApiResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RestController
@RequestMapping("api/user/")
@CrossOrigin("*")
public class UserController {
	private static final Logger logger = LogManager.getLogger(UserController.class);

	@Autowired
	public UserServiceImpl userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomUserDetailService userDetailService;

	@Autowired
	public BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtUtils jwt;

	@PostMapping("authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authRequest) throws Exception {
		Authentication authentication = null;
		try {
		 authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		} catch (Exception be) {
			throw new Exception("Incorrect Username or Password",be);
		}
		final String jwtToken = jwt.generateToken(authentication);
		return ResponseEntity.ok(new AuthenticationResponse(jwtToken));
	}
	
	@PostMapping("save")
	public ResponseEntity<?> saveUser(@Valid @RequestBody User user) {
		if (user != null && user.getPassword()!=null) {
			if(this.userService.isUserExists(user.getEmailId())) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(ApiResponse.getMessage(user.getUserId(), "User Already Exists", 400));
			}
			Profile profile = new Profile();
			user.setProfile(profile);
			profile.setUser(user);
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			this.userService.saveUser(user);
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(ApiResponse.getMessage(user.getUserId(), "User Created Successfully", 201));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(ApiResponse.getMessage(null, "All values are required", 400));
		}
	}
	
	
}
