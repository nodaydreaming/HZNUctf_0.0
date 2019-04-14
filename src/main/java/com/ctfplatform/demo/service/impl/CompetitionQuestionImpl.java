package com.ctfplatform.demo.service.impl;

import com.ctfplatform.demo.dao.CompetitionQuestionDao;
import com.ctfplatform.demo.entity.CompetitionQuestion;
import com.ctfplatform.demo.service.CompetitionQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CompetitionQuestionImpl implements CompetitionQuestionService {
    @Autowired
    private CompetitionQuestionDao competitionQuestionDao;
    @Override
    public List<CompetitionQuestion> queryCompetitionquestion() {
        return competitionQuestionDao.queryCompetitionquestion();
    }

    @Override
    public List<Integer> listCompetitionQuestionId(int competitionId) {
        return competitionQuestionDao.listCompetitionQuestionId(competitionId);
    }

    @Transactional
    @Override
    public Map<String,Object> insertCompetitionQuestion(CompetitionQuestion competitionQuestion) {
        Map<String,Object> modelMap = new HashMap<String,Object>();
        int effectedNum = competitionQuestionDao.insertCompetitionQuestion(competitionQuestion);
        if(effectedNum > 0){
        }else{
            modelMap.put("message","增加比赛赛题失败");
        }
        return modelMap;
    }

    @Override
    public Map<String,Object> deleteCompetitionQuestion(int competitionId) {
        Map<String,Object> modelMap = new HashMap<String,Object>();
        int effectedNum = competitionQuestionDao.deleteCompetitionQuestion(competitionId);
        if(effectedNum > 0){
        }else{
            modelMap.put("message","删除比赛赛题失败");
        }
        return modelMap;
    }
}
