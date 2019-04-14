package com.ctfplatform.demo.service;

import com.ctfplatform.demo.entity.Information;

import java.util.List;
import java.util.Map;

public interface InformationService {
    List<Information> queryInformation();
    Map<String,Object> insertInfor(Information information);
    Map<String,Object> updateInfor(Information information);
    Map<String,Object> deleteInfor(int informationId);
}
