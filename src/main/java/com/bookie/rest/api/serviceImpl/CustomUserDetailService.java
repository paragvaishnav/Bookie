package com.bookie.rest.api.serviceImpl;

import java.util.HashSet;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.bookie.rest.api.controller.UserController;
import com.bookie.rest.api.entity.User;
import com.bookie.rest.api.repositories.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService{

	private static final Logger logger = LogManager.getLogger(CustomUserDetailService.class);
	
	@Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 User user = repository.findByEmailId(username).get();
	        if(user == null){
	            throw new UsernameNotFoundException("Invalid username or password.");
	        }
	        logger.warn("here is the auth"+getAuthority(user)+" "+user+" "+getAuthority(user));
	        return new org.springframework.security.core.userdetails.User(user.getEmailId(), user.getPassword(), getAuthority(user));
	}
	
	private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return authorities;
    }

}
