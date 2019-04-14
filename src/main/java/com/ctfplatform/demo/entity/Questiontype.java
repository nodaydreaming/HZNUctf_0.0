package com.ctfplatform.demo.entity;

public class Questiontype {
    private Integer questionTypeId;

    private String questionType;
    private String questionTypeIntroduction;

    public String getQuestionTypeIntroduction() {
        return questionTypeIntroduction;
    }

    public void setQuestionTypeIntroduction(String questionTypeIntroduction) {
        this.questionTypeIntroduction = questionTypeIntroduction;
    }

    public Integer getQuestionTypeId() {
        return questionTypeId;
    }

    public void setQuestionTypeId(Integer questionTypeId) {
        this.questionTypeId = questionTypeId;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }
}
