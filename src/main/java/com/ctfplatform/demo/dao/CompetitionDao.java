package com.ctfplatform.demo.dao;

import com.ctfplatform.demo.entity.Competition;

import java.util.List;

public interface CompetitionDao {
    List<Competition> queryCompetition();
    Competition queryCompetitionByTitle(String competitionTitle);   //通过比赛名称判断比赛是否存在
    int insert(Competition competition);
    int update(Competition competition);
    int delete(int competitionId);
    Competition queryCompetitionByNumber(String number);
}
