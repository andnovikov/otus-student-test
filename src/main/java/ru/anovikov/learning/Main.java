package ru.anovikov.learning;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.anovikov.learning.domain.Question;
import ru.anovikov.learning.service.QuestionService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        QuestionService questionService = context.getBean(QuestionService.class);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Enter your name: ");
        String name = reader.readLine();

        System.out.print("Enter your lastname: ");
        String lastname = reader.readLine();

        int correctAnsw = 0;
        List<Question> questionList = questionService.getAllQuestions();
        for (Question q: questionList) {
            System.out.println(q.getText());

            int i = 0;
            while (i < q.getAnswers().size()) {
                System.out.println(String.valueOf(i+1) + ":" + q.getAnswers().get(i));
                i++;
            }

            int answ = 0; boolean answInput = false;
            while (!answInput) {
                try {
                    System.out.print("Enter number of your answer: ");
                    answ = Integer.parseInt(reader.readLine());

                    if ((answ <= 0) || (answ > 4)) {
                        System.out.println("Wrong number.");
                    }
                    else { answInput = true; };
                }
                catch (NumberFormatException e) {
                    System.out.println("Wrong number.");
                }
            }

            if (answ == q.getRightAnswer()) {
                correctAnsw++;
            }
        }

        System.out.println("Result: " + correctAnsw + " out " + questionList.size());
    }
}