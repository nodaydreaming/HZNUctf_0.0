package com.ctfplatform.demo.service;


import com.ctfplatform.demo.entity.TeamCompetitor;

import java.util.List;
import java.util.Map;

public interface TeamCompetitorService {
    List<TeamCompetitor> queryTeamCompetitor();
    List<TeamCompetitor> queryCompetitorIdByTeamId(int teamId);
    Map<String,Object> insertTeamCompetitor(TeamCompetitor teamCompetitor);
    Map<String,Object> deleteCompetitor(int competitorId, int teamId);
    Map<String,Object> deleteTeam(int teamId);
    Map<String,Object> queryTeamnameByCompetitorId(int competitorId);
}
