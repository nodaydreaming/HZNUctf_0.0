package com.ctfplatform.demo.dao;

import com.ctfplatform.demo.entity.User;

import java.util.List;

public interface UserDao {
    List<User> queryUser();
    User queryUserByUsername(String competitoruUsername);
    User queryUserByTelorNumber(String tel,String number);
    int insertUser(User user);
    int updateUser(User user);
    int deleteUser(String competitorUsername);
    User queryPasswordByUsername(String competitorUsername);
    User queryUserById(int userId);
}
