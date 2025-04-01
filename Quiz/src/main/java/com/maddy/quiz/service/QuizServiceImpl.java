package com.maddy.quiz.service;

import com.maddy.quiz.entity.QuizEntity;
import com.maddy.quiz.model.Question;
import com.maddy.quiz.model.Quiz;
import com.maddy.quiz.repo.QuizRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizServiceImpl implements IQuizService {

    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IQuestionFeignClient questionFeignClient;

    @Override
    public List<Quiz> getQuizAll() {
        List<QuizEntity> allQuizList = quizRepository.findAll();
        List<Quiz> allQuiz = modelMapper.map(allQuizList, new TypeToken<List<Quiz>>() {
        }.getType());
        System.out.println("All Quiz: "+allQuiz);
        return allQuiz;
    }

    @Override
    public Quiz getQuiz(Long quizId) {
        Optional<QuizEntity> quizById = quizRepository.findById(quizId);
        QuizEntity quizEntity = quizById.get();
        Quiz quiz = modelMapper.map(quizEntity, Quiz.class);
        System.out.println("Quiz by ID: "+quizId+ " == "+quiz);
        return quiz;
    }

    @Override
    public List<Quiz> deleteQuiz(Long quizId) {
        quizRepository.deleteById(quizId);
        return getQuizAll();
    }

    @Override
    public Quiz updateQuiz(Quiz quiz) {
        Long quizId = quiz.getQuizId();
        Optional<QuizEntity> optionalQuizEntity = quizRepository.findById(quizId);
        QuizEntity quizEntityDB = optionalQuizEntity.get();
        Long quizIdDB = quizEntityDB.getQuizId();
        if (quizId == quizIdDB) {
            QuizEntity quizEntity = modelMapper.map(quiz, QuizEntity.class);
            quizRepository.save(quizEntity);
            Quiz quizNew = modelMapper.map(quizEntity, Quiz.class);
            return quizNew;
        }
        return null;
    }

    @Override
    public Quiz saveQuiz(Quiz quiz) {
        QuizEntity quizEntity = modelMapper.map(quiz, QuizEntity.class);
        QuizEntity savedQuizEntity = quizRepository.save(quizEntity);
        Quiz savedQuiz = modelMapper.map(savedQuizEntity, Quiz.class);
        return savedQuiz;
    }

    // Get all Questions by Quiz ID using Feign Client
    @Override
    public Quiz getQuestionsByQuizId(Long quizId) {
        Optional<QuizEntity> quizEntityOptional = quizRepository.findById(quizId);
        QuizEntity quizEntity = quizEntityOptional.get();
        Quiz quiz = modelMapper.map(quizEntity, Quiz.class);
        List<Question> questionsListByQuiz = questionFeignClient.getQuestionsByQuiz(quizId);
        quiz.setQuestionList(questionsListByQuiz);
        return quiz;
    }
}
