package com.ctfplatform.demo.web;

import com.ctfplatform.demo.entity.Record;
import com.ctfplatform.demo.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Record")
public class RecordController {
    @Autowired
    private RecordService recordService;

    @RequestMapping(value = "queryRecord",method = RequestMethod.POST)
    private Map<String,Object> queryRecord(){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        List<Record> list = recordService.queryRecord();
        modelMap.put("listRecord",list);
        return modelMap;
    }
    @RequestMapping(value = "insertRecord",method = RequestMethod.POST)
    private Map<String,Object> insertRecord(Record record){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        modelMap.put("message",recordService.insertRecord(record).get("message"));
        return modelMap;
    }
    @RequestMapping(value = "deleteRecord",method = RequestMethod.POST)
    private Map<String,Object> deleteRecord(int answerId){
        Map<String,Object> modelMap = new HashMap<String,Object>();

        modelMap.put("message",recordService.deleteRecord(answerId).get("message"));
        return modelMap;
    }
}
