package com.ctfplatform.demo.service.impl;

import com.ctfplatform.demo.dao.CompetitionAdminDao;
import com.ctfplatform.demo.entity.CompetitionAdmin;
import com.ctfplatform.demo.service.CompetitionAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class CompetitionAdminServiceImpl implements CompetitionAdminService {
    @Autowired
    private CompetitionAdminDao competitionAdminDao;
    @Override
    public List<CompetitionAdmin> list() {
        return competitionAdminDao.listCompetitionAdmin();
    }

    @Override
    public List<Integer> listCompetitionAdminId(int competitionId) {
        return competitionAdminDao.listCompetitionAdminId(competitionId);
    }

    @Transactional
    @Override
    public Map<String, Object> insert(CompetitionAdmin competitionAdmin) {
        Map<String,Object> modelMap = new HashMap<String,Object>();
        int effectedNum = competitionAdminDao.insert(competitionAdmin);
        if(effectedNum > 0){
        }else{
            modelMap.put("message","添加失败");
        }
        return modelMap;
    }

    @Override
    public Map<String, Object> delete(CompetitionAdmin competitionAdmin) {
        Map<String,Object> modelMap = new HashMap<String,Object>();
        int effectedNum = competitionAdminDao.delete(competitionAdmin);
        if(effectedNum > 0){
        }else{
            modelMap.put("message","删除失败");
        }
        return modelMap;
    }
}
