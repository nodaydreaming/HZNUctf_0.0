package com.ctfplatform.demo.web;

import com.ctfplatform.demo.entity.SendData;
import com.ctfplatform.demo.service.SendDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/CompetitionMessage")
public class CompetitionMessageController {
    @Autowired
    private SendDataService sendDataService;
    @RequestMapping(value = "/queryByCompetitionId",method = RequestMethod.POST)
    private Map<String,Object> queryByCompetitionId(int competitionId){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        modelMap.put("CompetitionData",sendDataService.querySendDataByCompetitionId(competitionId));
        return modelMap;
    }
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    private Map<String,Object> insert(SendData sendData){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        modelMap.put("message",sendDataService.insert(sendData).get("message"));
        return modelMap;
    }
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    private Map<String,Object> update(SendData sendData){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        modelMap.put("message",sendDataService.update(sendData).get("message"));
        return modelMap;
    }
}
