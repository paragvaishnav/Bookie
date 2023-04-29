package com.bookie.rest.api.service;

import java.util.List;

import com.bookie.rest.api.entity.EmailDetails;
import com.bookie.rest.api.entity.User;

public interface UserService {

	public void saveUser(User user);
	
	public User getUserById(Long userId);
	
	public boolean isUserExists(String emailId);
	
	public List<User> getAllUser();
	
	public void sendEmail(EmailDetails details);
	
	public boolean validateUser(String username,String password);
	
	public User getUserbyId(String emailId);
}
