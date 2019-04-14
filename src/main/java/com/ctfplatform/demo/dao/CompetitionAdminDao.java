package com.ctfplatform.demo.dao;

import com.ctfplatform.demo.entity.CompetitionAdmin;

import java.util.List;

public interface CompetitionAdminDao {
    List<CompetitionAdmin> listCompetitionAdmin();
    List<Integer> listCompetitionAdminId(int competitionId);
    int insert(CompetitionAdmin competitionAdmin);
    int delete(CompetitionAdmin competitionAdmin);
}
