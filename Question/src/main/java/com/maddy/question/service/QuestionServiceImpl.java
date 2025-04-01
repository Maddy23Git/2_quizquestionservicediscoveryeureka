package com.maddy.question.service;

import com.maddy.question.entity.QuestionEntity;
import com.maddy.question.model.Question;
import com.maddy.question.repo.QuestionRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements IQuestionService {

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Question> getQuestionAll() {
        List<QuestionEntity> allQuestionList = questionRepository.findAll();
        List<Question> allQuestion = modelMapper.map(allQuestionList, new TypeToken<List<Question>>() {
        }.getType());
        System.out.println("All Question: " + allQuestion);
        return allQuestion;
    }

    @Override
    public Question getQuestion(Long questionId) {
        Optional<QuestionEntity> optionalQuestionEntity = questionRepository.findById(questionId);
        QuestionEntity questionEntity = optionalQuestionEntity.get();
        Question question = modelMapper.map(questionEntity, Question.class);
        System.out.println("Question by ID: " + questionId + " == " + question);
        return question;
    }

    @Override
    public List<Question> deleteQuestion(Long questionId) {
        questionRepository.deleteById(questionId);
        return getQuestionAll();
    }

    @Override
    public Question updateQuestion(Question question) {
        Long questionId = question.getQuestionId();
        Optional<QuestionEntity> optionalQuestionEntityDB = questionRepository.findById(questionId);
        QuestionEntity questionEntityDB = optionalQuestionEntityDB.get();
        Long questionIdDB = questionEntityDB.getQuestionId();
        if (questionId == questionIdDB) {
            if(question.getQuestionDesc()!= null && question.getQuestionDesc()!= "")
                questionEntityDB.setQuestionDesc(question.getQuestionDesc());
            questionRepository.save(questionEntityDB);
            Question questionNew = modelMapper.map(questionEntityDB, Question.class);
            return questionNew;
        }
        return null;
    }

    @Override
    public Question saveQuestion(Question question) {
        QuestionEntity questionEntity = modelMapper.map(question, QuestionEntity.class);
        QuestionEntity savedQuestionEntity = questionRepository.save(questionEntity);
        Question savedQuestion = modelMapper.map(savedQuestionEntity, Question.class);
        return savedQuestion;
    }

    @Override
    public List<Question> getQuestionsByQuizId(Long questionId) {
        List<QuestionEntity> allQuestionList = questionRepository.findByQuizId(questionId);
        List<Question> allQuestion = modelMapper.map(allQuestionList, new TypeToken<List<Question>>() {
        }.getType());
        System.out.println("All Question: "+ allQuestion+ " by Quiz ID: " + questionId);
        return allQuestion;
    }
}
