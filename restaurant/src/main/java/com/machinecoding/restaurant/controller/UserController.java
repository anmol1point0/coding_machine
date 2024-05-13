package com.machinecoding.restaurant.controller;

import com.machinecoding.restaurant.model.User;
import com.machinecoding.restaurant.request.UserLoginRequest;
import com.machinecoding.restaurant.request.UserRegistrationRequest;
import com.machinecoding.restaurant.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("register")
    public ResponseEntity<User> registerUser(UserRegistrationRequest userRegistrationRequest) {
        return ResponseEntity.ok(userService.registerUser(userRegistrationRequest));
    }

    @PostMapping("login")
    public ResponseEntity<Boolean> login(UserLoginRequest userLoginRequest) {
        return ResponseEntity.ok(userService.login(userLoginRequest));
    }
}
