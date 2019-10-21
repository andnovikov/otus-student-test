package ru.anovikov.learning.otusstudenttest.service;

import ru.anovikov.learning.otusstudenttest.domain.Question;

import java.util.List;

public interface QuestionService {
    List<Question> getAllQuestions();
}