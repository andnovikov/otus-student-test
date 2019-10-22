package ru.anovikov.learning.otusstudenttest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.anovikov.learning.otusstudenttest.service.TestingService;

@SpringBootApplication
public class OtusStudentTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(OtusStudentTestApplication.class, args);

		TestingService testingService = context.getBean(TestingService.class);

		testingService.startTest();
	}

}
