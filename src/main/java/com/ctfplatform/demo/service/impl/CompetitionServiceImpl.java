package com.ctfplatform.demo.service.impl;

import com.ctfplatform.demo.dao.CompetitionDao;
import com.ctfplatform.demo.entity.Competition;
import com.ctfplatform.demo.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CompetitionServiceImpl implements CompetitionService {
    @Autowired
    private CompetitionDao competitionDao;

    @Override
    public List<Competition> queryCompetition() {
        return competitionDao.queryCompetition();
    }

    @Override
    public Competition queryCompetitionByTitle(String competitionTitle) {
        return competitionDao.queryCompetitionByTitle(competitionTitle);
    }

    @Override
    public Competition queryCompetitionByNumber(String number) {
        return competitionDao.queryCompetitionByNumber(number);
    }

    @Transactional
    @Override
    public Map<String,Object> insert(Competition competition) {
        competition.setCompetitionCreatetime(new Date());
        Map<String,Object> modelMap = new HashMap<String,Object>();
        Competition competition1 = competitionDao.queryCompetitionByNumber(competition.getCompetitionNumber());
        if(competition1 == null){
            int effectedNum = competitionDao.insert(competition);
            if(effectedNum > 0){
            }else{
                modelMap.put("message","创建比赛");
            }
        }else{
            modelMap.put("message","number重复");
        }
        return modelMap;
    }

    @Override
    public Map<String,Object> update(Competition competition) {
        Map<String,Object> modelMap = new HashMap<String,Object>();
        int effectedNum = competitionDao.update(competition);
        if(effectedNum > 0){
        }else{
            modelMap.put("message","修改比赛失败");
        }
        return modelMap;
    }

    @Override
    public Map<String,Object> delete(int competitionId) {
        Map<String,Object> modelMap = new HashMap<String,Object>();
        int effectedNum = competitionDao.delete(competitionId);
        if(effectedNum > 0){
        }else{
            modelMap.put("message","删除比赛失败");
        }
        return modelMap;
    }
}
