package com.restfulabs.cas.controller;

import com.restfulabs.cas.domain.User;
import com.restfulabs.cas.service.IUserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SignupController {

    private IUserService userService;

    public SignupController(IUserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/signup")
    public User signup(@RequestBody User user) {
        return userService.saveUser(user);
    }
}
