package com.ctfplatform.demo.web;

import com.ctfplatform.demo.entity.CompetitionAdmin;
import com.ctfplatform.demo.entity.CompetitionQuestion;
import com.ctfplatform.demo.entity.Information;
import com.ctfplatform.demo.service.CompetitionAdminService;
import com.ctfplatform.demo.service.CompetitionQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/CompetitionAdmin")
public class CompetitionAdminController {
    @Autowired
    private CompetitionAdminService competitionAdminService;
    @Autowired
    private CompetitionQuestionService competitionQuestionService;
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    private Map<String,Object> list(){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        modelMap.put("listCompetitionAdmin",competitionAdminService.list());
        return modelMap;
    }
    @RequestMapping(value = "/listCompetitionAdminId",method = RequestMethod.POST)
    private Map<String,Object> listCompetitionAdminId(int competitionId){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        modelMap.put("listCompetitionAdminId",competitionAdminService.listCompetitionAdminId(competitionId));
        return modelMap;
    }
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    private Map<String,Object> update(int competitionId,String adminAdminList,String questionList){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        if(adminAdminList != null && adminAdminList != "") {
            CompetitionAdmin ca = new CompetitionAdmin();
            ca.setCompetitionId(competitionId);
            Map<String, Object> map = competitionAdminService.delete(ca);
            if (map.containsKey("message")) {
                modelMap.put("message", map.get("message"));
            }
            String[] list = adminAdminList.split(",");
            for (int i = 0; i < list.length; i++) {
                if (list[i] != "" && list[i] != null) {
                    CompetitionAdmin competitionAdmin = new CompetitionAdmin();
                    competitionAdmin.setCompetitionId(competitionId);
                    competitionAdmin.setAdminId(Integer.parseInt(list[i]));
                    map = competitionAdminService.insert(competitionAdmin);
                    if (map.containsKey("message")) {
                        modelMap.put("message", map.get("message"));
                    }
                }
            }
        }
        if(questionList != null && questionList != "") {
            Map<String, Object> map = competitionQuestionService.deleteCompetitionQuestion(competitionId);
            if (map.containsKey("message")) {
                modelMap.put("message", map.get("message"));
            }
            String[] quelist = questionList.split(",");
            for (int i = 0; i < quelist.length; i++) {
                if (quelist[i] != "" && quelist[i] != null) {
                    CompetitionQuestion competitionQuestion = new CompetitionQuestion();
                    competitionQuestion.setCompetitionId(competitionId);
                    competitionQuestion.setQuestionId(Integer.parseInt(quelist[i]));
                    competitionQuestion.setQuestionNumber(i + 1);

                    map = competitionQuestionService.insertCompetitionQuestion(competitionQuestion);
                    if (map.containsKey("message")) {
                        modelMap.put("message", map.get("message"));
                    }
                }
            }
        }
        return modelMap;
    }
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    private Map<String,Object> insert(int competitionId,String adminAdminList,String questionList){
        Map<String,Object> modelMap = new HashMap<String,Object>();

        String[] list = adminAdminList.split(",");
        String[] quelist = questionList.split(",");

        for(int i=0;i<list.length;i++){
//            System.out.println(i);
            if(list[i] != "" && list[i] != null) {
                CompetitionAdmin competitionAdmin = new CompetitionAdmin();
                competitionAdmin.setCompetitionId(competitionId);
                competitionAdmin.setAdminId(Integer.parseInt(list[i]));
                Map<String, Object> map = competitionAdminService.insert(competitionAdmin);
                if(map.containsKey("message")){
                    modelMap.put("message", map.get("message"));
                }
            }
        }
        for(int i = 0;i<quelist.length;i++){
//            System.out.println(i);
            if(quelist[i] != "" && quelist[i] != null) {
                CompetitionQuestion competitionQuestion = new CompetitionQuestion();
                competitionQuestion.setCompetitionId(competitionId);
                competitionQuestion.setQuestionId(Integer.parseInt(quelist[i]));
                competitionQuestion.setQuestionNumber(i+1);
                Map<String, Object> map = competitionQuestionService.insertCompetitionQuestion(competitionQuestion);
                if(map.containsKey("message")){
                    modelMap.put("message", map.get("message"));
                }
            }
        }
        return modelMap;
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    private Map<String,Object> delete(CompetitionAdmin competitionAdmin){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        if(competitionAdminService.delete(competitionAdmin).containsKey("message")){
            modelMap.put("message",competitionAdminService.delete(competitionAdmin).get("message"));
        }
        return modelMap;
    }
}
