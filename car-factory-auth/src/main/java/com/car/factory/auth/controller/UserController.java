package com.car.factory.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.car.factory.auth.model.User;
import com.car.factory.auth.model.UserDto;
import com.car.factory.auth.service.UserService;
import com.car.factory.util.PasswordEncoder;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/application")
@Api(value = "User Api documentation")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/createUser")
	public User createUser(@RequestBody UserDto userDto) {
		
		User user = new User();
		
		user.setEnabled(true);
		user.setPassword(PasswordEncoder.getInstance().getCryptPasswordEncoder().encode(userDto.getPassword()));
		user.setUsername(userDto.getUsername());
		user.setRole(userDto.getRole());
		
		return userService.saveUser(user);
		
	}
	
	@GetMapping("/getUserByUserName")
	public User getUserByUserName(@RequestParam String username) {
		return userService.getUserByUserName(username);
		
	}
	
	@GetMapping("/getUserById")
	public User getUserById(@RequestParam Long id) {
		
		return userService.getUserById(id);
	}
	
	@DeleteMapping("/deleteUserById")
	public void deleteUserById(@RequestParam Long id) {
		userService.deleteUserById(id);
	}

}
