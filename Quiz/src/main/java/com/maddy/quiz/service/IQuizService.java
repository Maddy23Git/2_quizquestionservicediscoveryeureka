package com.maddy.quiz.service;

import com.maddy.quiz.model.Question;
import com.maddy.quiz.model.Quiz;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IQuizService {
    public List<Quiz> getQuizAll();
    public Quiz getQuiz(Long quizId);
    public List<Quiz> deleteQuiz(Long quizId);
    public Quiz updateQuiz(Quiz quiz);
    public Quiz saveQuiz(Quiz quiz);

    // Get all Questions by Quiz ID using Feign Client
    public Quiz getQuestionsByQuizId(Long quizId);
}

