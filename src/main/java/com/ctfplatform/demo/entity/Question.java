package com.ctfplatform.demo.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Question {
    private Integer questionId;
    private String questionTitle;
    private Integer questionTypeId;
    private String questionBody;
    private String questionResource;
    private String questionAnswer;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date questionCreateTime;
    private Integer adminId;
    private String questionAuthor;
    private Integer questionPoint;
    private Integer questionAdditional;
    private Double questionDecrease;
    private Integer questionLevel;

    public Integer getQuestionLevel() {
        return questionLevel;
    }

    public void setQuestionLevel(Integer questionLevel) {
        this.questionLevel = questionLevel;
    }

    public Integer getQuestionPoint() {
        return questionPoint;
    }

    public void setQuestionPoint(Integer questionPoint) {
        this.questionPoint = questionPoint;
    }

    public Double getQuestionDecrease() {
        return questionDecrease;
    }

    public void setQuestionDecrease(Double questionDecrease) {
        this.questionDecrease = questionDecrease;
    }

    public Integer getQuestionAdditional() {
        return questionAdditional;
    }

    public void setQuestionAdditional(Integer questionAdditional) {
        this.questionAdditional = questionAdditional;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public Integer getQuestionTypeId() {
        return questionTypeId;
    }

    public void setQuestionTypeId(Integer questionTypeId) {
        this.questionTypeId = questionTypeId;
    }

    public String getQuestionBody() {
        return questionBody;
    }

    public void setQuestionBody(String questionBody) {
        this.questionBody = questionBody;
    }

    public String getQuestionResource() {
        return questionResource;
    }

    public void setQuestionResource(String questionResource) {
        this.questionResource = questionResource;
    }

    public String getQuestionAnswer() {
        return questionAnswer;
    }

    public void setQuestionAnswer(String questionAnswer) {
        this.questionAnswer = questionAnswer;
    }

    public Date getQuestionCreateTime() {
        return questionCreateTime;
    }

    public void setQuestionCreateTime(Date questionCreateTime) {
        this.questionCreateTime = questionCreateTime;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getQuestionAuthor() {
        return questionAuthor;
    }

    public void setQuestionAuthor(String questionAuthor) {
        this.questionAuthor = questionAuthor;
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionId=" + questionId +
                ", questionTitle='" + questionTitle + '\'' +
                ", questionTypeId=" + questionTypeId +
                ", questionBody='" + questionBody + '\'' +
                ", questionResource='" + questionResource + '\'' +
                ", questionAnswer='" + questionAnswer + '\'' +
                ", questionCreateTime=" + questionCreateTime +
                ", adminId=" + adminId +
                ", questionAuthor='" + questionAuthor + '\'' +
                ", questionPoint=" + questionPoint +
                ", questionAdditional=" + questionAdditional +
                ", questionDecrease=" + questionDecrease +
                ", questionLevel=" + questionLevel +
                '}';
    }
}
