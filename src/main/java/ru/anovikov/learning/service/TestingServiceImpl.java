package ru.anovikov.learning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.anovikov.learning.domain.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Locale;

@Service
public class TestingServiceImpl implements TestingService {

    private final BufferedReader reader;
    private List<Question> testingQuestions;
    private final Locale locale;
    private int currentQiestionNumber;

    private final QuestionService questionService;
    private final MessageSource messageSource;

    @Autowired
    public TestingServiceImpl(QuestionService questionService, MessageSource messageSource) {
        this.questionService = questionService;
        this.messageSource = messageSource;

        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.testingQuestions = this.questionService.getAllQuestions();
        this.locale = Locale.getDefault();
        this.currentQiestionNumber = 0;
    }

    public String readUserName() {
        String name = "";
        try {
            System.out.print(messageSource.getMessage("read.name", new String[] {""}, locale) + ": ");
            name = reader.readLine();
        }
        catch (IOException e) {
            System.out.print(messageSource.getMessage("error.read.name", new String[] {""}, locale) + ": " + e.getMessage());
        }
        return name;
    }

    public String readUserLastName() {
        String lastname = "";
        try {
            System.out.print(messageSource.getMessage("read.lastname", new String[] {""}, locale) + ": ");
            lastname = reader.readLine();
        }
        catch (IOException e) {
            System.out.print(messageSource.getMessage("error.read.lastname", new String[] {""}, locale) + ": " + e.getMessage());
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
                System.out.print(messageSource.getMessage("read.answer", new String[] {""}, locale) + ": ");
                answ = Integer.parseInt(reader.readLine());

                if ((answ <= 0) || (answ > 4)) {
                    System.out.println(messageSource.getMessage("warning.read.answer", new String[] {""}, locale));
                }
                else { answInput = true; };
            }
            catch (NumberFormatException e) {
                System.out.print(messageSource.getMessage("warning.read.answer", new String[] {""}, locale));
            }
            catch (IOException e) {
                System.out.print(messageSource.getMessage("error.read.answer", new String[] {""}, locale) + ": " + e.getMessage());
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

        System.out.print(String.format(messageSource.getMessage("write.result", new String[]{""}, locale), correctAnsw, questionCount));
    }
}