package com.ctfplatform.demo.service.impl;

import com.ctfplatform.demo.dao.QuestionDao;
import com.ctfplatform.demo.entity.Question;
import com.ctfplatform.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionDao questionDao;

    @Override
    public List<Question> queryQuestion() {
        return questionDao.queryQuestion();
    }
    @Transactional
    @Override
    public Map<String,Object> insertQuestion(Question question) {
        Map<String,Object> modelMap = new HashMap<String,Object>();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        try {
            question.setQuestionCreateTime(df.parse(df.format(new Date())));
        } catch (ParseException e) {
            modelMap.put("message","获取时间失败");
        }
        int effectedNum = questionDao.insertQuestion(question);
        if(effectedNum > 0){
        }else{
            modelMap.put("message","加入失败");
        }
        return modelMap;
    }

    @Override
    public Map<String,Object> updateQuestion(Question question) {
        Map<String,Object> modelMap = new HashMap<String,Object>();
        int effectedNum = questionDao.updateQuestion(question);
        if(effectedNum > 0){
        }else{
            modelMap.put("message","更新失败");
        }
        return modelMap;
    }

    @Override
    public Map<String,Object> deleteQuestion(int questionId) {
        Map<String,Object> modelMap = new HashMap<String,Object>();
        int effectedNum = questionDao.deleteQuestion(questionId);
        if(effectedNum > 0){
        }else{
            modelMap.put("message","删除失败");
        }
        return modelMap;
    }
}
