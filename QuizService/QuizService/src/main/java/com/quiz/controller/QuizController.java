package com.quiz.controller;

import com.quiz.entities.Quiz;
import com.quiz.services.QuizService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {
    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping
    Quiz createQuiz(@RequestBody Quiz quiz) {
        return quizService.add(quiz);
    }

    @GetMapping("/{id}")
    Quiz getQuiz(@PathVariable Long id) {
        return quizService.get(id);
    }

    @GetMapping
    List<Quiz> getAllQuiz() {
        return quizService.get();
    }
}
