package com.quiz.services.impl;

import com.quiz.entities.Question;
import com.quiz.entities.Quiz;
import com.quiz.repositories.QuizRepository;
import com.quiz.services.QuestionClient;
import com.quiz.services.QuizService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {

    final private QuizRepository quizRepository;
    final private QuestionClient questionClient;

    public QuizServiceImpl(QuizRepository quizRepository, QuestionClient questionClient) {
        this.quizRepository = quizRepository;
        this.questionClient = questionClient;
    }

    @Override
    public Quiz add(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @Override
    public Quiz get(Long id) {
        Quiz quiz =  quizRepository.findById(id).
                orElseThrow(() -> new RuntimeException("quiz not found"));
        quiz.setQuestions(questionClient.getQuestions(quiz.getId()));
        return quiz;
    }

    @Override
    public List<Quiz> get() {
        List<Quiz> quizList = quizRepository.findAll();
        for (Quiz quiz : quizList) {
            List<Question> questions = questionClient.getQuestions(quiz.getId());
            quiz.setQuestions(questions);
        }
        return quizList;
    }
}
