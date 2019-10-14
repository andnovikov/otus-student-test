package ru.anovikov.learning.service;

import ru.anovikov.learning.domain.Question;

public interface TestingService {

    String readUserName ();

    String readUserLastName ();

    Question getNextQuestion();

    void printQuestion(Question question);

    int readTestAnswer ();

    void startTest();
}
