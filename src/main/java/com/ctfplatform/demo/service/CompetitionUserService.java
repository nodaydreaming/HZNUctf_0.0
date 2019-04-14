package com.ctfplatform.demo.service;

import com.ctfplatform.demo.entity.Competition;
import com.ctfplatform.demo.entity.CompetitionUser;
import com.ctfplatform.demo.entity.User;

import java.util.List;
import java.util.Map;

public interface CompetitionUserService {
    List<CompetitionUser> listCompetitionUser();
    CompetitionUser getCompeititonUserByUserIdAndCompetitionId(int userId, int competitionId);
    Map<String,Object> insertCompetitionUser(CompetitionUser competitionUser);
    Map<String,Object> deleteCompetitionUser(CompetitionUser competitionUser);
    List<Integer> listUserParticipateCompetitionId(int competitorId);
    List<User> listOneCompetitionUser(int competitionId);
}
