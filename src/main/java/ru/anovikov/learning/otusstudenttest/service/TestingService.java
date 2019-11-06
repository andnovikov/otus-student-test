package ru.anovikov.learning.otusstudenttest.service;

import ru.anovikov.learning.otusstudenttest.domain.Question;

public interface TestingService {

    String readUserName ();

    Question getNextQuestion();

    void printQuestion(Question question);

    int readTestAnswer ();

    int startTest();

    void printResult();
}
