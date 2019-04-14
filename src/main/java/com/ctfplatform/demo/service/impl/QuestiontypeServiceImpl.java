package com.ctfplatform.demo.service.impl;

import com.ctfplatform.demo.dao.QuestiontypeDao;
import com.ctfplatform.demo.entity.Questiontype;
import com.ctfplatform.demo.service.QuestiontypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QuestiontypeServiceImpl implements QuestiontypeService {
    @Autowired
    private QuestiontypeDao questiontypeDao;

    @Override
    public List<Questiontype> queryQuestiontype() {
        return questiontypeDao.queryQuestiontype();
    }
    @Transactional
    @Override
    public Map<String,Object> insertQuestiontype(Questiontype questiontype) {
        Map<String,Object> modelMap = new HashMap<String,Object>();
        int effectedNum = questiontypeDao.insertQuestiontype(questiontype);
        if(effectedNum > 0){
        }else{
            modelMap.put("message","加入失败");
        }
        return modelMap;
    }

    @Override
    public Map<String,Object> updateQuestiontype(Questiontype questiontype) {
        Map<String,Object> modelMap = new HashMap<String,Object>();
        int effectedNum = questiontypeDao.updateQuestiontype(questiontype);
        if(effectedNum > 0){
        }else{
            modelMap.put("message","更新失败");
        }
        return modelMap;
    }

    @Override
    public Map<String,Object> deleteQuestiontype(int questiontypeId) {
        Map<String,Object> modelMap = new HashMap<String,Object>();
        int effectedNum = questiontypeDao.deleteQuestiontype(questiontypeId);
        if(effectedNum > 0){
        }else{
            modelMap.put("message","删除失败");
        }
        return modelMap;
    }
}
