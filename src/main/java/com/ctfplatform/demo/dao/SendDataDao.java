package com.ctfplatform.demo.dao;

import com.ctfplatform.demo.entity.SendData;

public interface SendDataDao {
    SendData querySendDataByCompetitionId(int competitionId);
    int update(SendData sendData);
    int insert(SendData sendData);
}
