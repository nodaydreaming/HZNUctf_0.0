package com.ctfplatform.demo.dao;

import com.ctfplatform.demo.entity.Team;

import java.util.List;

public interface TeamDao {
   List<Team> queryTeam();
   Team queryTeamByNameAndCompetitionId(String teamName,int competitionId);  //  判断队伍是否存在
   int insertTeam(Team team);
   int updateTeam(Team team);
   int deleteTeam(int teamId);
   int updatePoint(String teamName,float teamPoint);
   int queryTeamByUserIdAndCompetitionId(int userId,int competitionId);
   int queryTeamIdByUserIdAndCompetitionId(int userId, int competitionId);
}
