package com.ctfplatform.demo.dao;

import com.ctfplatform.demo.entity.Admin;

import java.util.List;

public interface AdminDao {
    Admin isSuper(String adminUsername);
    List<Admin> queryAdmin();
    Admin queryAdminByUsername(String adminUsername);   //判断管理员是否存在通过账号
    int insertAdmin(Admin admin);
    int updateAdmin(Admin admin);
    int deleteAdmin(String adminUsername);
}
