package ru.anovikov.learning.otusstudenttest.service;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;


@SpringBootTest
class TestingServiceTest {

    @InjectMocks
    TestingServiceImpl testingService;

    @Mock
    QuestionServiceImpl questionService;

    @Mock
    MessageSource messageSource;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void readUserName() {
        String example = "Boris";
        InputStream stream = new ByteArrayInputStream((example+"\n").getBytes(StandardCharsets.UTF_8)); //this stream will output the example string
        InputStream stdin = System.in;
        System.setIn(stream);
        String username = testingService.readUserName();
        assertEquals(example, username);
        System.setIn(stdin);
    }

    @Test
    void readUserLastName() {
        String example = "Britva";
        InputStream stream = new ByteArrayInputStream((example+"\n").getBytes(StandardCharsets.UTF_8)); //this stream will output the example string
        InputStream stdin = System.in;
        System.setIn(stream);
        String userLastname = testingService.readUserLastName();
        assertEquals(example, userLastname );
        System.setIn(stdin);
    }

    /*
    @Test
    void getNextQuestion() {
    }

    @Test
    void printQuestion() {
    }

    @Test
    void readTestAnswer() {
    }
    */
}