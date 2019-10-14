package ru.anovikov.learning.dao;

import ru.anovikov.learning.domain.Question;

import java.util.List;

public interface QuestionDao {

    List<Question> findAll();

}
