package com.ctfplatform.demo.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class CompetitionUser {
    private Integer competitionuserId;
    private Integer competitionId;
    private Integer competitorId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date enteringTime;
    public Integer getCompetitionuserId() {
        return competitionuserId;
    }

    public Date getEnteringTime() {
        return enteringTime;
    }

    public void setEnteringTime(Date enteringTime) {
        this.enteringTime = enteringTime;
    }

    public void setCompetitionuserId(Integer competitionuserId) {
        this.competitionuserId = competitionuserId;
    }

    public Integer getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(Integer competitionId) {
        this.competitionId = competitionId;
    }

    public Integer getCompetitorId() {
        return competitorId;
    }

    public void setCompetitorId(Integer competitorId) {
        this.competitorId = competitorId;
    }
}
