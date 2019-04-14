package com.ctfplatform.demo.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Competition {
    private Integer competitionId;
    private String competitionTitle;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date competitionStart;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date competitionEnd;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date competitionCreatetime;
    private Integer competitionCanregister;
    private Integer competitionIsteam;
    private String competitionNumber;

    private String competitionHolder;

    public String getCompetitionHolder() {
        return competitionHolder;
    }

    public void setCompetitionHolder(String competitionHolder) {
        this.competitionHolder = competitionHolder;
    }


    public String getCompetitionNumber() {
        return competitionNumber;
    }

    public void setCompetitionNumber(String competitionNumber) {
        this.competitionNumber = competitionNumber;
    }

    public Integer getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(Integer competitionId) {
        this.competitionId = competitionId;
    }

    public String getCompetitionTitle() {
        return competitionTitle;
    }

    public void setCompetitionTitle(String competitionTitle) {
        this.competitionTitle = competitionTitle;
    }

    public Date getCompetitionStart() {
        return competitionStart;
    }

    public void setCompetitionStart(Date competitionStart) {
        this.competitionStart = competitionStart;
    }

    public Date getCompetitionEnd() {
        return competitionEnd;
    }

    public void setCompetitionEnd(Date competitionEnd) {
        this.competitionEnd = competitionEnd;
    }



    public Date getCompetitionCreatetime() {
        return competitionCreatetime;
    }

    public void setCompetitionCreatetime(Date competitionCreatetime) {
        this.competitionCreatetime = competitionCreatetime;
    }

    public Integer getCompetitionCanregister() {
        return competitionCanregister;
    }

    public void setCompetitionCanregister(Integer competitionCanregister) {
        this.competitionCanregister = competitionCanregister;
    }

    public Integer getCompetitionIsteam() {
        return competitionIsteam;
    }

    public void setCompetitionIsteam(Integer competitionIsteam) {
        this.competitionIsteam = competitionIsteam;
    }

    @Override
    public String toString() {
        return "Competition{" +
                "competitionId=" + competitionId +
                ", competitionTitle='" + competitionTitle + '\'' +
                ", competitionStart=" + competitionStart +
                ", competitionEnd=" + competitionEnd +
                ", competitionCreatetime=" + competitionCreatetime +
                ", competitionCanregister=" + competitionCanregister +
                ", competitionIsteam=" + competitionIsteam +
                ", competitionNumber='" + competitionNumber + '\'' +
                ", competitionHolder='" + competitionHolder + '\'' +
                '}';
    }
}
