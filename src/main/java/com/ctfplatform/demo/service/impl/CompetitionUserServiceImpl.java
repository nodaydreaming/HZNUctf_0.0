package com.ctfplatform.demo.service.impl;

import com.ctfplatform.demo.dao.CompetitionUserDao;
import com.ctfplatform.demo.dao.TeamCompetitorDao;
import com.ctfplatform.demo.dao.TeamDao;
import com.ctfplatform.demo.dao.UserDao;
import com.ctfplatform.demo.entity.CompetitionUser;
import com.ctfplatform.demo.entity.TeamCompetitor;
import com.ctfplatform.demo.entity.User;
import com.ctfplatform.demo.service.CompetitionUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class CompetitionUserServiceImpl implements CompetitionUserService {
    @Autowired
    private CompetitionUserDao competitionUserDao;
    @Autowired
    private TeamDao teamDao;
    @Autowired
    private TeamCompetitorDao teamCompetitorDao;
    @Autowired
    private UserDao userDao;
    @Override
    public List<CompetitionUser> listCompetitionUser() {
        return competitionUserDao.listCompetitionUser();
    }
    @Transactional
    @Override
    public Map<String,Object> insertCompetitionUser(CompetitionUser competitionUser) {
        Map<String,Object> modelMap = new HashMap<String,Object>();
        competitionUser.setEnteringTime(new Date());
        int effectedNum = competitionUserDao.insertCompetitionUser(competitionUser);
//        System.out.println(competitionUser.getEnteringTime());
        if(effectedNum > 0){
        }else{
            modelMap.put("message","参加比赛失败");
        }
        return modelMap;
    }

    @Override
    public CompetitionUser getCompeititonUserByUserIdAndCompetitionId(int userId, int competitionId){
        return competitionUserDao.getCompetitionUserByUserIdAndCompetitionId(userId, competitionId);
    }

    @Override
    public Map<String,Object> deleteCompetitionUser(CompetitionUser competitionUser) {
        Map<String,Object> modelMap = new HashMap<String,Object>();
        int effectedNum = competitionUserDao.deleteCompetitionUser(competitionUser);
        if(effectedNum > 0){
            int teamId = teamDao.queryTeamIdByUserIdAndCompetitionId(competitionUser.getCompetitorId(),competitionUser.getCompetitionId());
//            TeamCompetitor teamCompetitor = teamCompetitorDao.queryCompetitorExist(competitionUser.getCompetitorId());   //找到选手所属队伍
//            int teamId = teamCompetitor.getTeamId();    //获得所属队伍Id
            //删除之前获得该队伍所有用户
            List<TeamCompetitor> members = teamCompetitorDao.queryCompetitorIdByTeamId(teamId);
            teamCompetitorDao.deleteCompetitor(competitionUser.getCompetitorId(), teamId);    //有记录 删除该记录
            //-----判断队伍人员是否大于1
            boolean isCaptain = false;
            for(TeamCompetitor tc : members){
                if((int)tc.getCompetitorId() == competitionUser.getCompetitorId() && tc.getCompetitorLevel() == 1){
                    isCaptain = true;
                }
            }
            if(members.size() == 1){
                teamDao.deleteTeam(teamId);
            }else{
                //设置新队长
                if(isCaptain){
                    for(TeamCompetitor tc : members){
                        if((int)tc.getCompetitorId() != competitionUser.getCompetitorId() && tc.getCompetitorLevel() != 1){
                            tc.setCompetitorLevel(1);
                            teamCompetitorDao.updateTeamCompetitor(tc);
                            break;
                        }
                    }
                }
            }
        }else{
            modelMap.put("message","退出比赛失败");
        }
        return modelMap;
    }

    @Override
    public List<Integer> listUserParticipateCompetitionId(int competitorId) {
        return competitionUserDao.listUserParticipateCompetitionId(competitorId);
    }

    @Override
    public List<User> listOneCompetitionUser(int competitionId) {
            List<Integer> list = competitionUserDao.listOneCompetitionUser(competitionId);
            List<User> userList = new ArrayList<User>();
            for(int i =0;i<list.size();i++){
                userList.add(userDao.queryUserById(list.get(i)));
            }
            return  userList;
    }
}
