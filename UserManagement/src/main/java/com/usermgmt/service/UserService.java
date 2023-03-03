package com.usermgmt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usermgmt.model.User;
import com.usermgmt.repository.UserRepository;

@Service
/**
 * @author PES1UG19CS490 Sneha P
 */
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public boolean registerUser(User user) {
		
		User duplicateUser = userRepository.findByEmail(user.getEmail());
		
		if (duplicateUser != null) {
			return false;
		} else {
			userRepository.save(user);
			return true;	
		}
		
	}
}
