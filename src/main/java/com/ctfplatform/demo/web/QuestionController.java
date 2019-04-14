package com.ctfplatform.demo.web;

import com.ctfplatform.demo.entity.Question;
import com.ctfplatform.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @RequestMapping(value = "listQuestion",method = RequestMethod.POST)
    private Map<String,Object> queryQuestion(){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        List<Question> list = questionService.queryQuestion();
        modelMap.put("listQuestion",list);
        return modelMap;
    }
    @RequestMapping(value = "insertQuestion",method = RequestMethod.POST)
    private Map<String,Object> insertQuestion(Question question){
        Map<String,Object> modelMap = new HashMap<String,Object>();
//        System.out.println(question.toString());
        modelMap.put("message",questionService.insertQuestion(question).get("message"));
        return modelMap;
    }
    @RequestMapping(value = "updateQuestion",method = RequestMethod.POST)
    private Map<String,Object> updateQuestion(Question question){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        modelMap.put("message",questionService.updateQuestion(question).get("message"));
        return modelMap;
    }
    @RequestMapping(value = "deleteQuestion",method = RequestMethod.POST)
    private Map<String,Object> deleteQuestion(int questionId){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        modelMap.put("message",questionService.deleteQuestion(questionId).get("message"));
        return modelMap;
    }
}
