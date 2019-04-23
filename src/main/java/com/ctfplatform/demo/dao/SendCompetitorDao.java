package com.ctfplatform.demo.dao;

import com.ctfplatform.demo.entity.send.SendCompetitor;

import java.util.List;

public interface SendCompetitorDao {
    int insert(SendCompetitor sendCompetitor);
    List<SendCompetitor> queryall();
    SendCompetitor queryByAccount(String account);
}
