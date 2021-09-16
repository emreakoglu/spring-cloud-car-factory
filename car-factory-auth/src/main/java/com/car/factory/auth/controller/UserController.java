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
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.SwaggerDefinition;

@RestController
@RequestMapping("/car-factory-auth/application")
@Api(value = "User Api documentation")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/createUser")
	@ApiOperation(value = "Kullanıcı Oluşturma Operasyonu")
	public User createUser(@RequestBody @ApiParam("Kullanıcı Oluşturma Servisi Dto") UserDto userDto) {
		
		User user = new User();
		
		user.setEnabled(true);
		user.setPassword(PasswordEncoder.getInstance().getCryptPasswordEncoder().encode(userDto.getPassword()));
		user.setUsername(userDto.getUsername());
		user.setRole(userDto.getRole());
		
		return userService.saveUser(user);
		
	}
	
	@GetMapping("/getUserByUserName")
	@ApiOperation(value = "Username'den User Getirme Operasyonu")
	public User getUserByUserName(@RequestParam String username) {
		return userService.getUserByUserName(username);
		
	}
	
	@GetMapping("/getUserById")
	@ApiOperation(value = "UserId'den User Getirme Operasyonu")
	public User getUserById(@RequestParam Long id) {
		
		return userService.getUserById(id);
	}
	
	@DeleteMapping("/deleteUserById")
	@ApiOperation(value = "UserId'den User Silme Operasyonu")
	public void deleteUserById(@RequestParam Long id) {
		userService.deleteUserById(id);
	}

}