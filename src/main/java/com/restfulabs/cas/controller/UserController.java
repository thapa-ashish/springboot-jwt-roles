package com.restfulabs.cas.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restfulabs.cas.domain.User;
import com.restfulabs.cas.service.IUserService;

@RestController
@RequestMapping("/api/v1")
public class UserController {

	private IUserService userService;

	public UserController(IUserService userService) {
		this.userService = userService;
	}

	@RequestMapping("/signup")
	public User signup(@RequestParam("user") User user) {
		return userService.saveUser(user);
	}
}
