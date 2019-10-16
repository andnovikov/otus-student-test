package ru.anovikov.learning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.anovikov.learning.dao.QuestionDao;
import ru.anovikov.learning.domain.Question;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private QuestionDao questionDao;

    @Autowired
    public QuestionServiceImpl(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    public List<Question> getAllQuestions() {

        return questionDao.findAll();
    }

}
