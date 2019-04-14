package com.ctfplatform.demo.web;

import com.ctfplatform.demo.entity.Information;
import com.ctfplatform.demo.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Information")
public class InformationController {
    @Autowired
    private InformationService informationService;

    @RequestMapping(value = "listInformation",method = RequestMethod.POST)
    private Map<String,Object> queryInformation(){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        List<Information> list = informationService.queryInformation();
        modelMap.put("listInformation",list);
        return modelMap;
    }
    @RequestMapping(value = "insertInfor",method = RequestMethod.POST)
    private Map<String,Object> insertInformation(Information information){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        modelMap.put("message",informationService.insertInfor(information).get("message"));
        return modelMap;
    }
    @RequestMapping(value = "updateInfor",method = RequestMethod.POST)
    private Map<String,Object> updateInformation(Information information){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        modelMap.put("message",informationService.updateInfor(information).get("message"));
        return modelMap;
    }
    @RequestMapping(value = "deleteInfor",method = RequestMethod.POST)
    private Map<String,Object> deleteInformation(int informationId){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        modelMap.put("message",informationService.deleteInfor(informationId).get("message"));
        return modelMap;
    }
}
