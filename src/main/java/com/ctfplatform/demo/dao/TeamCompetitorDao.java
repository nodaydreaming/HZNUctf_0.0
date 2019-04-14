package com.ctfplatform.demo.dao;

import com.ctfplatform.demo.entity.TeamCompetitor;

import java.util.List;

public interface TeamCompetitorDao {
    List<TeamCompetitor> queryTeamCompetitor();
    TeamCompetitor queryCompetitorExist(int competitorId);    //判断这个成员是否已经在队伍之中 或者其他队伍之中
    List<TeamCompetitor> queryCompetitorIdByTeamId(int teamId);
    int insertTeamCompetitor(TeamCompetitor teamCompetitor);
    int deleteCompetitor(int competitorId, int teamId);
    int deleteTeam(int teamId);
    String queryTeamnameByCompetitorId(int competitorId);
    int updateTeamCompetitor(TeamCompetitor teamCompetitor);
}
