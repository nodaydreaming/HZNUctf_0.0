package com.ctfplatform.demo.web;

import com.ctfplatform.demo.entity.Question;
import com.ctfplatform.demo.entity.Questiontype;
import com.ctfplatform.demo.service.QuestiontypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Questiontype")
public class QuestiontypeController {
    @Autowired
    private QuestiontypeService questiontypeService;

    @RequestMapping(value = "listQuestiontype",method = RequestMethod.POST)
    private Map<String,Object> listQuestiontype(){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        List<Questiontype> list = questiontypeService.queryQuestiontype();
        modelMap.put("listQuestiontype",list);
        return modelMap;
    }
    @RequestMapping(value = "insertQuestiontype",method = RequestMethod.POST)
    private Map<String,Object> insertQuestiontype(Questiontype questiontype){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        modelMap.put("message",questiontypeService.insertQuestiontype(questiontype).get("message"));
        return modelMap;
    }
    @RequestMapping(value = "updateQuestiontype",method = RequestMethod.POST)
    private Map<String,Object> updateQuestiontype(Questiontype questiontype){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        modelMap.put("message",questiontypeService.updateQuestiontype(questiontype).get("message"));
        return modelMap;
    }
    @RequestMapping(value = "deleteQuestiontype",method = RequestMethod.POST)
    private Map<String,Object> delteQuestiontype(String questiontypeId){
        int id = Integer.parseInt(questiontypeId);
        Map<String,Object> modelMap = new HashMap<String,Object>();
        modelMap.put("message",questiontypeService.deleteQuestiontype(id).get("message"));
        return modelMap;
    }
}
