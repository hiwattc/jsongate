package com.staroot.collector.service;

import com.staroot.collector.domain.User;
import com.staroot.collector.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

// UserService.java
@Service
public class UserService {
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }
    public List<Map> getAllUsers2() {
        return userMapper.getAllUsers2();
    }
}
