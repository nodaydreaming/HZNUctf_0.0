package com.ctfplatform.demo.entity;

public class TeamCompetitor {
    private Integer teamcompetitorId;
    private Integer teamId;
    private Integer competitorId;
    private Integer competitorLevel;

    public Integer getCompetitorLevel() {
        return competitorLevel;
    }

    public void setCompetitorLevel(Integer competitorLevel) {
        this.competitorLevel = competitorLevel;
    }

    public Integer getTeamcompetitorId() {
        return teamcompetitorId;
    }

    public void setTeamcompetitorId(Integer teamcompetitorId) {
        this.teamcompetitorId = teamcompetitorId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getCompetitorId() {
        return competitorId;
    }

    public void setCompetitorId(Integer competitorId) {
        this.competitorId = competitorId;
    }
}
