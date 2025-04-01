package com.maddy.quiz.controller;

import com.maddy.quiz.model.Question;
import com.maddy.quiz.model.Quiz;
import com.maddy.quiz.service.IQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private IQuizService quizService;

    @GetMapping
    public ResponseEntity<List<Quiz>> getQuizAll() {
        System.out.println("Inside getQuizAll()");
        List<Quiz> quizList = quizService.getQuizAll();
        return ResponseEntity.status(HttpStatus.OK).body(quizList);
    }

    @GetMapping("/{quizId}")
    public ResponseEntity<Quiz> getQuiz(@PathVariable("quizId") Long quizId) {
        Quiz quiz = quizService.getQuiz(quizId);
        return ResponseEntity.status(HttpStatus.OK).body(quiz);
    }

    @DeleteMapping("/{quizId}")
    public ResponseEntity<List<Quiz>> deleteQuiz(@PathVariable Long quizId) {
        List<Quiz> quizList = quizService.deleteQuiz(quizId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(quizList);
    }

    @PutMapping
    public ResponseEntity<Quiz> updateQuiz(@RequestBody Quiz quiz) {
        Quiz quizUpdated = quizService.updateQuiz(quiz);
        return ResponseEntity.status(HttpStatus.OK).body(quizUpdated);
    }

    @PostMapping
    public ResponseEntity<Quiz> saveQuiz(@RequestBody Quiz quiz) {
        System.out.println("Inside saveQuiz()");
        Quiz quizSaved = quizService.saveQuiz(quiz);
        return ResponseEntity.status(HttpStatus.CREATED).body(quizSaved);
    }

    // Get all Questions by Quiz ID using Feign Client
    @GetMapping("/questionAllByQuizId/{quizId}")
    public ResponseEntity<Quiz> getQuestionsByQuizId(@PathVariable Long quizId) {
        System.out.println("Inside getQuestionsByQuizId()");
        Quiz quiz = quizService.getQuestionsByQuizId(quizId);
        return ResponseEntity.status(HttpStatus.OK).body(quiz);
    }
}
