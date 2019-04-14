package com.ctfplatform.demo.dao;

import com.ctfplatform.demo.entity.Record;

import java.util.List;

public interface RecordDao {
    List<Record> queryRecord();
    int insertRecord(Record record);
    int deleteRecord(int answerId);
}
