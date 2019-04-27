package com.ctfplatform.demo.dao;

import com.ctfplatform.demo.entity.send.Problems;

import java.util.List;

public interface ProblemsDao {
    int insert(Problems problems);
    List<Problems> queryByCompetitionId(int competitionId);
}
