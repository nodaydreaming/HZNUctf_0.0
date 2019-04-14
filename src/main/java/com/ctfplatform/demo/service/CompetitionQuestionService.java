package com.ctfplatform.demo.service;

import com.ctfplatform.demo.entity.CompetitionQuestion;

import java.util.List;
import java.util.Map;

public interface CompetitionQuestionService {
    List<CompetitionQuestion> queryCompetitionquestion();
    List<Integer> listCompetitionQuestionId(int competitionId);
    Map<String,Object> insertCompetitionQuestion(CompetitionQuestion competitionQuestion);
    Map<String,Object> deleteCompetitionQuestion(int competitionId);
}
