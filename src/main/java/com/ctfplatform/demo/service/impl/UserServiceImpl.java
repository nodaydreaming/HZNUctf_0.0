package com.ctfplatform.demo.service.impl;

import com.ctfplatform.demo.config.service.RSA;
import com.ctfplatform.demo.dao.UserDao;
import com.ctfplatform.demo.entity.User;
import com.ctfplatform.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public List<User> queryUser() {
        return userDao.queryUser();
    }

    @Override
    public User queryUserByUsername(String competitorUsername) {
        return userDao.queryUserByUsername(competitorUsername);
    }
    public User queryUserById(int userId){
        return userDao.queryUserById(userId);
    }

    @Transactional
    @Override
    public Map<String,Object> register(User user) {
        Map<String,Object> modelMap = new HashMap<String,Object>();
        if(userDao.queryPasswordByUsername(user.getCompetitorUsername()) == null ){    //判断该账号数据库中是否存在
            if(userDao.queryUserByTelorNumber(user.getCompetitorTel(),user.getCompetitorStudentnumber()) == null) {
                if (user.getCompetitorUsername() != null && !"".equals(user.getCompetitorUsername())) {   //判断用户输入的账号是否为空
                    String pwd = user.getCompetitorPassword();
                    if (pwd != null && !"".equals(pwd)) {
                        RSA rsa = new RSA();
                        String pass = null;
                        try {
                            pass = rsa.testEncrypt(rsa.privateKey, pwd);
                            user.setCompetitorPassword(pass);
                        } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException
                                | IllegalBlockSizeException | BadPaddingException | IOException e) {
                            modelMap.put("message", "RSA加密失败");
                        }
                        int effectedNum = userDao.insertUser(user);
                        if (effectedNum > 0) {
                        } else {
                            modelMap.put("message", "注册信息失败");
                        }
                    } else {
                        modelMap.put("message", "密码不空");
                    }
                } else {
                    modelMap.put("message", "账号不空");
                }
            }else{
                modelMap.put("message","手机号或学号重复");
            }
        }else{
            modelMap.put("message","账号已存在");
        }
        return modelMap;
    }
    @Override
    public Map<String,Object> updateUser(User user,String newPassword) {
        Map<String,Object> modelMap = new HashMap<String,Object>();
        if (user.getCompetitorUsername() != null){
            String pwd = user.getCompetitorPassword(); //获取当前旧密码明文
            if(newPassword != null && !"".equals(newPassword)){    //输入了旧密码和新密码
                System.out.println("1");
                RSA rsa = new RSA();
                String enpwd = null;         //加密用户输入的密码
                try {
                    enpwd = rsa.testEncrypt(rsa.privateKey, pwd);

                    String inPassword = userDao.queryPasswordByUsername(user.getCompetitorUsername()).getCompetitorPassword();
                    if(enpwd.equals(inPassword)){    //如果加密后的旧密码与数据库中密码一样
                        String enNewPassword = rsa.testEncrypt(rsa.privateKey,newPassword);
                        user.setCompetitorPassword(enNewPassword);  //把新密码传入
                        int effectedNum = userDao.updateUser(user);
                        if(effectedNum > 0){
                        }else {
                            modelMap.put("message","更新用户失败");
                        }
                    }else{
                        modelMap.put("message","旧密码输入错误");
                    }
                } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException
                        | IllegalBlockSizeException | BadPaddingException | IOException e) {
                    modelMap.put("message","加密失败");
                }
            }else{     //更新的时候不要更新密码
                RSA rsa = new RSA();
                String enpwd = null;
                try {
                    enpwd = rsa.testEncrypt(rsa.privateKey, pwd);
                    String inPassword = userDao.queryPasswordByUsername(user.getCompetitorUsername()).getCompetitorPassword();
                    if(enpwd.equals(inPassword)){    //如果加密后的旧密码与数据库中密码一样
                        user.setCompetitorPassword(enpwd);  //把原密码传入
                        int effectedNum = userDao.updateUser(user);
                        if(effectedNum > 0){
                        }else {
                            modelMap.put("message","更新用户失败");
                        }
                    }else{
                        modelMap.put("message","旧密码输入错误");
                    }
                } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException
                        | IllegalBlockSizeException | BadPaddingException | IOException e) {
                    modelMap.put("message","加密失败");
                }
            }
        }else{
            modelMap.put("message","用户信息不能为空");
        }
        return modelMap;
    }

    @Override
    public Map<String,Object> deleteUser(String competitorUsername) {
        Map<String,Object> modelMap = new HashMap<String,Object>();
        if(competitorUsername != null && !"".equals(competitorUsername)){
            int effectedNum = userDao.deleteUser(competitorUsername);
            if(effectedNum > 0){
            }else{
                modelMap.put("message","删除用户信息失败");
            }
        }else{
            modelMap.put("message","用户Id不能为空");
        }
        return modelMap;
    }

    @Override
    public Map<String,Object> userlogin(String competitorUsername,String competitorPassword) {
        Map<String,Object> modelMap = new HashMap<String,Object>();
        if(competitorUsername != null && competitorPassword != null){
            User user = userDao.queryPasswordByUsername(competitorUsername);
            if(user != null){
                String pwd = user.getCompetitorPassword();
                RSA rsa = new RSA();
                String enpwd = null;         //加密用户输入的密码
                try {
                    enpwd = rsa.testEncrypt(rsa.privateKey, competitorPassword);
                } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException
                        | IllegalBlockSizeException | BadPaddingException | IOException e) {
                    modelMap.put("message","加密失败");
                }
                if(pwd.equals(enpwd)){      //比对两者
                }else{
                    modelMap.put("message","账号或密码不正确");
                }
            }else{
                modelMap.put("message","账号或密码不正确");
            }
        }else{
            modelMap.put("message","账号或密码不正确");
        }
        return modelMap;
    }

    @Override
    public String decrypt(String password) {
        RSA rsa = new RSA();
        String depwd=null;
        try {
            depwd = rsa.testDecrypt(rsa.publicKey,password);
        } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException
                | IllegalBlockSizeException | BadPaddingException | IOException e) {
            throw new RuntimeException("RSA解密失败" + e.getMessage());
        }
        return depwd;
    }
}
