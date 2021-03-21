package com.car.factory.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car.factory.auth.model.User;
import com.car.factory.auth.respository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public User saveUser(User user) {
		return userRepository.saveAndFlush(user);
	}
	
	public User getUserByUserName (String username) {
		return userRepository.getUserByUsername(username);
	}
	
	public User getUserById (Long id) {
		return userRepository.findById(id).get();
	}
	
	public void deleteUserById (Long id) {
		userRepository.deleteById(id);
	}
	
	
}
