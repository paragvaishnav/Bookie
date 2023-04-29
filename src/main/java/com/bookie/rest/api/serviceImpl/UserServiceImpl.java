package com.bookie.rest.api.serviceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.bookie.rest.api.entity.EmailDetails;
import com.bookie.rest.api.entity.Role;
import com.bookie.rest.api.entity.User;
import com.bookie.rest.api.repositories.UserRepository;
import com.bookie.rest.api.service.UserService;
import com.bookie.rest.api.utility.UserAlreadyExistsException;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	public UserRepository userRepository;
	
	@Autowired
	public JavaMailSender mailer;
	
	@Value("${spring.mail.username}")
	public String sender;
	
	private Role role;
	
	@Autowired
	public RoleServiceImpl roleService;
	
	@Override
	public void saveUser(User user) {
		Set<Role>roles = new HashSet<>();
		if(user.getEmailId().split("@")[1].equals("admin.edu")){
            role = roleService.findByName("ADMIN");
            roles.add(role);
        } else {
        	role = roleService.findByName("USER");
        	roles.add(role);
        }
        user.setRoles(roles);
		this.userRepository.save(user);
	}

	@Override
	public User getUserById(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isUserExists(String emailId) {
		return this.userRepository.existsByEmailId(emailId);
	}

	@Override
	public void sendEmail(EmailDetails details) {
		
		try {
			SimpleMailMessage msg = new SimpleMailMessage();
			msg.setFrom(sender);
			msg.setTo(details.getRecipient());
			msg.setText(details.getMsgBody());
			msg.setSubject(details.getSubject());
		} catch(Exception ex) {
			
		}
	}

	@Override
	public boolean validateUser(String username, String password) {
		return this.userRepository.findByEmailIdAndPassword(username, password)!=null;
		
	}

	@Override
	public User getUserbyId(String emailId) {
		return this.userRepository.findByEmailId(emailId).get();
	}

}
