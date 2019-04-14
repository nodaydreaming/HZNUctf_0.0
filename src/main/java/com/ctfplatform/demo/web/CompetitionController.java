package com.ctfplatform.demo.web;

import com.ctfplatform.demo.entity.Competition;
import com.ctfplatform.demo.entity.RegistrationEntity;
import com.ctfplatform.demo.service.CompetitionService;
import com.ctfplatform.demo.service.CompetitionUserService;
import com.ctfplatform.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/competition")
public class CompetitionController {
    @Autowired
    private CompetitionService competitionService;

    @RequestMapping(value = "/listcompetition",method = RequestMethod.POST)
    private Map<String,Object> listCompetition(){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        List<Competition> list = competitionService.queryCompetition();
        modelMap.put("competitionlist",list);
        return modelMap;
    }
    @RequestMapping(value = "/getcompetitionbytitle",method = RequestMethod.POST)
    private Map<String,Object> queryCompetitionByTitle(String competitionTitle) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        Competition competition = competitionService.queryCompetitionByTitle(competitionTitle);
        modelMap.put("queryCompetitionByTitle",competition);
        return modelMap;
    }
    @RequestMapping(value = "/getcompetitionbynumber",method = RequestMethod.POST)
    private Map<String,Object> queryCompetitionByNumber(String number) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        Competition competition =  competitionService.queryCompetitionByNumber(number);
        modelMap.put("competition",competition);
        return modelMap;
    }

    @RequestMapping(value = "insertCompetition",method = RequestMethod.POST)
    private Map<String,Object> insertCompetition(Competition competition) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        Map<String, Object> resultMap = competitionService.insert(competition);
        if (resultMap.containsKey("message")) {
            modelMap.put("message", resultMap.get("message"));
        }
        else{
            int competitionId = competitionService.queryCompetitionByTitle(competition.getCompetitionTitle()).getCompetitionId();
            modelMap.put("competitionId",competitionId);
        }
        return modelMap;
    }
    @RequestMapping(value = "updateCompetition",method = RequestMethod.POST)
    private Map<String,Object> updatecompetition(Competition competition) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
//        System.out.println(competition.toString());
        modelMap.put("message",competitionService.update(competition).get("message"));
        return modelMap;
    }
    @RequestMapping(value = "deleteCompetition",method = RequestMethod.POST)
    private Map<String,Object> deletecompetition(int competitionId) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("message",competitionService.delete(competitionId).get("message"));
        return modelMap;
    }
}
