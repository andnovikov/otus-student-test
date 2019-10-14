package ru.anovikov.learning;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.anovikov.learning.service.TestingService;

public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        TestingService testingService = context.getBean(TestingService.class);

        TestingRunner test = new TestingRunner();
        test.StartTesting(testingService);
    }
}