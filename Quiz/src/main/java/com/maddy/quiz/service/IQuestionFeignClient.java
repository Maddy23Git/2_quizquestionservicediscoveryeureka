package com.maddy.quiz.service;

import com.maddy.quiz.model.Question;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

// Below is working
//@FeignClient(url = "http://localhost:8082", value = "Question-Client")

//@FeignClient(name = "{This is Service ID which is registered on Eureka Server}", value = "Question-Client")
@FeignClient(name = "QUESTION-SERVICE")
public interface IQuestionFeignClient {
    @GetMapping("/question/quiz/{quizId}")
    public List<Question> getQuestionsByQuiz(@PathVariable Long quizId);
}
