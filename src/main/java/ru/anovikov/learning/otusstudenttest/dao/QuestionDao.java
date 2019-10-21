package ru.anovikov.learning.otusstudenttest.dao;

import ru.anovikov.learning.otusstudenttest.domain.Question;

import java.util.List;

public interface QuestionDao {

    List<Question> findAll();

}
