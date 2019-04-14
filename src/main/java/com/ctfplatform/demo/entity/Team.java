package com.ctfplatform.demo.entity;

public class Team {
    private Integer teamId;
    private String teamName;
    private Integer teamCompetitionId;
    private float teamPoint;

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Integer getTeamCompetitionId() {
        return teamCompetitionId;
    }

    public void setTeamCompetitionId(Integer teamCompetitionId) {
        this.teamCompetitionId = teamCompetitionId;
    }

    public float getTeamPoint() {
        return teamPoint;
    }

    public void setTeamPoint(float teamPoint) {
        this.teamPoint = teamPoint;
    }
}
