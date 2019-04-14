package com.ctfplatform.demo.dao;

import com.ctfplatform.demo.entity.Questiontype;

import java.util.List;

public interface QuestiontypeDao {
    List<Questiontype> queryQuestiontype();
    int insertQuestiontype(Questiontype questiontype);
    int updateQuestiontype(Questiontype questiontype);
    int deleteQuestiontype(int questiontypeId);
}
