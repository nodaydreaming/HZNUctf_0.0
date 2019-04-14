package com.ctfplatform.demo.service;

import com.ctfplatform.demo.entity.Record;

import java.util.List;
import java.util.Map;

public interface RecordService {
    List<Record> queryRecord();
    Map<String,Object> insertRecord(Record record);
    Map<String,Object> deleteRecord(int answerId);
}
