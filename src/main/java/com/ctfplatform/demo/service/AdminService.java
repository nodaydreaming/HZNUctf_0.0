package com.ctfplatform.demo.service;

import com.ctfplatform.demo.entity.Admin;

import java.util.List;
import java.util.Map;

public interface AdminService {
    boolean isSuper(String adminUsername);
    List<Admin> queryAdmin();
    Admin queryAdminByUsername(String adminUsername);   //判断管理员是否存在通过账号
    Map<String,Object> insertAdmin(Admin admin, String adminUsername);
    Map<String,Object> updateAdmin(Admin admin,String adminUsername);
    Map<String,Object> deleteAdmin(String username,String adminUsername);
    Map<String,Object> adminlogin(String adminUsername,String adminPassword);
    Map<String,Object> updateAdminself(Admin admin,String adminUsername);
}
