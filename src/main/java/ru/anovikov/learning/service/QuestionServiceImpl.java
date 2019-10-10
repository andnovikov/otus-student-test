package ru.anovikov.learning.service;

import ru.anovikov.learning.dao.QuestionDao;
import ru.anovikov.learning.domain.Question;

import java.util.List;

public class QuestionServiceImpl implements QuestionService {

    private QuestionDao questionDao;

    public QuestionServiceImpl(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    public List<Question> getAllQuestions() {

        return questionDao.findAll();
    }

}
