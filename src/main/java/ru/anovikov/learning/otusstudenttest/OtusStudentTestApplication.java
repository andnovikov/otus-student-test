package ru.anovikov.learning.otusstudenttest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import ru.anovikov.learning.otusstudenttest.service.TestingService;

@EnableAutoConfiguration
@SpringBootApplication
public class OtusStudentTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(OtusStudentTestApplication.class, args);
	}

	/*
	@Bean
	public CommandLineRunner test(TestingService testingService){
		return(args) -> {
			testingService.startTest();
		};
	}

	 */
}
