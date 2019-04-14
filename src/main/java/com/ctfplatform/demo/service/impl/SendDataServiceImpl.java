package com.ctfplatform.demo.service.impl;

import com.ctfplatform.demo.dao.SendDataDao;
import com.ctfplatform.demo.entity.SendData;
import com.ctfplatform.demo.service.SendDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class SendDataServiceImpl implements SendDataService {
    @Autowired
    private SendDataDao sendDataDao;
    @Override
    public SendData querySendDataByCompetitionId(int competitionId) {
        return sendDataDao.querySendDataByCompetitionId(competitionId);
    }

    @Override
    public Map<String, Object> update(SendData sendData) {
        Map<String,Object> modelMap = new HashMap<String,Object>();
        int effectedNum = sendDataDao.update(sendData);
        if(effectedNum >0){
        }else{
            modelMap.put("message","更新操作失败");
        }
        return modelMap;
    }

    @Override
    public Map<String, Object> insert(SendData sendData) {
        Map<String,Object> modelMap = new HashMap<String,Object>();
        int effectedNum = sendDataDao.insert(sendData);
        if(effectedNum > 0){
        }else{
            modelMap.put("message","插入操作失败");
        }
        return modelMap;
    }
}
