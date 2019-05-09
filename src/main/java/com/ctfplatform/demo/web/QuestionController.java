package com.ctfplatform.demo.web;

import com.ctfplatform.demo.config.service.RSA;
import com.ctfplatform.demo.entity.Question;
import com.ctfplatform.demo.service.QuestionService;
import com.ctfplatform.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "listQuestion",method = RequestMethod.POST)
    private Map<String,Object> queryQuestion(){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        List<Question> list = questionService.queryQuestion();
        List<Question> decryptList = new LinkedList<>();
        for(int i = 0; i < list.size(); ++i){
            Question q = list.get(i);
            q.setQuestionAnswer(userService.decrypt(q.getQuestionAnswer()));
            decryptList.add(q);
        }
        modelMap.put("listQuestion",decryptList);
        return modelMap;
    }
    @RequestMapping(value = "insertQuestion",method = RequestMethod.POST)
    private Map<String,Object> insertQuestion(Question question){
        Map<String,Object> modelMap = new HashMap<String,Object>();
//        System.out.println(question.toString());
        //flag加密
        RSA rsa = new RSA();
        String flag = null;
        try {
            flag = rsa.testEncrypt(rsa.privateKey, question.getQuestionAnswer());
            question.setQuestionAnswer(flag);
        } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException
                | IllegalBlockSizeException | BadPaddingException | IOException e) {
            modelMap.put("message", "RSA加密失败");
        }
        modelMap.put("message",questionService.insertQuestion(question).get("message"));
        return modelMap;
    }
    @RequestMapping(value = "updateQuestion",method = RequestMethod.POST)
    private Map<String,Object> updateQuestion(Question question){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        modelMap.put("message",questionService.updateQuestion(question).get("message"));
        return modelMap;
    }
    @RequestMapping(value = "deleteQuestion",method = RequestMethod.POST)
    private Map<String,Object> deleteQuestion(int questionId){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        modelMap.put("message",questionService.deleteQuestion(questionId).get("message"));
        return modelMap;
    }
}
