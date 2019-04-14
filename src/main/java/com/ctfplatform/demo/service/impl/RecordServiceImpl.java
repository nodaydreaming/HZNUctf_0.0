package com.ctfplatform.demo.service.impl;

import com.ctfplatform.demo.dao.RecordDao;
import com.ctfplatform.demo.entity.Record;
import com.ctfplatform.demo.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RecordServiceImpl implements RecordService {
    @Autowired
    private RecordDao recordDao;

    @Override
    public List<Record> queryRecord() {
        return recordDao.queryRecord();
    }
    @Transactional
    @Override
    public Map<String,Object> insertRecord(Record record) {
        Map<String,Object> modelMap = new HashMap<String,Object>();
        int effectedNum = recordDao.insertRecord(record);
        if(effectedNum > 0){

        }else{
            modelMap.put("message","加入记录失败");
        }
        return modelMap;
    }

    @Override
    public Map<String,Object> deleteRecord(int answerId) {
        Map<String,Object> modelMap = new HashMap<String,Object>();
        int effectedNum = recordDao.deleteRecord(answerId);
        if(effectedNum > 0){

        }else{
            modelMap.put("message","删除记录失败");
        }
        return modelMap;
    }
}
