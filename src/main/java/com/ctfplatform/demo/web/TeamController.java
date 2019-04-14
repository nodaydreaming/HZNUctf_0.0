package com.ctfplatform.demo.web;

import com.ctfplatform.demo.entity.Competition;
import com.ctfplatform.demo.entity.Team;
import com.ctfplatform.demo.entity.User;
import com.ctfplatform.demo.service.CompetitionService;
import com.ctfplatform.demo.service.TeamService;
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
@RequestMapping("/team")
public class TeamController {
    @Autowired
    private TeamService teamService;
    @Autowired
    private CompetitionService competitionService;
    @RequestMapping(value = "listteam",method = RequestMethod.POST)
    private Map<String,Object> listteam(){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        List<Team> list = teamService.queryTeam();
        modelMap.put("listteam",list);
        return modelMap;
    }
    @RequestMapping(value = "queryteambyname",method = RequestMethod.POST)
    private Map<String,Object> queryTeamByName(String teamName,int competitionId){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        Team team = (Team)teamService.queryTeamByNameAndCompetitionId(teamName,competitionId).get("team");
        modelMap.put("getTeam",team);
        return modelMap;
        }
    @RequestMapping(value = "insertTeam",method = RequestMethod.POST)  //创建队伍接口
    private Map<String,Object> insertteam(Team team, String number, HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        Competition competition =  competitionService.queryCompetitionByNumber(number);
        if(competition != null) {
            int canregister = competition.getCompetitionCanregister();
            if(canregister == 1) {
                int competitionId = competition.getCompetitionId();
                team.setTeamCompetitionId(competitionId);
                String username = user.getCompetitorUsername();
                modelMap.put("message", teamService.insertTeam(team, username).get("message"));
            }else if(canregister == 2){
                modelMap.put("message","报名已经结束");
            }
            else{
                modelMap.put("message","报名暂未开启");
            }
        }else{
            modelMap.put("message","根据number未找到该比赛");
        }
        return modelMap;
    }
    @RequestMapping(value = "updateTeam",method = RequestMethod.POST)
    private Map<String,Object> updateteam(Team team){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        modelMap.put("message",teamService.updateTeam(team).get("message"));
        return modelMap;
    }
    @RequestMapping(value = "deleteTeam",method = RequestMethod.POST)
    private Map<String,Object> deleteteam(int teamId){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        modelMap.put("message",teamService.deleteTeam(teamId).get("message"));
        return modelMap;
    }
    @RequestMapping(value = "updatePoint",method = RequestMethod.POST)
    private Map<String,Object> updatePoint(String teamName,float Point){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        modelMap.put("message",teamService.updatePoint(teamName,Point).get("message"));
        return modelMap;
    }
}
