package ru.anovikov.learning.otusstudenttest.service;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.DelegatingMessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import ru.anovikov.learning.otusstudenttest.domain.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@SpringBootTest
class TestingServiceTest {

    @InjectMocks
    TestingServiceImpl testingService;

    @Mock
    QuestionServiceImpl questionService;

    @Mock
    MessageSource messageSource;

    @Mock
    BufferedReader reader;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void readUserName() throws IOException {
        String example = "Boris";
        when(reader.readLine()).thenReturn(example);
        when(messageSource.getMessage(Mockito.anyString(), Mockito.any(Object[].class), Mockito.any(Locale.class))).thenReturn("");
        String userLastname = testingService.readUserName();
        assertEquals(example + " " + example, userLastname );
    }

    @Test
    void getNextQuestion() {
        List<Question> list = new ArrayList<>();
        Question q1 = new Question("question1");
        q1.addAnswer("1");
        q1.addAnswer("2");
        q1.addAnswer("3");
        q1.addAnswer("4");
        q1.setRightAnswer(1);
        list.add(q1);
        when(questionService.getAllQuestions()).thenReturn(list);
        Question q = testingService.getNextQuestion();
        assertEquals(q1, q);
    }

    @Test
    void startTest() throws IOException {
        List<Question> list = new ArrayList<>();
        Question q1 = new Question("question1");
        q1.addAnswer("1");
        q1.addAnswer("2");
        q1.addAnswer("3");
        q1.addAnswer("4");
        q1.setRightAnswer(1);
        list.add(q1);
        when(questionService.getAllQuestions()).thenReturn(list);

        String example = "1";
        when(reader.readLine()).thenReturn(example);
        when(messageSource.getMessage(Mockito.anyString(), Mockito.any(Object[].class), Mockito.any(Locale.class))).thenReturn("");
        when(questionService.getAllQuestions()).thenReturn(list);
        int res = testingService.startTest();

        assertEquals(1, res);
    }
}