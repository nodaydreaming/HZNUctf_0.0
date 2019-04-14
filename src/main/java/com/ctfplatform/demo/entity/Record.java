package com.ctfplatform.demo.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Record {
    private Integer answerId;
    private Integer competitionId;
    private Integer questionId;
    private Integer teamId;
    private Integer competitorId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date answerTime;
    private String answerBody;
    private Integer answerResult;
    private float answerGetPoint;

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public Integer getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(Integer competitionId) {
        this.competitionId = competitionId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
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

    public Date getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(Date answerTime) {
        this.answerTime = answerTime;
    }

    public String getAnswerBody() {
        return answerBody;
    }

    public void setAnswerBody(String answerBody) {
        this.answerBody = answerBody;
    }

    public Integer getAnswerResult() {
        return answerResult;
    }

    public void setAnswerResult(Integer answerResult) {
        this.answerResult = answerResult;
    }

    public float getAnswerGetPoint() {
        return answerGetPoint;
    }

    public void setAnswerGetPoint(float answerGetPoint) {
        this.answerGetPoint = answerGetPoint;
    }
}
