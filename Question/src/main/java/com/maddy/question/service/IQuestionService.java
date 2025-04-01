package com.maddy.question.service;

import com.maddy.question.model.Question;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IQuestionService {
    public List<Question> getQuestionAll();
    public Question getQuestion(Long questionId);
    public List<Question> deleteQuestion(Long questionId);
    public Question updateQuestion(Question question);
    public Question saveQuestion(Question question);
    public List<Question> getQuestionsByQuizId(Long questionId);
}

