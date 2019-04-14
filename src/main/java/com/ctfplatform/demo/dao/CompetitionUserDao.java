package com.ctfplatform.demo.dao;

import com.ctfplatform.demo.entity.CompetitionUser;
import com.ctfplatform.demo.entity.User;

import java.util.List;

public interface CompetitionUserDao {
    List<CompetitionUser> listCompetitionUser();
    int insertCompetitionUser(CompetitionUser competitionUser);
    int deleteCompetitionUser(CompetitionUser competitionUser);
    CompetitionUser getCompetitionUserByUserIdAndCompetitionId(int userId, int competitionId);
    List<Integer> listUserParticipateCompetitionId(int userId);
    List<Integer> listOneCompetitionUser(int competitionId);
    CompetitionUser queryCompetitionUserByCompetitionIdAndUserId(int competitionId,int userId);
}
