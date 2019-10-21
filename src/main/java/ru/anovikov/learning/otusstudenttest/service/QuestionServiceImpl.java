package ru.anovikov.learning.otusstudenttest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.anovikov.learning.otusstudenttest.dao.QuestionDao;
import ru.anovikov.learning.otusstudenttest.domain.Question;

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
