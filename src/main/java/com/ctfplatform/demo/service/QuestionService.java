package com.ctfplatform.demo.service;

import com.ctfplatform.demo.entity.Question;

import java.util.List;
import java.util.Map;

public interface QuestionService {
    List<Question> queryQuestion();
    Map<String,Object> insertQuestion(Question question);
    Map<String,Object> updateQuestion(Question question);
    Map<String,Object> deleteQuestion(int questionId);
}
