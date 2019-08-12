package com.restfulabs.cas.controller;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restfulabs.cas.domain.User;
import com.restfulabs.cas.service.IUserService;

@RestController
public class UserController {

	private IUserService userService;

	public UserController(IUserService userService) {
		this.userService = userService;
	}

	public User signup(@RequestParam("user") User user) {
		return userService.saveUser(user);
	}
}
