package com.ctfplatform.demo.web;


import com.ctfplatform.demo.dao.CompetitionUserDao;
import com.ctfplatform.demo.dao.TeamDao;
import com.ctfplatform.demo.entity.*;
import com.ctfplatform.demo.service.CompetitionService;
import com.ctfplatform.demo.service.TeamCompetitorService;
import com.ctfplatform.demo.service.TeamService;
import com.ctfplatform.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/TeamCompetitor")
public class TeamCompetitorController {
    @Autowired
    private CompetitionService competitionService;
    @Autowired
    private TeamCompetitorService teamCompetitorService;
    @Autowired
    private UserService userService;
    @Autowired
    private TeamService teamService;
    @Autowired
    private CompetitionUserDao competitionUserDao;
    @Autowired
    private TeamDao teamDao;
    @RequestMapping(value = "listTeamCompetitor",method = RequestMethod.POST)
    private Map<String,Object> listTeamCompetitor(){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        List<TeamCompetitor> list = teamCompetitorService.queryTeamCompetitor();
        modelMap.put("listTeamCompetitor",list);
        return modelMap;
    }
    @RequestMapping(value = "queryCompetitorIdByTeamId",method = RequestMethod.POST)
    private Map<String,Object> queryTeamByTeamId(int teamId){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        List<TeamCompetitor> list = teamCompetitorService.queryCompetitorIdByTeamId(teamId);
        modelMap.put("queryCompetitorIdByTeamId",list);
        return modelMap;
    }
    @RequestMapping(value = "insertTeamCompetitor",method = RequestMethod.POST) //加入队伍接口
    private Map<String,Object> insertCompetitor(String teamName, String number, HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        String username = user.getCompetitorUsername();
        int userId = userService.queryUserByUsername(username).getCompetitorId();
        Competition competition =  competitionService.queryCompetitionByNumber(number);
        if(competition != null) {
            int canregister = competitionService.queryCompetitionByNumber(number).getCompetitionCanregister();
            if(canregister == 1) {
                if(competitionUserDao.queryCompetitionUserByCompetitionIdAndUserId(competition.getCompetitionId(),userId) != null){    //找到记录
                    modelMap.put("message","当前比赛已报名");    //判断比赛是否报名
                }else {
                    int num = teamDao.queryTeamByUserIdAndCompetitionId(userId,competition.getCompetitionId());
                    if(num > 0){
                        modelMap.put("message","您已有队伍");  //判断该场比赛下是否加入过队伍
                    }else {
                        if (teamService.queryTeamByNameAndCompetitionId(teamName, competition.getCompetitionId()).containsKey("team")) { //判断这场比赛下是否有队伍
                            Team team = (Team) (teamService.queryTeamByNameAndCompetitionId(teamName, competition.getCompetitionId()).get("team"));
                            int teamId = team.getTeamId();
                            TeamCompetitor teamCompetitor = new TeamCompetitor();
                            teamCompetitor.setCompetitorId(userId);
                            teamCompetitor.setTeamId(teamId);
                            teamCompetitor.setCompetitorLevel(0);
                            if (teamCompetitorService.insertTeamCompetitor(teamCompetitor).containsKey("message")) {
                                modelMap.put("message", teamCompetitorService.insertTeamCompetitor(teamCompetitor).get("message"));
                            }
                        } else {
                            modelMap.put("message", "队伍不存在");
                        }
                    }
                }
            }else if(canregister == 2){
                modelMap.put("message","报名已经结束");
            }else{
                modelMap.put("message","报名暂未开启");
            }
        }else{
            modelMap.put("message","根据比赛Number未找到比赛");
        }
        return modelMap;
    }

    @RequestMapping(value = "deleteTeam",method = RequestMethod.GET)
    private Map<String,Object> deleteTeam(int teamId){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        modelMap.put("message",teamCompetitorService.deleteTeam(teamId).get("message"));
        return modelMap;
    }
    @RequestMapping(value = "queryTeamnameByCompetitorId",method = RequestMethod.POST)
    private Map<String,Object> queryTeamname(HttpServletRequest request){
        HttpSession session = request.getSession();
        Map<String,Object> modelMap = new HashMap<String,Object>();
        User user = (User)session.getAttribute("user");
        if(user != null) {
            String username = user.getCompetitorUsername();
            int userId = userService.queryUserByUsername(username).getCompetitorId();
            Map<String, Object> map = new HashMap<String, Object>();
            map = teamCompetitorService.queryTeamnameByCompetitorId(userId);
            if (map.containsKey("Teamname")) {
                modelMap.put("Teamname", map.get("Teamname"));
            } else {
                modelMap.put("message", map.get("message"));
            }
        }else{
            modelMap.put("message","获取失败");
        }
        return modelMap;
    }
}
