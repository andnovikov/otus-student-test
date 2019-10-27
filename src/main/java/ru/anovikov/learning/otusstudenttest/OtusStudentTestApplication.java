package ru.anovikov.learning.otusstudenttest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.anovikov.learning.otusstudenttest.service.TestingService;

@EnableAutoConfiguration
@SpringBootApplication
public class OtusStudentTestApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(OtusStudentTestApplication.class, args);
		TestingService testingService = context.getBean(TestingService.class);
		testingService.startTest();
	}
}