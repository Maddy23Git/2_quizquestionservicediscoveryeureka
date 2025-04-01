package com.maddy.question.controller;

import com.maddy.question.model.Question;
import com.maddy.question.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private IQuestionService questionService;

    @GetMapping
    public ResponseEntity<List<Question>> getQuestionAll() {
        System.out.println("Inside getQuestionAll()");
        List<Question> questionList = questionService.getQuestionAll();
        return ResponseEntity.status(HttpStatus.OK).body(questionList);
    }

    @GetMapping("/{questionId}")
    public ResponseEntity getQuestion(@PathVariable("questionId") Long questionId) {
        Question question = questionService.getQuestion(questionId);
        return ResponseEntity.status(HttpStatus.OK).body(question);
    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity<List<Question>> deleteQuestion(@PathVariable Long questionId) {
        List<Question> questionList = questionService.deleteQuestion(questionId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(questionList);
    }

    @PutMapping
    public ResponseEntity updateQuestion(@RequestBody Question question) {
        Question questionUpdated = questionService.updateQuestion(question);
        return ResponseEntity.status(HttpStatus.OK).body(questionUpdated);
    }

    @PostMapping
    public ResponseEntity saveQuestion(@RequestBody Question question) {
        System.out.println("Inside saveQuestion()");
        Question questionSaved = questionService.saveQuestion(question);
        return ResponseEntity.status(HttpStatus.CREATED).body(questionSaved);
    }

    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<List<Question>> getQuestionsByQuizId(@PathVariable Long quizId) {
        System.out.println("Inside getQuestionAll()");
        List<Question> questionList = questionService.getQuestionsByQuizId(quizId);
        return ResponseEntity.status(HttpStatus.OK).body(questionList);
    }
}
