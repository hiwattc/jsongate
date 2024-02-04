package com.staroot.collector.mapper;

import com.staroot.collector.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

// UserMapper.java
@Mapper
public interface UserMapper {
    List<User> getAllUsers();
    List<Map> getAllUsers2();
}
