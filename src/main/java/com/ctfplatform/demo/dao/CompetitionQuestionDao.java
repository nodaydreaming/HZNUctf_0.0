package com.ctfplatform.demo.dao;

import com.ctfplatform.demo.entity.CompetitionQuestion;

import java.util.List;

public interface CompetitionQuestionDao {
    List<CompetitionQuestion> queryCompetitionquestion();
    List<Integer> listCompetitionQuestionId(int competitionId);
    int insertCompetitionQuestion(CompetitionQuestion competitionQuestion);
    int deleteCompetitionQuestion(int competitionId);
}
