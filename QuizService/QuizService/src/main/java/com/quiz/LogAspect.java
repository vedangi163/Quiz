package com.quiz;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Before("execution(* com.quiz.controller.QuizController.*(..))")
    void logBeforeAdvice(JoinPoint point) {
        logger.info("Before entering method " + point.getSignature()+" "+point.getArgs());
    }

    @After("execution(* com.quiz.controller.QuizController.*(..))")
    void logAfterAdvice(JoinPoint point) {
        logger.info("After entering method "+point.getSignature()+" "+point.getArgs());
    }

    @AfterReturning(pointcut = "execution(* com.quiz.controller.QuizController.*(..))", returning="result")
    void logAfterReturningAdvice(JoinPoint point, Object result) {
        logger.info("After returning method "+point.getSignature()+" "+point.getArgs()+ " "+result);
    }

    @AfterThrowing(pointcut = "execution(* com.quiz.controller.QuizController.*(..))", throwing="exception")
    void logAfterThrowingAdvice(JoinPoint point, Throwable exception) {
        logger.info("After throwing method "+point.getSignature()+" "+point.getArgs());
    }


    @Around("execution(* com.quiz.controller.QuizController.*(..))")
    Object logBeforeAdvice(ProceedingJoinPoint point) throws Throwable {
        logger.info("Before entering method " + point.getSignature()+" "+point.getArgs());
        Object result = point.proceed();
        //In case of exception, this after won't execute anything
        logger.info("After entering method " + point.getSignature()+" "+point.getArgs());
        return result;
    }

}

