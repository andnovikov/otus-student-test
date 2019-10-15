package ru.anovikov.learning.service;

import ru.anovikov.learning.domain.Question;

import java.util.List;

public interface QuestionService {
    List<Question> getAllQuestions();
}