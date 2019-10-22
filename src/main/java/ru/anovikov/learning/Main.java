package ru.anovikov.learning;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.anovikov.learning.otusstudenttest.service.TestingService;

@Configuration
@ComponentScan
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Main.class);

        TestingService testingService = context.getBean(TestingService.class);

        testingService.startTest();
    }
}