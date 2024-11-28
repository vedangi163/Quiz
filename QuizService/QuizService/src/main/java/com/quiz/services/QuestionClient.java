package com.quiz.services;

import com.quiz.entities.Question;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

//@FeignClient(url = "http://localhost:9092", value = "Question-Client")
@FeignClient(name = "QUESTIONSERVICE")
public interface QuestionClient {

    //feign client will call the api from question service with this url http://localhost:8082/question/quiz/{id}
    @GetMapping("/question/quiz/{id}")
    List<Question> getQuestions(@PathVariable Long id);

}
