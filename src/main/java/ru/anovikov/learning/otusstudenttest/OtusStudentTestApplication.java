package ru.anovikov.learning.otusstudenttest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
public class OtusStudentTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(OtusStudentTestApplication.class, args);
	}
}