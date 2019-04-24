package com.ctfplatform.demo.service.impl;

import com.ctfplatform.demo.config.service.RSA;
import com.ctfplatform.demo.dao.AdminDao;
import com.ctfplatform.demo.entity.Admin;
import com.ctfplatform.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.rmi.server.ExportException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;

    @Override
    public boolean isSuper(String adminUsername) {
        if(adminDao.isSuper(adminUsername).getAdminState()==3){     //超级管理员state为3
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<Admin> queryAdmin() {
        return adminDao.queryAdmin();
    }

    @Override
    public Admin queryAdminByUsername(String adminUsername) {
        return adminDao.queryAdminByUsername(adminUsername);
    }
    @Transactional
    @Override
    public Map<String,Object> insertAdmin(Admin admin,String adminUsername) {
        Map<String,Object> modelMap = new HashMap<String,Object>();
        if(isSuper(adminUsername)) {
            if (admin.getAdminUsername() != null && !"".equals(admin.getAdminUsername())) {//判断账号是否为空
                if (adminDao.queryAdminByUsername(admin.getAdminUsername()) == null) { //判断该账号是否已经存在 不存在则继续
                    String pwd = admin.getAdminPassword();
                    if (pwd != null && !"".equals(pwd)) { //判断输入的密码是否为空
                        RSA rsa = new RSA();
                        String pass = null;
                        try {
                            pass = rsa.testEncrypt(rsa.privateKey, pwd);
                            admin.setAdminPassword(pass);
                        } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException
                                | IllegalBlockSizeException | BadPaddingException | IOException e) {
                            modelMap.put("message","加密失败");
                        }
                        int effectedNum = adminDao.insertAdmin(admin);
                        if (effectedNum > 0) {
                        } else {
                            modelMap.put("message","加入失败");
                        }
                    } else {
                        modelMap.put("message","密码不为空");
                    }
                } else {
                    modelMap.put("message","账号已经存在");
                }
            } else {
                modelMap.put("message","账号不为空");
            }
        }else{
            modelMap.put("message","不是超级管理员");
        }
        return modelMap;
    }

    @Override
    public Map<String,Object> updateAdmin(Admin admin,String adminUsername) {
        Map<String,Object> modelMap = new HashMap<String,Object>();
        System.out.println(adminUsername);
        if(isSuper(adminUsername)) {
            if (admin.getAdminUsername() != null && !"".equals(admin.getAdminUsername())) {//判断账号是否为空
                String pwd = admin.getAdminPassword();
                if (pwd != null && !"".equals(pwd)) { //判断是否要改密码
                    RSA rsa = new RSA();
                    String pass = null;
                    try {
                        pass = rsa.testEncrypt(rsa.privateKey, pwd);
                        admin.setAdminPassword(pass);
                    } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException
                            | IllegalBlockSizeException | BadPaddingException | IOException e) {
                        modelMap.put("message","加密失败");
                    }
                    int effectedNum = adminDao.updateAdmin(admin);
                    if (effectedNum > 0) {
                    } else {
                        modelMap.put("message","更改失败");
                    }
                } else {
                    modelMap.put("message","密码不能空");
                }
            } else {
                modelMap.put("message","账号不为空");
            }
        }else{
            modelMap.put("message","不是超级管理员");
        }
        return modelMap;
    }

    @Override
    public Map<String,Object> deleteAdmin(String username,String adminUsername) {
        Map<String,Object> modelMap = new HashMap<String, Object>();
        if(isSuper(adminUsername)){
            int effectedNum = adminDao.deleteAdmin(username);
            if(effectedNum > 0){
            }else{
                modelMap.put("message","删除失败");
            }
        }else{
            modelMap.put("message","您不是超级管理员，无法删除管理员信息");
        }
        return modelMap;
    }

    @Override
    public Map<String,Object> adminlogin(String adminUsername, String adminPassword) {
        Map<String,Object> modelMap = new HashMap<String,Object>();
        if(adminUsername != null && adminPassword != null){
            Admin admin = null;
            admin = adminDao.queryAdminByUsername(adminUsername);
            if(admin != null) {
                if(admin.getAdminState() != 1) {
                    String pwd = admin.getAdminPassword();   //获得数据库中的密码
                    RSA rsa = new RSA();
                    String enpwd = null;
                    try {
                        enpwd = rsa.testEncrypt(rsa.privateKey, adminPassword);  //将输入的密码加密
                    } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException
                            | IllegalBlockSizeException | BadPaddingException | IOException e) {
                        modelMap.put("message", "加密失败");
                    }
                    if (pwd.equals(enpwd)) {
                    } else {
                        modelMap.put("message", "账号密码不正确");
                    }
                }else{
                    modelMap.put("message","比赛管理员不得登录后台");
                }
            }else{
                modelMap.put("message","账号密码不正确");
            }
        }else{
            modelMap.put("message","账号密码不能为空");
        }
        return modelMap;
    }

    @Override
    public Map<String,Object> updateAdminself(Admin admin, String adminUsername) {
        Map<String,Object> modelMap = new HashMap<String,Object>();
        if(admin.getAdminUsername().equals(adminUsername)){
            String pwd = admin.getAdminPassword();
            if(pwd!=null && !"".equals(pwd)) {    //判断是否要改密码
                RSA rsa = new RSA();
                String enpwd = null;
                try {
                    enpwd = rsa.testEncrypt(rsa.privateKey, pwd);
                    admin.setAdminPassword(enpwd);
                } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException
                        | IllegalBlockSizeException | BadPaddingException | IOException e) {
                    modelMap.put("message","加密失败");
                }
                int effectedNum = adminDao.updateAdmin(admin);
                if (effectedNum > 0) {
                } else {
                    modelMap.put("message","更新信息失败");
                }
            }else{
                modelMap.put("message","更新的密码不能为空");
            }
        }else{
            modelMap.put("message","只能更新自己的信息");
        }
        return modelMap;
    }
}
