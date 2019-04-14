package com.ctfplatform.demo.dao;

import com.ctfplatform.demo.entity.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> queryQuestion();
    int insertQuestion(Question question);
    int updateQuestion(Question question);
    int deleteQuestion(int questionId);
}
