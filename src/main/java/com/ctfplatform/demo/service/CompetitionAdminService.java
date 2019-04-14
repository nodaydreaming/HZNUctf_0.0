package com.ctfplatform.demo.service;

import com.ctfplatform.demo.entity.CompetitionAdmin;

import java.util.List;
import java.util.Map;

public interface CompetitionAdminService {
    List<CompetitionAdmin> list();
    List<Integer> listCompetitionAdminId(int competitionId);
    Map<String,Object> insert(CompetitionAdmin competitionAdmin);
    Map<String,Object> delete(CompetitionAdmin competitionAdmin);
}
