package com.ctfplatform.demo.dao;

import com.ctfplatform.demo.entity.Information;

import java.util.List;

public interface InformationDao {
    List<Information> queryInformation();
    int insertInfor(Information information);
    int updateInfor(Information information);
    int deleteInfor(int informationId);
}
