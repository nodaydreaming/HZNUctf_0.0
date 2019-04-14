package com.ctfplatform.demo.service.impl;

import com.ctfplatform.demo.dao.InformationDao;
import com.ctfplatform.demo.entity.Information;
import com.ctfplatform.demo.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InformationServiceImpl implements InformationService {
    @Autowired
    private InformationDao informationDao;

    @Override
    public List<Information> queryInformation() {
        return informationDao.queryInformation();
    }

    @Transactional
    @Override
    public Map<String,Object> insertInfor(Information information) {
        Map<String,Object> modelMap = new HashMap<String,Object>();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        try {
            information.setCreateTime(df.parse(df.format(new Date())));
        } catch (ParseException e) {
            modelMap.put("message","获取时间失败");
        }
        int effectedNum = informationDao.insertInfor(information);
        if(effectedNum > 0){
        }else{
            modelMap.put("message","加入通知失败");
        }
        return modelMap;
    }

    @Override
    public Map<String,Object> updateInfor(Information information) {
        Map<String,Object> modelMap = new HashMap<String,Object>();
        int effectedNum = informationDao.updateInfor(information);
        if(effectedNum > 0){
        }else{
            modelMap.put("message","删除失败");
        }
        return modelMap;
    }

    @Override
    public Map<String,Object> deleteInfor(int informationId) {
        Map<String,Object> modelMap = new HashMap<String,Object>();
        int effectedNum = informationDao.deleteInfor(informationId);
        if(effectedNum > 0){
        }else{
            modelMap.put("message","删除失败");
        }
        return modelMap;
    }
}
