package com.ctfplatform.demo.web;


import com.ctfplatform.demo.entity.Admin;
import com.ctfplatform.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "isSuper",method = RequestMethod.POST)
    private Map<String,Object> isSuper(String adminUsername){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        modelMap.put("success",adminService.isSuper(adminUsername));
        return modelMap;
    }
    @RequestMapping(value="listadmin",method = RequestMethod.POST)
    private Map<String,Object> listAdmin(){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        List<Admin> list = adminService.queryAdmin();
        modelMap.put("adminlist",list);
        return modelMap;
    }
    @RequestMapping(value="queryAdminByUsername",method = RequestMethod.POST)
    private Map<String,Object> queryAdminByUsername(String adminUsername){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        Admin admin = adminService.queryAdminByUsername(adminUsername);
        modelMap.put("getAdminByUsername",admin);
        return modelMap;
    }
    @RequestMapping(value = "insertadmin",method = RequestMethod.POST)
    private Map<String,Object> insertAdmin(Admin admin,HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        HttpSession session = request.getSession();
        Admin loginAdmin = (Admin)session.getAttribute("admin");
        String nowadminUsername = loginAdmin.getAdminUsername();
        modelMap.put("message",adminService.insertAdmin(admin,nowadminUsername).get("message"));
        return modelMap;
    }
    @RequestMapping(value = "updateadmin",method = RequestMethod.POST)
    private Map<String,Object> updateadmin(Admin admin,HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        HttpSession session = request.getSession();
        Admin loginAdmin = (Admin)session.getAttribute("admin");
        String nowadminUsername = loginAdmin.getAdminUsername();
        modelMap.put("message",adminService.updateAdmin(admin,nowadminUsername).get("message"));
        return modelMap;
    }
    @RequestMapping(value = "deleteadmin",method = RequestMethod.POST)
    private Map<String,Object> deleteadmin(String username,HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        HttpSession session = request.getSession();
        Admin loginAdmin = (Admin)session.getAttribute("admin");
        String nowadminUsername = loginAdmin.getAdminUsername();
        modelMap.put("message",adminService.deleteAdmin(username,nowadminUsername).get("message"));
        return modelMap;
    }
    @RequestMapping(value = "adminlogin",method = RequestMethod.POST)
    private Map<String,Object> adminlogin(String adminUsername, String adminPassword, HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        HttpSession session = request.getSession();
        if(adminService.adminlogin(adminUsername,adminPassword).get("message") == null){
            Admin admin = new Admin();
            admin.setAdminUsername(adminUsername);
            admin.setAdminState(adminService.queryAdminByUsername(adminUsername).getAdminState());
            admin.setAdminEmail(adminService.queryAdminByUsername(adminUsername).getAdminEmail());
            admin.setAdminGender(adminService.queryAdminByUsername(adminUsername).getAdminGender());
            admin.setAdminId(adminService.queryAdminByUsername(adminUsername).getAdminId());
            admin.setAdminNickname(adminService.queryAdminByUsername(adminUsername).getAdminNickname());
            admin.setAdminPhoto(adminService.queryAdminByUsername(adminUsername).getAdminPhoto());
            admin.setAdminState(adminService.queryAdminByUsername(adminUsername).getAdminState());
            session.setAttribute("admin",admin);     //添加管理员map
//            System.out.println(session.getAttribute("admin"));
            modelMap.put("success",true);
            modelMap.put("newPage","ctf_admin/main.html");
        }else{
            Map<String,Object> promap = new HashMap<String,Object>();
            modelMap.put("message",adminService.adminlogin(adminUsername,adminPassword).get("message"));
        }
        return modelMap;
    }
    @RequestMapping(value = "adminUpdateself",method = RequestMethod.POST)
    private Map<String,Object> adminUpdateself(Admin admin,HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        HttpSession session = request.getSession();
        Admin loginAdmin = (Admin)session.getAttribute("admin");
        String nowadminUsername = loginAdmin.getAdminUsername();
        modelMap.put("message",adminService.updateAdminself(admin,nowadminUsername).get("message"));
        return modelMap;
    }
    @RequestMapping(value = "getAdminMap",method = RequestMethod.POST)
    private Map<String,Object> getUserMap(HttpServletRequest request){
        HttpSession session = request.getSession();
        Admin loginAdmin = (Admin)session.getAttribute("admin");
        Map<String,Object> modelMap = new HashMap<String,Object>();
        modelMap.put("loginAdmin",loginAdmin);
        return modelMap;
    }
    @RequestMapping(value = "quitAdmin", method = RequestMethod.POST)
    private Map<String, Object> quitAdmin(HttpServletRequest request){
        HttpSession session = request.getSession();
        Map<String,Object> modelMap = new HashMap<String,Object>();

        session.removeAttribute("admin");
        if(session.getAttribute("admin") != null){
            modelMap.put("message", "退出失败！");
        }
        return modelMap;
    }
}
