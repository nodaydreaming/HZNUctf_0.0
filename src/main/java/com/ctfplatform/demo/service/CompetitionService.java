package com.ctfplatform.demo.service;

import com.ctfplatform.demo.entity.Competition;

import java.util.List;
import java.util.Map;

public interface CompetitionService {
    List<Competition> queryCompetition();
    Competition queryCompetitionByTitle(String competitionTitle);   //通过比赛名称判断比赛是否存在
    Competition queryCompetitionByNumber(String number);
    Map<String,Object> insert(Competition competition);
    Map<String,Object> update(Competition competition);
    Map<String,Object> delete(int competitionId);
}
