package com.ctfplatform.demo.web;

import com.ctfplatform.demo.entity.Competition;
import com.ctfplatform.demo.entity.CompetitionUser;
import com.ctfplatform.demo.entity.User;
import com.ctfplatform.demo.service.CompetitionService;
import com.ctfplatform.demo.service.CompetitionUserService;
import com.ctfplatform.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/CompetitionUser")
public class CompetitionUserController {
    @Autowired
    private CompetitionUserService competitionUserService;
    @Autowired
    private UserService userService;
    @Autowired
    private CompetitionService competitionService;
    @RequestMapping(value = "/listCompetitionUser",method = RequestMethod.POST)
    private Map<String,Object> listCompetitionUser(){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        List<CompetitionUser> list = competitionUserService.listCompetitionUser();
        modelMap.put("listCompetitionUser",list);
        return modelMap;
    }
    @RequestMapping(value = "/listOneCompetition",method = RequestMethod.POST)
    private Map<String,Object> listOneCompetition(int competitionId){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        modelMap.put("OneCompetitionUser",competitionUserService.listOneCompetitionUser(competitionId));
        return modelMap;
    }
    @RequestMapping(value = "/addCompetitionUser",method = RequestMethod.POST)
    private Map<String,Object> addCompetitionUser(String number,HttpServletRequest request){
        CompetitionUser competitionUser = new CompetitionUser();
        Map<String,Object> modelMap = new HashMap<String,Object>();
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        String username = user.getCompetitorUsername();
        User newuser = userService.queryUserByUsername(username);
        Competition competition = competitionService.queryCompetitionByNumber(number);
        if(competition != null) {
            int competitionId = competition.getCompetitionId();
            competitionUser.setCompetitionId(competitionId);    //通过number获取ID
            competitionUser.setCompetitorId(newuser.getCompetitorId());    //通过session获取id
            modelMap.put("message", competitionUserService.insertCompetitionUser(competitionUser).get("message"));
        }else{
            modelMap.put("message","根据number未找到该队伍");
        }
        return modelMap;
    }
    @RequestMapping(value = "/deleteCompetitionUser",method = RequestMethod.POST)
    private Map<String,Object> deleteCompetitionUser(String number,HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        Competition competition = competitionService.queryCompetitionByNumber(number);
        if(competition != null){
            CompetitionUser competitionUser = competitionUserService.getCompeititonUserByUserIdAndCompetitionId(user.getCompetitorId(), competition.getCompetitionId());
            modelMap.put("message",competitionUserService.deleteCompetitionUser(competitionUser).get("message"));
        }else{
            modelMap.put("message","通过比赛Number为找到比赛");
        }
        return modelMap;

    }
    @RequestMapping(value = "/getCompetitionIsParticipate",method = RequestMethod.POST)
    private Map<String,Object> getCompetitionIsParticipate(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        String userName = user.getCompetitorUsername();
        int userId = userService.queryUserByUsername(userName).getCompetitorId();
        List<Integer> competitionIdList = competitionUserService.listUserParticipateCompetitionId(userId);  //准备返还给前端选手参与的比赛Id列表
        modelMap.put("ParticipateCompetitionId",competitionIdList);
        return modelMap;
    }
}
