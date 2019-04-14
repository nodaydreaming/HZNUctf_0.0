package com.ctfplatform.demo.web;

import com.ctfplatform.demo.entity.CompetitionQuestion;
import com.ctfplatform.demo.service.CompetitionQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/CompetitionQuestion")
public class CompetitionQuestionController {
    @Autowired
    private CompetitionQuestionService competitionQuestionService;

    @RequestMapping(value = "queryCompetitionQuestion",method = RequestMethod.POST)
    private Map<String,Object> listCompetitionquestion(){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        List<CompetitionQuestion> list = competitionQuestionService.queryCompetitionquestion();
        modelMap.put("queryCompetitionquestion",list);
        return modelMap;
    }
    @RequestMapping(value = "listCompetitionQuestionId",method = RequestMethod.POST)
    private Map<String,Object> listCompetitionQuestionId(int competitionId){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        modelMap.put("listCompetitionQuestionId",competitionQuestionService.listCompetitionQuestionId(competitionId));
        return modelMap;
    }
    @RequestMapping(value = "insertCompetitionQuestion",method = RequestMethod.POST)
    private Map<String,Object> insertCompetitionQuestion(CompetitionQuestion competitionQuestion){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        modelMap.put("message",competitionQuestionService.insertCompetitionQuestion(competitionQuestion).get("message"));
        return modelMap;
    }
    @RequestMapping(value = "deleteCompetitionQuestion",method = RequestMethod.POST)
    private Map<String,Object> insertCompetitionQuestion(int competitionQuestionId){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        modelMap.put("message",competitionQuestionService.deleteCompetitionQuestion(competitionQuestionId).get("message"));
        return modelMap;
    }
}
