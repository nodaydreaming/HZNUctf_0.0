package com.ctfplatform.demo.web;

import com.ctfplatform.demo.entity.User;
import com.ctfplatform.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.management.ValueExp;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value="/listuser",method = RequestMethod.GET)
    private Map<String,Object> listUser(int page, int limit){
        List<User> list = userService.queryUser();
        List<User> sortUsers = new LinkedList<User>();
        for(int i = 0; i < list.size(); ++i){
            User u = list.get(i);
            u.setCompetitorId(i+1);
            u.setCompetitorPassword("");
            sortUsers.add(u);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 0);
        map.put("msg","");
        map.put("count", sortUsers.size());

        int startNum = (page - 1) * limit;
        int endNum = page * limit;
        List<User> resultList = new LinkedList<User>();
        for(int i = startNum; i < sortUsers.size() && i < endNum; ++i){
            resultList.add(sortUsers.get(i));
        }
        map.put("data", resultList);
        return map;
    }
    @RequestMapping(value="/getuserbyUsername",method = RequestMethod.POST)
    private Map<String,Object> getUserByUsername(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        String userName = user.getCompetitorUsername();
        User returnUser = userService.queryUserByUsername(userName);
        String enpwd = returnUser.getCompetitorPassword();
        returnUser.setCompetitorPassword(null);
        modelMap.put("getUserByUsername",returnUser);
        return modelMap;
    }
    @RequestMapping(value="/adduser",method = RequestMethod.POST)
    private Map<String, Object> addUser(User user){
        String[] academyList={"杭州国际服务工程学院","理学院","文化创意学院","外国语学院",
                "政治与社会学院","经济与管理学院","沈钧儒法学院","教育学院","医学院",
                "材料与化学化工学院","阿里巴巴商学院","经亨颐学院","生命与环境科学学院","体育与健康学院","美术学院","人文学院"};

        Map<String,Object> modelMap = new HashMap<String,Object>();
        String reg = "^[a-z0-9]+$";
        String newreg = "^[0-9]+$";
        int flag = 1;
        if(user.getCompetitorUsername() == null || "".equals(user.getCompetitorUsername()) ||user.getCompetitorUsername().length()<3 || user.getCompetitorUsername().length()>20 || !Pattern.matches(reg,user.getCompetitorUsername())){
            modelMap.put("message","用户名必须是3-20个小写字母或数字");
            flag=0;
        }else if(user.getCompetitorPassword()==null || "".equals(user.getCompetitorPassword()) || user.getCompetitorPassword().length()<5 || user.getCompetitorPassword().length()>15 || !Pattern.matches(reg,user.getCompetitorPassword())){
            modelMap.put("message","密码必须是5-15个小写字母或数字");
            flag=0;
        }else if(user.getCompetitorGender() == null){
            modelMap.put("message","请选择性别！");
            flag=0;
        }else if(user.getCompetitorNickname()==null || "".equals(user.getCompetitorNickname()) || user.getCompetitorNickname().length()<1 || user.getCompetitorNickname().length()>15){
            modelMap.put("message","昵称必须是1-15个字符");
            flag=0;
        }else if(user.getCompetitorTel()==null || "".equals(user.getCompetitorTel()) || user.getCompetitorTel().length()!=11 || !Pattern.matches(newreg,user.getCompetitorTel())){
            modelMap.put("message","手机号码必须是11位长号");
            flag=0;
        }else if(user.getCompetitorStudentnumber()==null ||"".equals(user.getCompetitorStudentnumber())||user.getCompetitorStudentnumber().length()!=13 || !Pattern.matches(newreg,user.getCompetitorStudentnumber())){
            modelMap.put("message","学号必须是13位数字");
            flag=0;
        }else if(user.getCompetitorAcademy()==null || "".equals(user.getCompetitorAcademy()) || user.getCompetitorAcademy().length()>13){
            modelMap.put("message","学院格式不合法");
            flag=0;
        }else if(user.getCompetitorAcademy()!=null&&!"".equals(user.getCompetitorAcademy())&&user.getCompetitorAcademy().length()<=13){
            int newflag=0;
            for(int i=0;i<academyList.length;i++){
                if(academyList[i].equals(user.getCompetitorAcademy())){
                    newflag=1;             //有一个匹配
                    break;
                }
            }
            if(newflag == 0){
                modelMap.put("message","请重新选择学院");
                flag=0;
            }
        } else if (user.getCompetitorClass() == null || "".equals(user.getCompetitorClass()) || user.getCompetitorClass().length() > 13) {
            modelMap.put("message","班级格式不合法");
            flag=0;
        }else if(user.getCompetitorName()==null||"".equals(user.getCompetitorName())||user.getCompetitorName().length()<2||user.getCompetitorName().length()>6){
            modelMap.put("message","名字格式不合法");
            flag=0;
        }
        if(flag == 1) {
            modelMap.put("message", userService.register(user).get("message"));
        }
        return modelMap;
    }
    @RequestMapping(value = "/updateuser",method = RequestMethod.POST)
    private Map<String, Object> updateUser(User user,String newPassword,HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        User temporaryUser = (User)session.getAttribute("user");
        String userName = temporaryUser.getCompetitorUsername();   //获得session中的账号
        user.setCompetitorUsername(userName);        //使得账号为session中的账号
        String reg = "^[a-z0-9]+$";
        int flag = 1;
        if(user.getCompetitorPassword()==null||"".equals(user.getCompetitorPassword())){
            modelMap.put("message","密码不能为空");
            flag=0;
        }else if(user.getCompetitorPassword().length()<5 || user.getCompetitorPassword().length()>15||!Pattern.matches(reg,user.getCompetitorPassword())){
            modelMap.put("message","密码必须是5-15个小写字母或数字");
            flag=0;
        }
        if(newPassword == null || "".equals(newPassword)){
        }else if(newPassword.length()<5 || newPassword.length()>15 ||!Pattern.matches(reg,newPassword)){
            modelMap.put("message","新密码必须是5-15个小写字母或数字");
            flag=0;
        }
        if(user.getCompetitorEmail()==null||"".equals(user.getCompetitorEmail())){
        }else if(user.getCompetitorEmail().length()>20){
            modelMap.put("message","邮箱字符过长");
            flag=0;
        }
        if(user.getCompetitorNickname()==null||"".equals(user.getCompetitorNickname())){
            modelMap.put("message","昵称不能为空");
            flag=0;
        }else if(user.getCompetitorNickname().length()<1 || user.getCompetitorNickname().length()>15){
            modelMap.put("message","昵称必须是1-15个字符");
            flag=0;
        }

        if(flag==1) {
            modelMap.put("message", userService.updateUser(user, newPassword).get("message"));
        }
        return modelMap;
    }
    @RequestMapping(value = "/deleteuser",method = RequestMethod.POST)
    private Map<String, Object> deleteUser(String competitorUsername){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        modelMap.put("success", userService.deleteUser(competitorUsername));
        return modelMap;
    }
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    private  Map<String, Object> loginUser(String username, String password, HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        int flag=1;
        String reg = "^[a-z0-9]+$";
        if(username==null||"".equals(username)||!Pattern.matches(reg,username)||username.length()<3||username.length()>20){
            modelMap.put("message","用户名格式不合法");
            flag=0;
        }else if(password==null||"".equals(password)||!Pattern.matches(reg,password)||password.length()<3||password.length()>15){
            modelMap.put("message","密码格式不合法");
            flag=0;
        }
        if(flag==1) {
            if (userService.userlogin(username, password).get("message") == null) {
                User user = new User();
                user.setCompetitorUsername(username);
                user.setCompetitorId(userService.queryUserByUsername(username).getCompetitorId());
                user.setCompetitorNickname(userService.queryUserByUsername(username).getCompetitorNickname());
                user.setCompetitorAcademy(userService.queryUserByUsername(username).getCompetitorAcademy());
                user.setCompetitorClass(userService.queryUserByUsername(username).getCompetitorClass());
                user.setCompetitorEmail(userService.queryUserByUsername(username).getCompetitorEmail());
                session.setAttribute("user", user);
                modelMap.put("success", true);
            } else {
                Map<String, Object> promap = new HashMap<String, Object>();
                modelMap.put("message", userService.userlogin(username, password).get("message"));
            }
        }
        return modelMap;
    }
    @RequestMapping(value = "getUserMap",method = RequestMethod.POST)
    private Map<String,Object> getUserMap(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        HttpSession session = request.getSession();
        User loginUser = (User)session.getAttribute("user");
        modelMap.put("loginUser",loginUser);
        return modelMap;
    }
    @RequestMapping(value = "quxiao",method = RequestMethod.POST)
    private Map<String,Object> quxiao(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        try {
            HttpSession session = request.getSession();
            session.removeAttribute("user");
        }catch(Exception e){
            modelMap.put("message","失败");
        }
        return modelMap;
    }
}
