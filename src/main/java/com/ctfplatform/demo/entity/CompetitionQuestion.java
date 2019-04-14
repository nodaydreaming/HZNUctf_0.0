package com.ctfplatform.demo.entity;

public class CompetitionQuestion {
    private Integer competitionQuestionId;
    private Integer questionId;
    private Integer competitionId;
    private Integer questionNumber;

    public Integer getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(Integer questionNumber) {
        this.questionNumber = questionNumber;
    }

    public Integer getCompetitionQuestionId() {
        return competitionQuestionId;
    }

    public void setCompetitionQuestionId(Integer competitionQuestionId) {
        this.competitionQuestionId = competitionQuestionId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(Integer competitionId) {
        this.competitionId = competitionId;
    }

    @Override
    public String toString() {
        return "CompetitionQuestion{" +
                "competitionQuestionId=" + competitionQuestionId +
                ", questionId=" + questionId +
                ", competitionId=" + competitionId +

                '}';
    }
}
