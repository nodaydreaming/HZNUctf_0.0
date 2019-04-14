package com.ctfplatform.demo.entity.send;

import java.util.Date;

/**
 * 用于发送到比赛端的比赛信息实体类
 */
public class SendCompetition {
    private int id;
    private String competitionId;
    private String name;
    private String organizer;
    private boolean teamCompetition;
    private boolean canRegister;
    private Long startTime;
    private Long endTime;

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

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public boolean isTeamCompetition() {
        return teamCompetition;
    }

    public void setTeamCompetition(boolean teamCompetition) {
        this.teamCompetition = teamCompetition;
    }

    public boolean isCanRegister() {
        return canRegister;
    }

    public void setCanRegister(boolean canRegister) {
        this.canRegister = canRegister;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }
}
