package com.ctfplatform.demo.service;

import com.ctfplatform.demo.entity.SendData;

import java.util.Map;

public interface SendDataService {
    SendData querySendDataByCompetitionId(int competitionId);
    Map<String,Object> update(SendData sendData);
    Map<String,Object> insert(SendData sendData);
}
