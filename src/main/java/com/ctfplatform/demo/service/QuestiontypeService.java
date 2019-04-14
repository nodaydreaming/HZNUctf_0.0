package com.ctfplatform.demo.service;

import com.ctfplatform.demo.entity.Questiontype;

import java.util.List;
import java.util.Map;

public interface QuestiontypeService {
    List<Questiontype> queryQuestiontype();
    Map<String,Object> insertQuestiontype(Questiontype questiontype);
    Map<String,Object> updateQuestiontype(Questiontype questiontype);
    Map<String,Object> deleteQuestiontype(int questiontypeId);
}
