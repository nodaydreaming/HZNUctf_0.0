package com.ctfplatform.demo.service.impl;

import com.ctfplatform.demo.dao.TeamCompetitorDao;
import com.ctfplatform.demo.entity.TeamCompetitor;
import com.ctfplatform.demo.service.TeamCompetitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TeamCompetitorServiceImpl implements TeamCompetitorService {
    @Autowired
    private TeamCompetitorDao teamCompetitorDao;

    @Override
    public List<TeamCompetitor> queryTeamCompetitor() {
        return teamCompetitorDao.queryTeamCompetitor();
    }

    @Override
    public List<TeamCompetitor> queryCompetitorIdByTeamId(int teamId) {
        return teamCompetitorDao.queryCompetitorIdByTeamId(teamId);
    }
    @Transactional
    @Override
    public Map<String,Object> insertTeamCompetitor(TeamCompetitor teamCompetitor) {
        Map<String,Object> modelMap = new HashMap<String,Object>();
        List<TeamCompetitor> competitorNum = teamCompetitorDao.queryCompetitorIdByTeamId(teamCompetitor.getTeamId());
        if (competitorNum != null) {
            if (competitorNum.size() < 3) {
                int effectedNum = teamCompetitorDao.insertTeamCompetitor(teamCompetitor);
                if (effectedNum > 0) {
                } else {
                    modelMap.put("message", "队伍添加成员失败");
                }
            } else {
                modelMap.put("message", "队伍人数已满");
            }
        }
        else{
            modelMap.put("message", "队伍不存在");
        }
        return modelMap;
    }

    @Override
    public Map<String,Object> deleteCompetitor(int competitorId, int teamId) {
        Map<String,Object> modelMap = new HashMap<String,Object>();
        int effectedNum = teamCompetitorDao.deleteCompetitor(competitorId, teamId);
        if(effectedNum > 0){

        }else{
            modelMap.put("message","退出队伍失败");
        }
        return modelMap;
    }

    @Override
    public Map<String,Object> deleteTeam(int teamId) {
        Map<String,Object> modelMap = new HashMap<String,Object>();
        int effectedNum = teamCompetitorDao.deleteTeam(teamId);
        if(effectedNum > 0){
        }else{
            modelMap.put("message","删除队伍失败");
        }
        return modelMap;
    }

    @Override
    public Map<String,Object> queryTeamnameByCompetitorId(int competitorId) {
        Map<String,Object> modelMap = new HashMap<String,Object>();
        String teamname = teamCompetitorDao.queryTeamnameByCompetitorId(competitorId);
        if(teamname != null){
            modelMap.put("Teamname",teamname);
        }else{
            modelMap.put("message","获得队名失败");
        }
        return modelMap;
    }
}
