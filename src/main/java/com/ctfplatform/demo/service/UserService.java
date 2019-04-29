package com.ctfplatform.demo.service;

import com.ctfplatform.demo.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<User> queryUser();
    User queryUserByUsername(String competitorUsername);
    User queryUserById(int userId);
    Map<String,Object> register(User user);
    Map<String,Object> updateUser(User user,String newPassword);
    Map<String,Object> deleteUser(String competitorUsername);
    Map<String,Object> userlogin(String competitorUsername, String competitorPassword);
    String decrypt(String password);
    Map<String, Object> adminResetUserPwd(User user);
}
