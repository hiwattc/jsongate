package com.staroot.collector.controller;

import com.staroot.collector.domain.User;
import com.staroot.collector.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

// UserController.java
@RestController
@RequestMapping("/mybatis")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("users1")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    @GetMapping("users2")
    public List<Map> getAllUsers2() {
        return userService.getAllUsers2();
    }
}
