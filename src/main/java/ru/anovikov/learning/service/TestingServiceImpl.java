package ru.anovikov.learning.service;

import ru.anovikov.learning.domain.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class TestingServiceImpl implements TestingService {

    private BufferedReader reader;
    private QuestionService questionService;
    private List<Question> testingQuestions;
    private int currentQiestionNumber;

    public TestingServiceImpl(QuestionService questionService) {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.questionService = questionService;
        this.testingQuestions = this.questionService.getAllQuestions();
        this.currentQiestionNumber = 0;
    }

    public String readUserName() {
        String name = "";
        try {
            System.out.print("Enter your name: ");
            name = reader.readLine();
        }
        catch (IOException e) {
            System.out.println("Cannot read name");
        }
        return name;
    }

    public String readUserLastName() {
        String lastname = "";
        try {
            System.out.print("Enter your lastname: ");
            lastname = reader.readLine();
        }
        catch (IOException e) {
            System.out.println("Cannot read lastname");
        }
        return lastname;
    }

    public Question getNextQuestion() {
        if (currentQiestionNumber < testingQuestions.size()) {
            Question question = testingQuestions.get(currentQiestionNumber);
            currentQiestionNumber++;
            return question;
        }
        return null;
    }

    public void printQuestion(Question question){

        System.out.println(question.getText());

        int i = 0;
        while (i < question.getAnswers().size()) {
            System.out.println(String.valueOf(i+1) + ":" + question.getAnswers().get(i));
            i++;
        }
    }

    public int readTestAnswer() {
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
            catch (IOException e) {
                System.out.println("Cannot read answer.");
            }
        }
        return answ;
    }

    public void startTest() {
        String userName = this.readUserName();
        String userLastName = this.readUserLastName();
        int correctAnsw = 0;

        Question q = this.getNextQuestion();
        int questionCount = 0;
        while (q != null) {

            this.printQuestion(q);

            if (this.readTestAnswer() == q.getRightAnswer()) {
                correctAnsw++;
            }

            q = this.getNextQuestion();
            questionCount++;
        }

        System.out.println("Result: " + correctAnsw + " out " + questionCount);
    }
}