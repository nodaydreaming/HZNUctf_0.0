package com.ctfplatform.demo.entity.send;

import java.util.List;

/**
 * 用于发送到比赛端的队伍实体类
 */
public class SendTeam {
    private int id;
    private String competitionId;
    private String name;
    private String account;
    private List<String> teamMates;
    private String captain;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(String competitionId) {
        this.competitionId = competitionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public List<String> getTeamMates() {
        return teamMates;
    }

    public void setTeamMates(List<String> teamMates) {
        this.teamMates = teamMates;
    }

    public String getCaptain() {
        return captain;
    }

    public void setCaptain(String captain) {
        this.captain = captain;
    }
}
