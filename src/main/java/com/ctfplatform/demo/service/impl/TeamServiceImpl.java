package com.ctfplatform.demo.service.impl;

import com.ctfplatform.demo.dao.CompetitionUserDao;
import com.ctfplatform.demo.dao.TeamCompetitorDao;
import com.ctfplatform.demo.dao.TeamDao;
import com.ctfplatform.demo.dao.UserDao;
import com.ctfplatform.demo.entity.CompetitionUser;
import com.ctfplatform.demo.entity.Team;
import com.ctfplatform.demo.entity.TeamCompetitor;
import com.ctfplatform.demo.service.CompetitionUserService;
import com.ctfplatform.demo.service.TeamCompetitorService;
import com.ctfplatform.demo.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    private TeamDao teamDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private TeamCompetitorDao teamCompetitorDao;
    @Autowired
    private CompetitionUserDao competitionUserDao;
    @Autowired
    private TeamCompetitorService teamCompetitorService;
    @Override
    public List<Team> queryTeam() {
        return teamDao.queryTeam();
    }

    @Override
    public Map<String,Object> queryTeamByNameAndCompetitionId(String teamName,int competitionId) {
        Map<String,Object> modelMap = new HashMap<String,Object>();
        if(teamDao.queryTeamByNameAndCompetitionId(teamName,competitionId)!=null){
            modelMap.put("team",teamDao.queryTeamByNameAndCompetitionId(teamName,competitionId));
        }else{
            modelMap.put("message","队伍不存在");
        }
        return modelMap;
    }

    @Transactional
    @Override
    public Map<String,Object> insertTeam(Team team, String userName) {
        Map<String,Object> modelMap = new HashMap<String,Object>();
        TeamCompetitor teamCompetitor = new TeamCompetitor();
        int userId = userDao.queryPasswordByUsername(userName).getCompetitorId();
        if(competitionUserDao.queryCompetitionUserByCompetitionIdAndUserId(team.getTeamCompetitionId(),userId) != null){    //找到记录
            modelMap.put("message","当前比赛已报名");    //判断比赛是否报名
        }else{
            int num = teamDao.queryTeamByUserIdAndCompetitionId(userId,team.getTeamCompetitionId());
            if(num > 0){
                modelMap.put("message","您已有队伍");  //判断该场比赛下是否加入过队伍
            }else {
                if (teamDao.queryTeamByNameAndCompetitionId(team.getTeamName(),team.getTeamCompetitionId()) != null) {
                    modelMap.put("message", "队伍名字重复");    //判断该场比赛下队伍名是否重复
                } else {
                    team.setTeamPoint(0);
                    int effectedNum = teamDao.insertTeam(team);
                    if (effectedNum > 0) {
                        int teamId = teamDao.queryTeamByNameAndCompetitionId(team.getTeamName(),team.getTeamCompetitionId()).getTeamId();    //当添加队伍成功后，同步更新teamcompetitor表中的队员和队伍信息
                        teamCompetitor.setTeamId(teamId);
                        teamCompetitor.setCompetitorLevel(1);
                        teamCompetitor.setCompetitorId(userDao.queryPasswordByUsername(userName).getCompetitorId());
                        if (teamCompetitorService.insertTeamCompetitor(teamCompetitor).get("message") == null) {
                        } else {
                            modelMap.put("message", "创建队伍后添加队长信息失败");
                        }
                    } else {
                        modelMap.put("message", "创建队伍失败");
                    }
                }
            }
        }

        return modelMap;
    }

    @Override
    public Map<String,Object> updateTeam(Team team) {
        Map<String,Object> modelMap = new HashMap<String,Object>();
        int effectedNum = teamDao.updateTeam(team);
        if(effectedNum > 0){
        }else{
            modelMap.put("message","更新队伍失败");
        }
        return modelMap;
    }

    @Override
    public Map<String,Object> deleteTeam(int teamId) {
        Map<String,Object> modelMap = new HashMap<String,Object>();
        int effectedNum = teamDao.deleteTeam(teamId);
        if(effectedNum > 0){
        }else{
            modelMap.put("message","删除队伍失败");
        }
        return modelMap;
    }

    @Override
    public Map<String,Object> updatePoint(String teamName,float teamPoint){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        int effectedNum = teamDao.updatePoint(teamName,teamPoint);
        if(effectedNum > 0){
        }else{
            modelMap.put("message","更新分数失败");
        }
        return modelMap;
    }

}
