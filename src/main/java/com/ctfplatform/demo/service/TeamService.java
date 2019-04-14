package com.ctfplatform.demo.service;

import com.ctfplatform.demo.entity.Team;

import java.util.List;
import java.util.Map;

public interface TeamService {
    List<Team> queryTeam();
    Map<String,Object> queryTeamByNameAndCompetitionId(String teamName,int competitionId);
    Map<String,Object> insertTeam(Team team, String userName);
    Map<String,Object> updateTeam(Team team);
    Map<String,Object> deleteTeam(int teamId);
    Map<String,Object> updatePoint(String teamName,float teamPoint);
}
